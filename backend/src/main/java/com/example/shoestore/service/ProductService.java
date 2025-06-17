package com.example.shoestore.service;

import com.example.shoestore.model.Product;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();

    @PostConstruct
    public void init() {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("product-info.txt");
            if (is != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = reader.readLine()) != null) {
                    // name|brand|description|image|price
                    String[] parts = line.split("\\|");
                    if (parts.length == 5) {
                        products.add(new Product(parts[0], parts[1], parts[2], parts[3], Double.parseDouble(parts[4])));
                    }
                }
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts() {
        return products;
    }
} 