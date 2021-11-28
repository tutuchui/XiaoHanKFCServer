package com.conehanor.kfcserver.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "purchase_ingredients", schema = "han_fc", catalog = "")
public class PurchaseIngredients {
    private int purchaseIngredientsId;
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
        return purchaseIngredientsId == that.purchaseIngredientsId && Objects.equals(number, that.number) && Objects.equals(purchaseTime, that.purchaseTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseIngredientsId, number, purchaseTime);
    }
}
