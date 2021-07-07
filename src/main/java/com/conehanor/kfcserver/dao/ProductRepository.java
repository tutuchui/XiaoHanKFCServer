package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    @Query(value = "select * from product where category= :category", nativeQuery = true)
    List<Product> selectByCategory(@Param("category") String category);
}
