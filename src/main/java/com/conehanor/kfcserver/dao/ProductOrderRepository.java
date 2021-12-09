package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer> {
    @Query("select count(po) from ProductOrder po order by po.productOrderId desc")
    int generateOrderId();

    @Query("select po from ProductOrder po where po.customerId = :customerId order by po.orderDate desc")
    List<ProductOrder> getOrdersByCustomerId(int customerId);

    @Query("select count(o) from ProductOrder o")
    int getProductOrderCount();

    @Query("select count(o) from ProductOrder o where o.orderDate between :start and :end")
    int getOrderCountByDate(Date start, Date end);

    @Query("select count(o) from ProductOrder o where o.price >= :min and o.price < :max")
    int getOrderCountByPriceInterval(double min, double max);
}
