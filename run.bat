@echo off
echo =======================================
echo     ADIDAS SHOE STORE - LAB 3
echo =======================================

REM Kiểm tra Maven
where mvn >nul 2>nul
if %errorlevel% neq 0 (
    echo ❌ Maven không được tìm thấy. Vui lòng cài đặt Maven.
    echo    Tải tại: https://maven.apache.org/download.cgi
    pause
    exit /b 1
)

REM Kiểm tra Java
where java >nul 2>nul
if %errorlevel% neq 0 (
    echo ❌ Java không được tìm thấy. Vui lòng cài đặt Java 11+.
    pause
    exit /b 1
)

echo ✅ Đang khởi động ứng dụng JavaFX...
echo 📦 Downloading dependencies và compiling...

REM Chạy ứng dụng với Maven
mvn clean javafx:run

echo 🎉 Ứng dụng đã đóng!
echo =======================================
pause 