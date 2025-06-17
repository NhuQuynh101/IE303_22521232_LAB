package com.example.shoestore.model;

public class Product {
    private String name;
    private String brand;
    private String description;
    private String image;
    private double price;

    public Product(String name, String brand, String description, String image, double price) {
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public Product() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
} 