package com.conehanor.kfcserver.dao;
import com.conehanor.kfcserver.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductOrderDetailRepository extends JpaRepository<ProductOrderDetail, String>{
}
