package com.conehanor.kfcserver.controller;

import com.conehanor.kfcserver.dao.*;
import com.conehanor.kfcserver.entity.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/getAllOrder")
    @CrossOrigin
    public String getAllOrder() {
        return new Gson().toJson(1);
    }
}
