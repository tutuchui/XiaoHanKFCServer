package com.conehanor.kfcserver.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ingredients_list", schema = "han_fc", catalog = "")
@IdClass(IngredientsListPK.class)
public class IngredientsList {
    private int ingredientsId;
    private int productId;
    private Integer ingredientsNumber;

    @Id
    @Column(name = "ingredients_id")
    public int getIngredientsId() {
        return ingredientsId;
    }

    public void setIngredientsId(int ingredientsId) {
        this.ingredientsId = ingredientsId;
    }

    @Id
    @Column(name = "product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "ingredients_number")
    public Integer getIngredientsNumber() {
        return ingredientsNumber;
    }

    public void setIngredientsNumber(Integer ingredientsNumber) {
        this.ingredientsNumber = ingredientsNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientsList that = (IngredientsList) o;
        return ingredientsId == that.ingredientsId && productId == that.productId && Objects.equals(ingredientsNumber, that.ingredientsNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientsId, productId, ingredientsNumber);
    }
}
