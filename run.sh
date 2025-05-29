#!/bin/bash

echo "======================================="
echo "    ADIDAS SHOE STORE - LAB 3"
echo "======================================="

# Kiểm tra Maven
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven không được tìm thấy. Vui lòng cài đặt Maven."
    echo "   Tải tại: https://maven.apache.org/download.cgi"
    exit 1
fi

# Kiểm tra Java
if ! command -v java &> /dev/null; then
    echo "❌ Java không được tìm thấy. Vui lòng cài đặt Java 11+."
    exit 1
fi

echo "✅ Đang khởi động ứng dụng JavaFX..."
echo "📦 Downloading dependencies và compiling..."

# Chạy ứng dụng với Maven
mvn clean javafx:run

echo "🎉 Ứng dụng đã đóng!"
echo "======================================="