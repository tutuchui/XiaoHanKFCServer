package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, String> {
    @Query(value = "select count(*) from product_order", nativeQuery = true)
    public int getOrderCount();

    @Query(value = "select po from ProductOrder po where po.customerId = :customerId")
    public List<ProductOrder> selectProductOrderByCustomer(@Param("customerId") String phone);

    @Modifying
    @Transactional
    @Query(value = "update product_order set payment_status =:payment_status where order_id =:order_id ", nativeQuery = true)
    public int updatePaymentStatus(@Param("payment_status") int paymentStatus, @Param("order_id") String orderId);
}
