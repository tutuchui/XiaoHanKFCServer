package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer> {
    @Query("select count(po) from ProductOrder po")
    int getOrderCount();
}
