package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {


    @Query(value = "select new com.conehanor.kfcserver.entity.Product(id,name,price,imageUrl,category,introduction) from Product where category= :category")
    List<Product> selectByCategory(@Param("category") String category);

    @Transactional
    @Modifying
    @Query(value = "update Product p set p.name=:name, p.imageUrl=:imageUrl, p.category=:category, p.price=:price, p.introduction =:introduction where p.id =:id")
    int updateProductById(@Param("name")String name, @Param("imageUrl") String iamgeUrl, @Param("category")String category, @Param("price") double price, @Param("introduction")String introduction, @Param("id") String productId);

    @Transactional
    @Modifying
    @Query(value = "update Product p set p.name=:name, p.category=:category, p.price=:price, p.introduction =:introduction where p.id =:id")
    int updateProductWithoutImageById(@Param("name")String name, @Param("category")String category, @Param("price") double price, @Param("introduction")String introduction, @Param("id") String productId);


}
