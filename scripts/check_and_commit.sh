#!/bin/bash
# Получаем список изменённых файлов
changed=$(git diff --name-only HEAD)

echo "Changed files: $changed"

# Определяем критичный класс
critical="org/ren1kron/FacesConfiguration.java"

# Если критичный класс не найден в списке изменений, то выполняем commit
if [[ "$changed" != *"$critical"* ]]; then
    echo "Critical file not modified. Committing changes..."
    git commit -a -m "Automatic commit"
    # Если требуется, можно также выполнить git push:
    # git push
else
    echo "Changes affect critical classes. Commit skipped."
fi
