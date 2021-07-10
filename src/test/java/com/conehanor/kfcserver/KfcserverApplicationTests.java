package com.conehanor.kfcserver;

import com.conehanor.kfcserver.dao.ProductRepository;
import com.conehanor.kfcserver.entity.Product;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KfcserverApplicationTests {

    @Autowired
    ProductRepository productRepository;

    @Test
    void contextLoads() {
        Product product = new Product();
        product.setPrice(100);
        product.setIntroduction("哈哈哈哈哈哈");
        product.setName("舒适");
        product.setCategory("1");
        product.setImageUrl("1");

        productRepository.saveAndFlush(product);
    }


}
