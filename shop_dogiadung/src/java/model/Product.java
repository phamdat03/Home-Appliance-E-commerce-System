package model;

import java.util.Date;

public class Product {
    private int productId;
    private int categoryId;
    private String name;
    private String description;
    private double price;
    private int stock; // số lượng tồn kho
    private String imageUrl;
    private Date createdAt;

    // số lượng trong giỏ
    private int quantity;

    public Product() {}

    public Product(int productId, int categoryId, String name, String description, double price, int stock, String imageUrl, Date createdAt) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.quantity = 0;
    }

    // Copy constructor để thêm vào giỏ
    public Product(Product p) {
        this.productId = p.productId;
        this.categoryId = p.categoryId;
        this.name = p.name;
        this.description = p.description;
        this.price = p.price;
        this.stock = p.stock;
        this.imageUrl = p.imageUrl;
        this.createdAt = p.createdAt;
        this.quantity = 0; // mặc định khi thêm vào giỏ
    }

    // Getters & Setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
