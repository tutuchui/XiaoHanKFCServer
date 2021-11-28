package com.conehanor.kfcserver.controller;

import com.conehanor.kfcserver.dao.AdminRepository;
import com.conehanor.kfcserver.entity.Admin;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    Gson gson;

    @Autowired
    AdminRepository adminRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody String body){
        Admin admin = gson.fromJson(body, Admin.class);
        Admin targetAdmin = adminRepository.findByNumber(admin.getNumber());
        if(targetAdmin != null && admin.getPassword().equals(targetAdmin.getPassword())){
            return new ResponseEntity<>(gson.toJson(targetAdmin.getName()), HttpStatus.OK);
        }else if(targetAdmin == null){
            return new ResponseEntity<>(gson.toJson("ERROR"), HttpStatus.NOT_IMPLEMENTED);
        }else if(!targetAdmin.getPassword().equals(admin.getPassword())){
            return new ResponseEntity<>(gson.toJson("ERROR"), HttpStatus.BAD_GATEWAY);
        }else {
            return new ResponseEntity<>(gson.toJson("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
