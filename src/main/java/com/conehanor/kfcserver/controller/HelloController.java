package com.conehanor.kfcserver.controller;

import com.conehanor.kfcserver.dao.ProductRepository;
import com.conehanor.kfcserver.entity.Product;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.List;

@RestController
public class HelloController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ServletContext servletContext;

    @Value("${imagePath}")
    private String imagePath;

    @GetMapping("/")
    public String getHello(){
        return "Hello 小王";
    }

    @PostMapping("/uploadProduct")
    @CrossOrigin
    public ResponseEntity<String> saveProduct(@RequestParam("imageFile")MultipartFile file,
                                      @RequestParam("productName")String productName,
                                      @RequestParam("productPrice") double productPrice,
                                      @RequestParam("productIntroduction") String productIntroduction,
                                      @RequestParam("productCategory") String productCategory){

        String fileName = file.getOriginalFilename();
        String imageUrl = imagePath + File.separator + fileName;
        System.out.println(imageUrl);
        try{

            file.transferTo(new File(imageUrl));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error");
        }
        Product product = new Product();
        product.setName(productName);
        product.setPrice(productPrice);
        product.setIntroduction(productIntroduction);
        product.setCategory(productCategory);
        product.setImageUrl("images/food" + File.separator + fileName);
        productRepository.saveAndFlush(product);
        return ResponseEntity.status(200).body("Success");
    }

    @GetMapping("/getAllProducts")
    @CrossOrigin
    public String getAllProduct(){
        List<Product> productList = productRepository.findAll();
        return new Gson().toJson(productList);
    }

    @GetMapping("/getProductByCategory")
    public String getProductByCategory(@RequestParam("category")String productCategory) {
        System.out.println(productCategory);
        List<Product> productList = productRepository.selectByCategory(productCategory);
        return new Gson().toJson(productList);
    }
}
