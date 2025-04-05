@echo off
REM === Настройки ===
set "WILDFLY_HOME=D:\Programm Files\wildfly-33.0.2.Final\wildfly-33.0.2.Final"
set "DEPLOY_FILE=ant\build\libs\web3.war"
set "HOST=localhost"
set "PORT=6969"
set "USER=Kostya666"
set "PASS=666666"

REM === Проверка наличия файла ===
if not exist "%DEPLOY_FILE%" (
    echo Файл %DEPLOY_FILE% не найден!
    exit /b 1
)

REM === Запуск WildFly ===
echo Запуск WildFly...
start "WildFly" "%WILDFLY_HOME%\bin\standalone.bat"
REM === Ожидание запуска (ждём порт 6969) ===
echo Ожидание запуска сервера...
:waitloop
timeout /t 2 >nul
powershell -Command "try { (New-Object Net.Sockets.TcpClient('%HOST%', %PORT%)).Close(); exit 0 } catch { exit 1 }"
if %errorlevel% neq 0 goto waitloop
echo WildFly запущен!

REM === Выполнение деплоя ===
echo Деплой %DEPLOY_FILE% на WildFly...

"%WILDFLY_HOME%\bin\jboss-cli.bat" --connect --controller=%HOST%:%PORT% --user=%USER% --password=%PASS% ^
"deploy %DEPLOY_FILE% --force"

echo Деплой завершён!
