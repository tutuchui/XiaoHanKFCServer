package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.ProductOrder;
import com.conehanor.kfcserver.entity.ProductOrderDetail;
import com.conehanor.kfcserver.entity.ProductOrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductOrderDetailRepository extends JpaRepository<ProductOrderDetail, ProductOrderDetailId> {
    @Query(value = "select pod from ProductOrderDetail pod where pod.orderId = :orderId")
    List<ProductOrderDetail> selectProductOrderByOrderId(@Param("orderId") String orderId);
}
