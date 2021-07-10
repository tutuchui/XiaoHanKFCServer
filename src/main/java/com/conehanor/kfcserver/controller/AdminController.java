package com.conehanor.kfcserver.controller;

import com.conehanor.kfcserver.dao.*;
import com.conehanor.kfcserver.entity.*;
import com.conehanor.kfcserver.util.CustomEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
public class AdminController {

    @Autowired
    AdminRepository adminRepository;

    @PostMapping("/admin_login")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestParam("number") String number, @RequestParam("password") String password) {
        try {
            System.out.println("lalalalala");
            Admin admin = adminRepository.selectBynumber(number);
            if (admin.getPassword() == null) {
                return ResponseEntity.status(501).body("Customer Not Exists");
            }
            if (password.equals(admin.getPassword())) {
                return ResponseEntity.status(200).body(admin.getName());
            } else {
                return ResponseEntity.status(502).body("Incorrect Password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("error");
        }

    }
}
