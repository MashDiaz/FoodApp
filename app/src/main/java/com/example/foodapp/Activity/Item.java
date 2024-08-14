package com.example.foodapp.Activity;

public class Item {
    private int foodId;
    private String foodName;
    private double price;
    private String category;
    private byte[] image;
    private String description;

    public Item(int foodId, String foodName, double price, String category, byte[] image, String description) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.price = price;
        this.category = category;
        this.image = image;
        this.description = description;
    }

    // Getters and Setters
    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
