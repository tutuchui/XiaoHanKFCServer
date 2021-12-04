package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.IngredientsList;
import com.conehanor.kfcserver.entity.IngredientsListPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsListRepository extends JpaRepository<IngredientsList, IngredientsListPK> {
}
