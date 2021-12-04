package com.conehanor.kfcserver.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Ingredients {
    private int ingredientsId;
    private String name;
    private double price;
    private String merchant;
    private String category;
    private String introduction;

    @Id
    @Column(name = "ingredients_id")
    public int getIngredientsId() {
        return ingredientsId;
    }

    public void setIngredientsId(int ingredientsId) {
        this.ingredientsId = ingredientsId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "merchant")
    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    @Basic
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "introduction")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredients that = (Ingredients) o;
        return ingredientsId == that.ingredientsId && price == that.price && Objects.equals(name, that.name) && Objects.equals(merchant, that.merchant) && Objects.equals(category, that.category) && Objects.equals(introduction, that.introduction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientsId, name, price, merchant, category, introduction);
    }
}
