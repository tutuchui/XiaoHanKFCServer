package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer> {
    @Query("select po from ProductOrder po order by po.productOrderId")
    ProductOrder findFirstByProductOrderId();

    @Query("select po from ProductOrder po where po.customerId = :customerId order by po.orderDate desc")
    List<ProductOrder> getOrdersByCustomerId(int customerId);
}
