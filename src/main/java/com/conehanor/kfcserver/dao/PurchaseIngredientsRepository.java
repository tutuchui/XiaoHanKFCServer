package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.PurchaseIngredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PurchaseIngredientsRepository extends JpaRepository<PurchaseIngredients, Integer> {
    @Query("select sum(i.number) from PurchaseIngredients i where i.ingredientsId = :ingredientsId group by i.ingredientsId")
    int getTotalPurchaseIngredients(int ingredientsId);
}
