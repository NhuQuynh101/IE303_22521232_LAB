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
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.animation.ParallelTransition;
import javafx.util.Duration;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;

public class lab4 extends Application {
    
    // Dữ liệu sản phẩm
    private static class Product {
        String name;
        double price;
        String brand;
        String description;
        String image;
    }
    
    private List<Product> products;
    private VBox[] productCards;
    private VBox selectedCard = null;
    private Label productNameLabel;
    private Label productPriceLabel;
    private Label productBrandLabel;
    private Label productDescLabel;
    private ImageView selectedProductImage;
    private VBox productDetailPanel;
    
    @Override
    public void start(Stage primaryStage) {
        // Khởi tạo dữ liệu sản phẩm từ backend
        initializeProductsFromApi();
        
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
        productDetailPanel = createProductDetailPanel();
        
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
        primaryStage.setTitle("Adidas Shoe Store - Lab 4");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Chọn sản phẩm đầu tiên mặc định với hiệu ứng
        if (productCards.length > 0) {
            selectProductWithAnimation(0);
        }
    }
    
    private void initializeProductsFromApi() {
        try {
            URL url = new URL("http://localhost:8080/api/products");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder content = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            System.out.println("JSON từ backend: " + content.toString()); // debug
            Gson gson = new Gson();
            products = gson.fromJson(content.toString(), new TypeToken<List<Product>>(){}.getType());
            System.out.println("Số lượng sản phẩm lấy được từ API: " + products.size()); // debug
        } catch (Exception e) {
            e.printStackTrace();
            products = List.of();
        }
    }
    
    private GridPane createProductGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(10));
        grid.setAlignment(Pos.CENTER);
        
        productCards = new VBox[products.size()];
        
        for (int i = 0; i < products.size(); i++) {
            VBox productCard = createProductCard(products.get(i), i);
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
        
        // Tạo hình ảnh sản phẩm từ backend
        ImageView imageView = new ImageView();
        try {
            Image image = new Image("http://localhost:8080/" + product.image, true);
            imageView.setImage(image);
            imageView.setFitWidth(140);
            imageView.setFitHeight(100);
            imageView.setPreserveRatio(true);
        } catch (Exception e) {
            System.out.println("Không thể tải hình ảnh: " + product.image);
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
        Label descLabel = new Label(product.description.length() > 50 ? 
                                  product.description.substring(0, 50) + "..." : product.description);
        descLabel.setFont(Font.font("Arial", 9));
        descLabel.setTextFill(Color.web("#888"));
        descLabel.setWrapText(true);
        descLabel.setMaxWidth(150);
        descLabel.setMaxHeight(40);
        
        // Giá
        Label priceLabel = new Label("$" + product.price);
        priceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        priceLabel.setTextFill(Color.web("#e74c3c"));
        
        card.getChildren().addAll(imageView, nameLabel, brandLabel, descLabel, priceLabel);
        
        // Thêm sự kiện click với animation
        card.setOnMouseClicked(e -> selectProductWithAnimation(index));
        
        // Thêm hiệu ứng hover với animation
        card.setOnMouseEntered(e -> {
            if (selectedCard != card) {
                animateCardHover(card, true);
            }
        });
        
        card.setOnMouseExited(e -> {
            if (selectedCard != card) {
                animateCardHover(card, false);
            }
        });
        
        return card;
    }
    
    private void animateCardHover(VBox card, boolean isHover) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), card);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(200), card);
        
        if (isHover) {
            scaleTransition.setToX(1.05);
            scaleTransition.setToY(1.05);
            fadeTransition.setToValue(0.9);
            card.setStyle("-fx-background-color: #f8f9fa; -fx-border-color: #007bff; -fx-border-width: 2; " +
                        "-fx-border-radius: 8; -fx-background-radius: 8; -fx-effect: dropshadow(three-pass-box, rgba(0,123,255,0.3), 10, 0, 0, 5);");
        } else {
            scaleTransition.setToX(1.0);
            scaleTransition.setToY(1.0);
            fadeTransition.setToValue(1.0);
            card.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-width: 2; " +
                        "-fx-border-radius: 8; -fx-background-radius: 8; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 2);");
        }
        
        ParallelTransition parallelTransition = new ParallelTransition(scaleTransition, fadeTransition);
        parallelTransition.play();
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
    
    private void selectProductWithAnimation(int index) {
        // Animation cho việc bỏ chọn card cũ
        if (selectedCard != null) {
            animateCardDeselection(selectedCard);
        }
        
        // Animation cho việc chọn card mới
        selectedCard = productCards[index];
        animateCardSelection(selectedCard);
        
        // Animation cho panel thông tin với delay
        animateProductInfoUpdate(index);
    }
    
    private void animateCardDeselection(VBox card) {
        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(150), card);
        scaleOut.setToX(1.0);
        scaleOut.setToY(1.0);
        
        scaleOut.setOnFinished(e -> {
            card.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-width: 2; " +
                        "-fx-border-radius: 8; -fx-background-radius: 8; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 2);");
        });
        
        scaleOut.play();
    }
    
    private void animateCardSelection(VBox card) {
        // Scale animation
        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(300), card);
        scaleIn.setFromX(1.0);
        scaleIn.setFromY(1.0);
        scaleIn.setToX(1.08);
        scaleIn.setToY(1.08);
        
        // Bounce effect
        scaleIn.setAutoReverse(true);
        scaleIn.setCycleCount(2);
        
        scaleIn.setOnFinished(e -> {
            card.setStyle("-fx-background-color: #e3f2fd; -fx-border-color: #2196f3; -fx-border-width: 3; " +
                        "-fx-border-radius: 8; -fx-background-radius: 8; -fx-effect: dropshadow(three-pass-box, rgba(33,150,243,0.4), 15, 0, 0, 6);");
        });
        
        scaleIn.play();
    }
    
    private void animateProductInfoUpdate(int index) {
        // Fade out current info
        FadeTransition fadeOut = new FadeTransition(Duration.millis(200), productDetailPanel);
        fadeOut.setToValue(0.3);
        
        // Slide effect
        TranslateTransition slideOut = new TranslateTransition(Duration.millis(200), productDetailPanel);
        slideOut.setToX(20);
        
        ParallelTransition hideTransition = new ParallelTransition(fadeOut, slideOut);
        
        hideTransition.setOnFinished(e -> {
            // Update product info
            updateProductInfo(index);
            
            // Fade in new info
            FadeTransition fadeIn = new FadeTransition(Duration.millis(300), productDetailPanel);
            fadeIn.setToValue(1.0);
            
            // Slide back
            TranslateTransition slideIn = new TranslateTransition(Duration.millis(300), productDetailPanel);
            slideIn.setToX(0);
            
            // Scale effect for the image
            ScaleTransition imageScale = new ScaleTransition(Duration.millis(400), selectedProductImage);
            imageScale.setFromX(0.8);
            imageScale.setFromY(0.8);
            imageScale.setToX(1.0);
            imageScale.setToY(1.0);
            
            ParallelTransition showTransition = new ParallelTransition(fadeIn, slideIn, imageScale);
            showTransition.play();
        });
        
        hideTransition.play();
    }
    
    private void updateProductInfo(int index) {
        Product selectedProduct = products.get(index);
        productNameLabel.setText(selectedProduct.name);
        productBrandLabel.setText(selectedProduct.brand);
        productPriceLabel.setText("$" + selectedProduct.price);
        productDescLabel.setText(selectedProduct.description);
        // Load ảnh từ backend
        try {
            Image image = new Image("http://localhost:8080/" + selectedProduct.image, true);
            selectedProductImage.setImage(image);
        } catch (Exception e) {
            System.out.println("Không thể tải hình ảnh: " + selectedProduct.image);
        }
    }
    
    // Deprecated method - keeping for compatibility
    private void selectProduct(int index) {
        selectProductWithAnimation(index);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
