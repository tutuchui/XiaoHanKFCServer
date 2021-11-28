package com.conehanor.kfcserver.controller;

import com.conehanor.kfcserver.dao.ProductRepository;
import com.conehanor.kfcserver.entity.Product;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

    @Autowired
    Gson gson;

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("body") String body, @RequestParam("file") MultipartFile file) {
        Product product = gson.fromJson(body, Product.class);
        System.out.println(product.toString());
        Path root = Paths.get("images");
        String imageUrl = "";
        if (file != null) {
            try {
                Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
                imageUrl = "images" + File.separator + file.getOriginalFilename();
            } catch (Exception e) {
                System.out.println("Could not store the file. Error: " + e.getMessage());
                return new ResponseEntity<>(gson.toJson("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        product.setImageUrl(imageUrl);
        productRepository.saveAndFlush(product);
        return new ResponseEntity<>(gson.toJson("SUCCESS"), HttpStatus.OK);
    }

    @GetMapping("getAllProducts")
    public ResponseEntity<String> getAllProducts() {
        return new ResponseEntity<>(gson.toJson(productRepository.findAll()), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestParam("body") String body, @RequestParam(value = "file", required = false) MultipartFile file) {
        System.out.println(body);
        Product product = gson.fromJson(body, Product.class);
        System.out.println(product.toString());
        Path root = Paths.get("images");
        if (file != null) {
            try {
                Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
                String imageUrl = "images" + File.separator + file.getOriginalFilename();
                product.setImageUrl(imageUrl);

            } catch (Exception e) {
                System.out.println("Could not store the file. Error: " + e.getMessage());
                return new ResponseEntity<>(gson.toJson("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        productRepository.saveAndFlush(product);
        return new ResponseEntity<>(gson.toJson("SUCCESS"), HttpStatus.OK);
    }

    @GetMapping("/delete")
    public ResponseEntity<String> deleteProduct(@RequestParam("productId") int productId) {
        try {
            Optional<Product> product = productRepository.findById(productId);
            Path root = Paths.get(product.get().getImageUrl());
            Files.delete(root);
            productRepository.deleteById(productId);
            return new ResponseEntity<>(gson.toJson("SUCCESS"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(gson.toJson("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
