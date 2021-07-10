package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.OrderStatus;
import com.conehanor.kfcserver.entity.OrderStatusId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, OrderStatusId> {

    @Query("select os from OrderStatus os where os.orderId=:orderId")
    List<OrderStatus> selectOrderStatusById(@Param("orderId") String orderId);
}
