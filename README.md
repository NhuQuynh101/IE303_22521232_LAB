# Adidas Shoe Store - Lab 3

Ứng dụng bán giày Adidas được xây dựng bằng JavaFX với các tính năng và hiệu ứng động:

## 🎯 Tính năng

✅ **Hiển thị 8 sản phẩm giày** với hình ảnh, tên, giá, thương hiệu và mô tả  
✅ **Tính năng click chọn sản phẩm** - sản phẩm được chọn sẽ thay đổi màu khung (viền xanh)  
✅ **Panel thông tin chi tiết** - hiển thị thông tin sản phẩm được chọn ở phía bên phải  
✅ **Giao diện đẹp mắt** với hiệu ứng hover, shadow và responsive design  
✅ **Architecture pattern** - Sử dụng Maven để quản lý dependencies  
🆕 **Hiệu ứng động nâng cao**:
   - **Scale Animation**: Card sản phẩm có hiệu ứng bounce khi được chọn
   - **Fade Transition**: Panel thông tin fade in/out mượt mà khi chuyển sản phẩm
   - **Slide Effect**: Thông tin sản phẩm slide từ phải sang trái
   - **Hover Animation**: Card có hiệu ứng scale và shadow khi hover chuột
   - **Image Scale**: Hình ảnh sản phẩm có hiệu ứng zoom khi cập nhật

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
│       │   └── lab3.java          # File ứng dụng chính với animations
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
3. **Hover chuột** - Di chuột qua sản phẩm để xem hiệu ứng hover với scale và shadow
4. **Chọn sản phẩm** - Click vào bất kỳ sản phẩm nào để chọn:
   - Card sản phẩm sẽ có hiệu ứng bounce và scale lên
   - Viền chuyển thành màu xanh với shadow mạnh hơn
   - Panel thông tin bên phải sẽ fade out và slide
5. **Xem thông tin chi tiết** - Panel bên phải sẽ fade in với thông tin mới:
   - Hình ảnh sản phẩm zoom từ nhỏ đến kích thước đầy đủ
   - Text thông tin slide vào từ phải
   - Hiệu ứng mượt mà và chuyên nghiệp

## ⚙️ Tính năng kỹ thuật

- **Layout Manager**: BorderPane với GridPane cho sản phẩm và VBox cho panel thông tin
- **Responsive Design**: Ứng dụng có thể resize và scroll
- **Interactive UI**: Hiệu ứng hover và selection với CSS styling  
- **Modern UI**: Shadow effects, rounded corners, color schemes
- **Resource Management**: Hình ảnh được quản lý trong classpath resources
- **Maven Integration**: Dependency management và build automation
- **Animation Framework**: Sử dụng JavaFX Animation API:
  - `ScaleTransition` cho hiệu ứng scale/zoom
  - `FadeTransition` cho hiệu ứng fade in/out
  - `TranslateTransition` cho hiệu ứng slide
  - `ParallelTransition` để chạy nhiều animation cùng lúc
  - Timing và easing tối ưu cho UX mượt mà

## 🎨 Hiệu ứng và Animation

### Card Product Animation
- **Hover Effect**: Scale 1.05x + fade + shadow enhancement (200ms)
- **Selection Bounce**: Scale 1.08x với auto-reverse (300ms)
- **Deselection**: Scale về 1.0x với fade (150ms)

### Product Info Panel Animation
- **Hide Transition**: Fade out (0.3 opacity) + slide right 20px (200ms)
- **Show Transition**: Fade in + slide back + image scale (300-400ms)
- **Smooth Timing**: Các animation được chain với onFinished events

### Visual Feedback
- **Selected State**: Blue border (#2196f3) + enhanced shadow
- **Hover State**: Blue border (#007bff) + scale + shadow
- **Normal State**: Gray border (#ddd) + subtle shadow

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

### Animation lag hoặc choppy
**Giải pháp**: 
- Đảm bảo có đủ RAM và GPU acceleration
- JavaFX sử dụng hardware acceleration tự động
- Giảm duration của animation nếu cần

## 📸 Screenshots & Demo

### Giao diện chính
- Hiển thị 8 sản phẩm trong lưới 4x2 với animations
- Panel thông tin chi tiết bên phải với smooth transitions
- Header với tên cửa hàng

### Tính năng tương tác
- **Click Selection**: Bounce effect + blue border + enhanced shadow
- **Hover Effects**: Scale + fade + shadow animation
- **Info Panel**: Slide + fade + image zoom transitions
- **Thông tin sản phẩm**: Cập nhật real-time với smooth animations

## 👨‍💻 Thông tin tác giả

**Sinh viên**: Ngô Thị Như Quỳnh 
**MSSV**: 22521232  
**Môn học**: IE303 - Công nghệ Java  
**Lab**: Lab 3 - 

## 📄 License

This project is for educational purposes only.

## 🎬 Demo Video

### Xem video demo ứng dụng:

https://github.com/user-attachments/assets/[video-id]

*Video demo hiển thị tất cả tính năng và hiệu ứng của ứng dụng bao gồm:*
- ✨ **Animations**: Hiệu ứng bounce, fade, scale và slide transitions
- 🖱️ **Interactive UI**: Hover effects và selection animations  
- 📱 **Responsive Design**: Giao diện đẹp mắt và mượt mà
- 🛒 **Product Selection**: Chọn sản phẩm với visual feedback
- 📋 **Info Panel**: Panel thông tin chi tiết với smooth transitions

### File video demo:
- **File**: `Demo.mov`
- **Kích thước**: 4.2MB
- **Chất lượng**: HD recording của toàn bộ workflow

> **Lưu ý**: Nếu không xem được video trên GitHub, bạn có thể tải file `2025-06-03 22-15-13.mov` trong repository để xem demo.

---

**🎯 Tóm tắt**: Ứng dụng JavaFX hoàn chỉnh với animations chuyên nghiệp, UI/UX hiện đại và architecture clean code.

## Demo
