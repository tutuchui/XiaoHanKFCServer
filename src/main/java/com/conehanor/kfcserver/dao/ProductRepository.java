package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {


    @Query(value = "select new com.conehanor.kfcserver.entity.Product(id,name,price,imageUrl,category,introduction) from Product where category= :category")
    List<Product> selectByCategory(@Param("category") String category);


}
