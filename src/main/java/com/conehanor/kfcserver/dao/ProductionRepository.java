package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductionRepository extends JpaRepository<Production, Integer> {
    @Query("select sum(p.number) as sum from Production p where p.productId = :productId group by p.productId")
    public Integer getProductionCountForProduct(int productId);
}
