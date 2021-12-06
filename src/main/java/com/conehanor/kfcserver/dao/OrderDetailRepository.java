package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.OrderDetail;
import com.conehanor.kfcserver.entity.OrderDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {
    @Query("select sum(o.number) from OrderDetail o where o.productId = :productId group by o.productId")
    Integer getOrderCountForProduct(int productId);
}
