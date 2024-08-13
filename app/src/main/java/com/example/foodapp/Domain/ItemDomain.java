package com.example.foodapp.Domain;

public class ItemDomain {
    private String imgPath;
    private String title;
    private double price;
    private double description;
    private double rate;

    public ItemDomain(String imgPath, String title, double price, double description, double rate) {
        this.imgPath = imgPath;
        this.title = title;
        this.price = price;
        this.description = description;
        this.rate = rate;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDescription() {
        return description;
    }

    public void setDescription(double description) {
        this.description = description;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
