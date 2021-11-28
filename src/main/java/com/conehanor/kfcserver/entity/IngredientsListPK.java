package com.conehanor.kfcserver.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class IngredientsListPK implements Serializable {
    private int ingredientsId;
    private int productId;

    @Column(name = "ingredients_id")
    @Id
    public int getIngredientsId() {
        return ingredientsId;
    }

    public void setIngredientsId(int ingredientsId) {
        this.ingredientsId = ingredientsId;
    }

    @Column(name = "product_id")
    @Id
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientsListPK that = (IngredientsListPK) o;
        return ingredientsId == that.ingredientsId && productId == that.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientsId, productId);
    }
}
