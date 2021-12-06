package com.conehanor.kfcserver.controller;

import com.conehanor.kfcserver.dao.IngredientsListRepository;
import com.conehanor.kfcserver.dao.IngredientsRepository;
import com.conehanor.kfcserver.dao.ProductRepository;
import com.conehanor.kfcserver.entity.Ingredients;
import com.conehanor.kfcserver.entity.IngredientsList;
import com.conehanor.kfcserver.entity.IngredientsListPK;
import com.conehanor.kfcserver.entity.Product;
import com.conehanor.kfcserver.model.IngredientForProduct;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

    @Autowired
    Gson gson;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    IngredientsRepository ingredientsRepository;

    @Autowired
    IngredientsListRepository ingredientsListRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("body") String body, @RequestParam("file") MultipartFile file) {
        Product product = gson.fromJson(body, Product.class);
        System.out.println(product.toString());
        Path root = Paths.get("images");
        String imageUrl = "";
        if (file != null) {
            try {
                String fileName = file.getOriginalFilename();
                int index = 1;
                while(Files.exists(Paths.get("images", fileName))){
                    fileName = file.getOriginalFilename().split("\\.")[0] + index + "." + file.getOriginalFilename().split("\\.")[1];
                    index++;
                }
                Files.copy(file.getInputStream(), root.resolve(fileName));
                imageUrl = "images" + File.separator + fileName;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Could not store the file. Error: " + e.getMessage());
                return new ResponseEntity<>(gson.toJson("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        product.setImageUrl(imageUrl);
        productRepository.saveAndFlush(product);
        return new ResponseEntity<>(gson.toJson("SUCCESS"), HttpStatus.OK);
    }

    @GetMapping("/getAllValidProducts")
    public ResponseEntity<String> getAllValidProducts() {
        return new ResponseEntity<>(gson.toJson(productRepository.findAllValidProducts()), HttpStatus.OK);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<String> getAllProducts() {
        return new ResponseEntity<>(gson.toJson(productRepository.findAll()), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestParam("body") String body, @RequestParam(value = "file", required = false) MultipartFile file) {
        Product product = gson.fromJson(body, Product.class);
        Path root = Paths.get("images");
        if (file != null) {
            try {
                String fileName = file.getOriginalFilename();
                int index = 1;
                while(Files.exists(Paths.get("images", fileName))){
                    fileName = file.getOriginalFilename().split("\\.")[0] + index + "." + file.getOriginalFilename().split("\\.")[1];
                    index++;
                }
                Files.copy(file.getInputStream(), root.resolve(fileName));
                String imageUrl = "images" + File.separator + fileName;
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

    @GetMapping("/getAllIngredients")
    public ResponseEntity<String> getAllIngredients(){
        List<Ingredients> ingredientsList = ingredientsRepository.findAll();
        return new ResponseEntity<>(gson.toJson(ingredientsList), HttpStatus.OK);
    }

    @PostMapping("/addIngredientForProduct")
    public ResponseEntity<String>addIngredientForProduct(@RequestBody String body){
        IngredientsList ingredientsList = gson.fromJson(body, IngredientsList.class);
        ingredientsListRepository.saveAndFlush(ingredientsList);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @GetMapping("/getIngredientsForProduct")
    public ResponseEntity<String> getIngredientsForProduct(@RequestParam("productId") int productId){
        List<IngredientForProduct> ingredientForProductList = ingredientsRepository.selectIngredientForProduct(productId);
        return new ResponseEntity<>(gson.toJson(ingredientForProductList), HttpStatus.OK);
    }

    @PostMapping("/deleteIngredientForProduct")
    public  ResponseEntity<String> deleteIngredientForProduct(@RequestBody String body){
        IngredientsListPK ingredientsListPK = gson.fromJson(body, IngredientsListPK.class);
        ingredientsListRepository.deleteById(ingredientsListPK);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @GetMapping("/updateProductState")
    public ResponseEntity<String> updateProductState(@RequestParam("productId") int productId, @RequestParam("state") int state){
        try{
            if(state == 0){
                productRepository.updateProductState(1, productId);
                return new ResponseEntity<>(gson.toJson(1), HttpStatus.OK);

            }else{
                productRepository.updateProductState(0, productId);
                return new ResponseEntity<>(gson.toJson(0), HttpStatus.OK);

            }
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/addIngredient")
    public ResponseEntity<String>addIngredient(@RequestBody String body){
        Ingredients ingredients = gson.fromJson(body, Ingredients.class);
        ingredientsRepository.saveAndFlush(ingredients);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

}
