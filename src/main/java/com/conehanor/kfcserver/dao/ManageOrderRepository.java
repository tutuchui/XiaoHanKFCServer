package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.ManageOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ManageOrderRepository extends JpaRepository<ManageOrder, Integer> {

    @Query("select m from ManageOrder m where m.productOrderId = :productOrderId order by m.manageTime desc")
    List<ManageOrder> findAllByOrderId(int productOrderId);

}
