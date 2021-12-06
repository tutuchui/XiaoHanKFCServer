package com.conehanor.kfcserver.model;

public class IngredientForProduct {
//    原料名称	原料厂家	原料价格	所需数量
    private int ingredientId;
    private String ingredientName;
    private String merchant;
    private double price;
    private int count;
    private int remainCount;

    public IngredientForProduct(int ingredientId, String ingredientName, String merchant, double price, int count) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.merchant = merchant;
        this.price = price;
        this.count = count;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getRemainCount() {
        return remainCount;
    }

    public void setRemainCount(int remainCount) {
        this.remainCount = remainCount;
    }
}
