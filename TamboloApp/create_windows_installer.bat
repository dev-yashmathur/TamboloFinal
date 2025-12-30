@echo off
set "APP_NAME=Tambolo"
set "VERSION=1.0"
if not "%~1"=="" set "VERSION=%~1"
set "SRC_DIR=src"
set "BUILD_DIR=build"
set "INSTALLER_DIR=%BUILD_DIR%\installer"
set "JAR_FILE=%BUILD_DIR%\libs\tambolo.jar"

echo === Building %APP_NAME% version %VERSION% on Windows ===

REM Check for Java
where javac >nul 2>nul
if %errorlevel% neq 0 (
    echo Error: JDK not found. Please ensure Java is installed and in your PATH.
    pause
    exit /b 1
)

REM 1. Clean and Compile
echo [1/4] Cleaning and Compiling...
if exist "%BUILD_DIR%" rmdir /s /q "%BUILD_DIR%"
mkdir "%BUILD_DIR%\classes"
mkdir "%BUILD_DIR%\libs"

REM Gather source files
dir /s /b "%SRC_DIR%\*.java" > sources.txt
javac -d "%BUILD_DIR%\classes" @sources.txt
del sources.txt

if %errorlevel% neq 0 (
    echo Compilation failed.
    pause
    exit /b %errorlevel%
)

REM 2. Create JAR
echo [2/4] Creating JAR file...
jar --create --file "%JAR_FILE%" --main-class tambolo.UI --module-version "%VERSION%" -C "%BUILD_DIR%\classes" .

REM 3. Run jpackage
echo [3/4] Running jpackage...
if not exist "%INSTALLER_DIR%" mkdir "%INSTALLER_DIR%"

jpackage --name "%APP_NAME%" ^
  --input "%BUILD_DIR%\libs" ^
  --main-jar "tambolo.jar" ^
  --type exe ^
  --dest "%INSTALLER_DIR%" ^
  --app-version "%VERSION%" ^
  --win-dir-chooser ^
  --win-menu ^
  --win-shortcut

if %errorlevel% neq 0 (
    echo jpackage failed.
    pause
    exit /b %errorlevel%
)

echo === Success! ===
echo Installer created at: %INSTALLER_DIR%\%APP_NAME%-%VERSION%.exe
pause
