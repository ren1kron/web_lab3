#!/bin/bash
# Скрипт history: откатываемся назад до тех пор, пока сборка не пройдет, либо не исчерпаем 10 попыток.
# Если сборка проходит, формируется файл с диффом между последней рабочей ревизией и предыдущей.

max_attempts=10
attempt=1
build_success=false

echo "Начинаем проверку сборки текущей ревизии..."

while [ $attempt -le $max_attempts ]; do
    echo "Попытка $attempt: попытка собрать проект..."
    if ./gradlew clean compileJava; then
        build_success=true
        break
    else
        echo "Сборка не удалась. Откатываемся на один коммит..."
        git reset HEAD~1 --hard
    fi
    attempt=$((attempt + 1))
done

if [ "$build_success" = true ]; then
    echo "Сборка прошла успешно на попытке $attempt."
    current_sha=$(git rev-parse HEAD)
    echo "Текущая ревизия: $current_sha"
    # Проверяем, существует ли предыдущая ревизия для формирования диффа.
    if git rev-parse "${current_sha}~1" >/dev/null 2>&1; then
        git show "${current_sha}~1..${current_sha}" > history_diff.txt
        echo "Файл с diff создан: history_diff.txt"
    else
        echo "Предыдущая ревизия не найдена, невозможно сформировать diff."
    fi
else
    echo "Сборка не удалась после $max_attempts попыток, рабочая ревизия не найдена."
fi
