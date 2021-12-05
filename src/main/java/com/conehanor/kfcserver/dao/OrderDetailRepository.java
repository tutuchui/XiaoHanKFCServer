package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.OrderDetail;
import com.conehanor.kfcserver.entity.OrderDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {
}
