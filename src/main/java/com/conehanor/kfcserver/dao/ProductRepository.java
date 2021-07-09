package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.NamePair;
import com.conehanor.kfcserver.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.naming.Name;
import java.util.List;
import java.util.Map;

public interface ProductRepository extends JpaRepository<Product, String> {


    @Query(value = "select new com.conehanor.kfcserver.entity.Product(id,name,price,imageUrl,category,introduction) from Product where category= :category")
    List<Product> selectByCategory(@Param("category") String category);

    @Query(nativeQuery = false, value = "select new com.conehanor.kfcserver.entity.NamePair(p.name, e.name) from Product p, Employee e where e.id= :id")
    List<NamePair> selectNames(@Param("id") int id);

}
