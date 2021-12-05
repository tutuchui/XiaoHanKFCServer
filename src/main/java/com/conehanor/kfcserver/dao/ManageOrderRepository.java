package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.ManageOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManageOrderRepository extends JpaRepository<ManageOrder, Integer> {
}
