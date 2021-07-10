package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, String> {
    @Query(value = "select count(*) from product_order", nativeQuery = true)
    public int getOrderCount();

    @Query(value = "select po from ProductOrder po where po.customerId = :customerId")
    public List<ProductOrder> selectProductOrderByCustomer(@Param("customerId") String phone);
}
