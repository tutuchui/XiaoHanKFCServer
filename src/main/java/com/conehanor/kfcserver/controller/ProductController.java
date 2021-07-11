package com.conehanor.kfcserver.controller;

import com.conehanor.kfcserver.dao.ProductRepository;
import com.conehanor.kfcserver.entity.OrderStatus;
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
public class ProductController {
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
    public ResponseEntity<String> saveProduct(@RequestParam(value = "imageFile", required = false)MultipartFile file,
                                      @RequestParam("productName")String productName,
                                      @RequestParam("productPrice") double productPrice,
                                      @RequestParam("productIntroduction") String productIntroduction, @RequestParam("productCategory") String productCategory){
        String fileName = "null";
        if(file != null){
            fileName = file.getOriginalFilename();
            String imageUrl = imagePath + File.separator + fileName;
            System.out.println(imageUrl);
            try{

                file.transferTo(new File(imageUrl));
            }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.status(500).body("Error");
            }
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


    @PostMapping("/updateProduct")
    @CrossOrigin
    public ResponseEntity<String> updateProduct(@RequestParam(value = "imageFile", required = false)MultipartFile file,
                                                @RequestParam("productName")String productName,
                                                @RequestParam("productPrice") double productPrice,
                                                @RequestParam("productIntroduction") String productIntroduction,
                                                @RequestParam("productCategory") String productCategory,
                                                @RequestParam("productId") String  productId)
    {
        int result = -1;
        if(file != null) {
            String fileName = file.getOriginalFilename();
            String imageUrl = imagePath + File.separator + fileName;
            System.out.println(imageUrl);
            try {
                file.transferTo(new File(imageUrl));
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(500).body("Error");
            }
             result = productRepository.updateProductById(productName, imageUrl, productCategory, productPrice, productIntroduction, productId);
        }else{
            System.out.println(productIntroduction);
            System.out.println(productId);
            result = productRepository.updateProductWithoutImageById(productName, productCategory, productPrice, productIntroduction, productId);
        }


        if(result >= 0){
            return ResponseEntity.status(200).body("Success");
        }
        return ResponseEntity.status(501).body("Error");
    }
}
