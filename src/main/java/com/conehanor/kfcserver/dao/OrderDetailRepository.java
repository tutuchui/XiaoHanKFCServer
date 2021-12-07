package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.OrderDetail;
import com.conehanor.kfcserver.entity.OrderDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {
    @Query("select sum(o.number) from OrderDetail o where o.productId = :productId group by o.productId")
    Integer getOrderCountForProduct(int productId);

    @Query("select o from OrderDetail o where o.productOrderId = :orderId")
    List<OrderDetail> getOrderDetailByOrderId(int orderId);
}
