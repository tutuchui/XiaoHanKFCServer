package com.conehanor.kfcserver;

import com.conehanor.kfcserver.dao.ProductRepository;
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
        System.out.println(new Gson().toJson(productRepository.selectNames(1)));
    }

}
