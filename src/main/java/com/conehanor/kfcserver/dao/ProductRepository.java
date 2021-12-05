package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select p from Product p where p.state = 1")
    List<Product> findAllValidProducts();

    @Query("update Product  p set p.state = :state where p.productId = :productId")
    @Transactional
    @Modifying
    int updateProductState(int state, int productId);
}
