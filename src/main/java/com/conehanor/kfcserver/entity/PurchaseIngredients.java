package com.conehanor.kfcserver.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "purchase_ingredients", schema = "han_fc", catalog = "")
public class PurchaseIngredients {
    private int purchaseIngredientsId;
    private int ingredientsId;
    private int adminId;
    private int number;
    private Timestamp purchaseTime;

    @Id
    @Column(name = "purchase_ingredients_id")
    public int getPurchaseIngredientsId() {
        return purchaseIngredientsId;
    }

    public void setPurchaseIngredientsId(int purchaseIngredientsId) {
        this.purchaseIngredientsId = purchaseIngredientsId;
    }

    @Column(name = "ingredients_id")
    public int getIngredientsId() {
        return ingredientsId;
    }

    public void setIngredientsId(int ingredientsId) {
        this.ingredientsId = ingredientsId;
    }

    @Column(name = "admin_id")
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Basic
    @Column(name = "number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Basic
    @Column(name = "purchase_time")
    public Timestamp getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Timestamp purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseIngredients that = (PurchaseIngredients) o;
        return purchaseIngredientsId == that.purchaseIngredientsId && number == that.number && Objects.equals(purchaseTime, that.purchaseTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseIngredientsId, number, purchaseTime);
    }
}
