package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
