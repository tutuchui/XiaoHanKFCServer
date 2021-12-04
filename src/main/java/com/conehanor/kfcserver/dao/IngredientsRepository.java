package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.Ingredients;
import com.conehanor.kfcserver.model.IngredientForProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IngredientsRepository extends JpaRepository<Ingredients, Integer> {
    @Query("select new com.conehanor.kfcserver.model.IngredientForProduct(a.ingredientsId, a.name, a.merchant, a.price, b.ingredientsNumber) from Ingredients a, IngredientsList  b where b.productId = :productId and a.ingredientsId = b.ingredientsId")
    List<IngredientForProduct> selectIngredientForProduct(int productId);
}
