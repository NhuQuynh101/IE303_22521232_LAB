import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.InputStream;

public class lab3 extends Application {
    
    // Dữ liệu sản phẩm
    private static class Product {
        String name;
        String price;
        String brand;
        String description;
        String imagePath;
        
        Product(String name, String price, String brand, String description, String imagePath) {
            this.name = name;
            this.price = price;
            this.brand = brand;
            this.description = description;
            this.imagePath = imagePath;
        }
    }
    
    private Product[] products;
    private VBox[] productCards;
    private VBox selectedCard = null;
    private Label productNameLabel;
    private Label productPriceLabel;
    private Label productBrandLabel;
    private Label productDescLabel;
    private ImageView selectedProductImage;
    
    @Override
    public void start(Stage primaryStage) {
        // Khởi tạo dữ liệu sản phẩm
        initializeProducts();
        
        // Tạo layout chính
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f5f5f5;");
        
        // Tạo tiêu đề
        Label titleLabel = new Label("ADIDAS SHOE STORE");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        titleLabel.setTextFill(Color.web("#2c3e50"));
        titleLabel.setPadding(new Insets(20));
        
        VBox titleBox = new VBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().add(titleLabel);
        titleBox.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-width: 0 0 2 0;");
        
        root.setTop(titleBox);
        
        // Tạo khu vực hiển thị sản phẩm
        GridPane productGrid = createProductGrid();
        ScrollPane productScrollPane = new ScrollPane(productGrid);
        productScrollPane.setFitToWidth(true);
        productScrollPane.setStyle("-fx-background: #f5f5f5; -fx-background-color: #f5f5f5;");
        
        // Tạo panel thông tin sản phẩm
        VBox productDetailPanel = createProductDetailPanel();
        
        // Tạo layout chính với sản phẩm bên trái và thông tin bên phải
        HBox mainContent = new HBox(20);
        mainContent.setPadding(new Insets(20));
        
        VBox leftPanel = new VBox();
        leftPanel.getChildren().add(productScrollPane);
        leftPanel.setPrefWidth(800);
        
        productDetailPanel.setPrefWidth(350);
        
        mainContent.getChildren().addAll(leftPanel, productDetailPanel);
        
        root.setCenter(mainContent);
        
        // Tạo Scene và Stage
        Scene scene = new Scene(root, 1200, 800);
        primaryStage.setTitle("Adidas Shoe Store - Lab 3");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Chọn sản phẩm đầu tiên mặc định
        if (productCards.length > 0) {
            selectProduct(0);
        }
    }
    
    private void initializeProducts() {
        products = new Product[] {
            new Product("4DFWD PULSE SHOES", "$160.00", "Adidas", 
                       "This product is excluded from all promotional discounts and offers.", "img1.png"),
            new Product("FORUM MID SHOES", "$100.00", "Adidas", 
                       "NMD City Sock 2 - Classic urban style with modern comfort.", "img2.png"),
            new Product("SUPERNOVA SHOES", "$150.00", "Adidas", 
                       "NMD City Sock 2 - Premium running shoes for everyday athletes.", "img3.png"),
            new Product("ADIDAS RUNNING", "$160.00", "Adidas", 
                       "NMD City Sock 2 - High-performance running shoes.", "img4.png"),
            new Product("ADIDAS SPORT", "$120.00", "Adidas", 
                       "NMD City Sock 2 - Versatile sports shoes for active lifestyle.", "img5.png"),
            new Product("4DFWD PULSE SHOES", "$160.00", "Adidas", 
                       "This product is excluded from all promotional discounts and offers.", "img6.png"),
            new Product("4DFWD PULSE SHOES", "$160.00", "Adidas", 
                       "This product is excluded from all promotional discounts and offers.", "img1.png"),
            new Product("FORUM MID SHOES", "$100.00", "Adidas", 
                       "This product is excluded from all promotional discounts and offers.", "img2.png")
        };
    }
    
    private GridPane createProductGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(10));
        grid.setAlignment(Pos.CENTER);
        
        productCards = new VBox[products.length];
        
        for (int i = 0; i < products.length; i++) {
            VBox productCard = createProductCard(products[i], i);
            productCards[i] = productCard;
            
            int row = i / 4;
            int col = i % 4;
            grid.add(productCard, col, row);
        }
        
        return grid;
    }
    
    private VBox createProductCard(Product product, int index) {
        VBox card = new VBox(10);
        card.setPrefSize(180, 280);
        card.setAlignment(Pos.TOP_CENTER);
        card.setPadding(new Insets(15));
        card.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-width: 2; " +
                     "-fx-border-radius: 8; -fx-background-radius: 8; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 2);");
        
        // Tạo hình ảnh sản phẩm
        ImageView imageView = new ImageView();
        try {
            InputStream imageStream = getClass().getResourceAsStream("/" + product.imagePath);
            if (imageStream != null) {
                Image image = new Image(imageStream);
                imageView.setImage(image);
                imageView.setFitWidth(140);
                imageView.setFitHeight(100);
                imageView.setPreserveRatio(true);
                imageStream.close();
            } else {
                System.out.println("Không thể tải hình ảnh: " + product.imagePath);
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi tải hình ảnh: " + product.imagePath + " - " + e.getMessage());
        }
        
        // Tên sản phẩm
        Label nameLabel = new Label(product.name);
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        nameLabel.setWrapText(true);
        nameLabel.setMaxWidth(150);
        nameLabel.setAlignment(Pos.CENTER);
        nameLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        
        // Thương hiệu
        Label brandLabel = new Label(product.brand);
        brandLabel.setFont(Font.font("Arial", 10));
        brandLabel.setTextFill(Color.web("#666"));
        
        // Mô tả
        Label descLabel = new Label(product.description);
        descLabel.setFont(Font.font("Arial", 9));
        descLabel.setTextFill(Color.web("#888"));
        descLabel.setWrapText(true);
        descLabel.setMaxWidth(150);
        descLabel.setMaxHeight(40);
        
        // Giá
        Label priceLabel = new Label(product.price);
        priceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        priceLabel.setTextFill(Color.web("#e74c3c"));
        
        card.getChildren().addAll(imageView, nameLabel, brandLabel, descLabel, priceLabel);
        
        // Thêm sự kiện click
        card.setOnMouseClicked(e -> selectProduct(index));
        
        // Thêm hiệu ứng hover
        card.setOnMouseEntered(e -> {
            if (selectedCard != card) {
                card.setStyle("-fx-background-color: #f8f9fa; -fx-border-color: #007bff; -fx-border-width: 2; " +
                            "-fx-border-radius: 8; -fx-background-radius: 8; -fx-effect: dropshadow(three-pass-box, rgba(0,123,255,0.2), 8, 0, 0, 3);");
            }
        });
        
        card.setOnMouseExited(e -> {
            if (selectedCard != card) {
                card.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-width: 2; " +
                            "-fx-border-radius: 8; -fx-background-radius: 8; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 2);");
            }
        });
        
        return card;
    }
    
    private VBox createProductDetailPanel() {
        VBox panel = new VBox(20);
        panel.setPadding(new Insets(20));
        panel.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-width: 2; " +
                      "-fx-border-radius: 8; -fx-background-radius: 8; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 2);");
        
        // Tiêu đề panel
        Label titleLabel = new Label("THÔNG TIN SẢN PHẨM");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        titleLabel.setTextFill(Color.web("#2c3e50"));
        
        // Hình ảnh sản phẩm được chọn
        selectedProductImage = new ImageView();
        selectedProductImage.setFitWidth(200);
        selectedProductImage.setFitHeight(150);
        selectedProductImage.setPreserveRatio(true);
        
        // Thông tin sản phẩm
        productNameLabel = new Label();
        productNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        productNameLabel.setWrapText(true);
        productNameLabel.setTextFill(Color.web("#2c3e50"));
        
        productBrandLabel = new Label();
        productBrandLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        productBrandLabel.setTextFill(Color.web("#666"));
        
        productPriceLabel = new Label();
        productPriceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        productPriceLabel.setTextFill(Color.web("#e74c3c"));
        
        productDescLabel = new Label();
        productDescLabel.setFont(Font.font("Arial", 12));
        productDescLabel.setWrapText(true);
        productDescLabel.setTextFill(Color.web("#555"));
        
        // Thêm khoảng cách
        Region spacer1 = new Region();
        spacer1.setPrefHeight(10);
        
        Region spacer2 = new Region();
        spacer2.setPrefHeight(10);
        
        panel.getChildren().addAll(
            titleLabel, spacer1,
            selectedProductImage, spacer2,
            productNameLabel,
            productBrandLabel,
            productPriceLabel,
            productDescLabel
        );
        
        return panel;
    }
    
    private void selectProduct(int index) {
        // Bỏ chọn sản phẩm trước đó
        if (selectedCard != null) {
            selectedCard.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-width: 2; " +
                                "-fx-border-radius: 8; -fx-background-radius: 8; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 2);");
        }
        
        // Chọn sản phẩm mới
        selectedCard = productCards[index];
        selectedCard.setStyle("-fx-background-color: #e3f2fd; -fx-border-color: #2196f3; -fx-border-width: 3; " +
                            "-fx-border-radius: 8; -fx-background-radius: 8; -fx-effect: dropshadow(three-pass-box, rgba(33,150,243,0.3), 10, 0, 0, 4);");
        
        // Cập nhật thông tin sản phẩm
        Product selectedProduct = products[index];
        productNameLabel.setText(selectedProduct.name);
        productBrandLabel.setText(selectedProduct.brand);
        productPriceLabel.setText(selectedProduct.price);
        productDescLabel.setText(selectedProduct.description);
        
        // Cập nhật hình ảnh
        try {
            InputStream imageStream = getClass().getResourceAsStream("/" + selectedProduct.imagePath);
            if (imageStream != null) {
                Image image = new Image(imageStream);
                selectedProductImage.setImage(image);
                imageStream.close();
            } else {
                System.out.println("Không thể tải hình ảnh: " + selectedProduct.imagePath);
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi tải hình ảnh: " + selectedProduct.imagePath + " - " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
