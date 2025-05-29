# Adidas Shoe Store - Lab 3

Ứng dụng bán giày Adidas được xây dựng bằng JavaFX với các tính năng:

## 🎯 Tính năng

✅ **Hiển thị 8 sản phẩm giày** với hình ảnh, tên, giá, thương hiệu và mô tả  
✅ **Tính năng click chọn sản phẩm** - sản phẩm được chọn sẽ thay đổi màu khung (viền xanh)  
✅ **Panel thông tin chi tiết** - hiển thị thông tin sản phẩm được chọn ở phía bên phải  
✅ **Giao diện đẹp mắt** với hiệu ứng hover, shadow và responsive design  
✅ **Architecture pattern** - Sử dụng Maven để quản lý dependencies  

## 🚀 Cách chạy nhanh

### Option 1: Sử dụng script (Khuyến nghị)

**MacOS/Linux:**
```bash
./run.sh
```

**Windows:**
```cmd
run.bat
```

### Option 2: Sử dụng Maven trực tiếp
```bash
mvn clean javafx:run
```

## 📋 Yêu cầu hệ thống

- **Java 11+** - [Tải Java](https://adoptium.net/)
- **Maven 3.6+** - [Tải Maven](https://maven.apache.org/download.cgi)

## 🛠️ Cài đặt chi tiết

### 1. Cài đặt Java
```bash
# Kiểm tra Java version
java -version

# Nếu chưa có Java, tải từ: https://adoptium.net/
```

### 2. Cài đặt Maven
```bash
# Kiểm tra Maven
mvn -version

# MacOS với Homebrew
brew install maven

# Windows với Chocolatey
choco install maven
```

### 3. Clone và chạy
```bash
git clone [repository-url]
cd IE303_22521232_LAB
./run.sh   # hoặc run.bat trên Windows
```

## 📁 Cấu trúc Project

```
IE303_22521232_LAB/
├── src/
│   └── main/
│       ├── java/
│       │   └── lab3.java          # File ứng dụng chính
│       └── resources/
│           ├── img1.png           # Hình ảnh sản phẩm
│           ├── img2.png
│           ├── img3.png
│           ├── img4.png
│           ├── img5.png
│           └── img6.png
├── pom.xml                        # Maven configuration
├── run.sh                         # Script chạy cho MacOS/Linux  
├── run.bat                        # Script chạy cho Windows
└── README.md                      # File hướng dẫn này
```

## 🎮 Hướng dẫn sử dụng

1. **Khởi động ứng dụng** - Chạy script `./run.sh` hoặc `run.bat`
2. **Xem danh sách sản phẩm** - 8 sản phẩm hiển thị dạng lưới 4x2
3. **Chọn sản phẩm** - Click vào bất kỳ sản phẩm nào để chọn
4. **Xem thông tin chi tiết** - Panel bên phải sẽ hiển thị thông tin của sản phẩm được chọn
5. **Hiệu ứng visual** - Hover chuột để xem hiệu ứng, sản phẩm được chọn có viền xanh

## ⚙️ Tính năng kỹ thuật

- **Layout Manager**: BorderPane với GridPane cho sản phẩm và VBox cho panel thông tin
- **Responsive Design**: Ứng dụng có thể resize và scroll
- **Interactive UI**: Hiệu ứng hover và selection với CSS styling  
- **Modern UI**: Shadow effects, rounded corners, color schemes
- **Resource Management**: Hình ảnh được quản lý trong classpath resources
- **Maven Integration**: Dependency management và build automation

## 🚨 Troubleshooting

### Lỗi JavaFX không được tìm thấy
```
Error: JavaFX runtime components are missing
```
**Giải pháp**: Sử dụng Maven để tự động tải JavaFX dependencies.

### Lỗi Maven không được tìm thấy
```
mvn: command not found
```
**Giải pháp**: 
- Cài đặt Maven từ https://maven.apache.org/download.cgi
- Thêm Maven vào PATH environment variable

### Lỗi không tải được hình ảnh
```
Không thể tải hình ảnh: imgX.png
```
**Giải pháp**: Đảm bảo các file hình ảnh có trong thư mục `src/main/resources/`.

### Lỗi Java version không tương thích
```
Unsupported class file major version
```
**Giải pháp**: Cài đặt Java 11 hoặc cao hơn.

## 📸 Screenshots

### Giao diện chính
- Hiển thị 8 sản phẩm trong lưới 4x2
- Panel thông tin chi tiết bên phải
- Header với tên cửa hàng

### Tính năng tương tác
- Click để chọn sản phẩm (viền xanh)
- Hover effects với màu sắc thay đổi
- Thông tin sản phẩm cập nhật real-time

## 👨‍💻 Thông tin tác giả

**Sinh viên**: Điền Hồ  
**MSSV**: 22521232  
**Môn học**: IE303 - Công nghệ Web và Ứng dụng  
**Lab**: Lab 3 - JavaFX Application  

## 📄 License

This project is for educational purposes only.