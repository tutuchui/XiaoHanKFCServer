package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.IngredientsList;
import com.conehanor.kfcserver.entity.IngredientsListPK;
import com.conehanor.kfcserver.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IngredientsListRepository extends JpaRepository<IngredientsList, IngredientsListPK> {
    @Query("select i from IngredientsList i where i.ingredientsId = :ingredientsId")
    List<IngredientsList> getProductInfoForIngredients(int ingredientsId);

    @Query("select i from IngredientsList i where i.productId = :productId")
    List<IngredientsList> getIngredientInfoForProduct(int productId);
}
