package com.conehanor.kfcserver.controller;

import com.conehanor.kfcserver.dao.*;
import com.conehanor.kfcserver.entity.*;
import com.conehanor.kfcserver.model.IngredientForAdmin;
import com.conehanor.kfcserver.model.IngredientForProduct;
import com.conehanor.kfcserver.model.ProductForAdmin;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
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

    @Autowired
    PurchaseIngredientsRepository purchaseIngredientsRepository;

    @Autowired
    ProductionRepository productionRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

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
        List<Product> productList = productRepository.findAll();
        List<ProductForAdmin> productForAdminList = new ArrayList<>();
        for(Product product: productList){
            ProductForAdmin productForAdmin = new ProductForAdmin();
            productForAdmin.setProductId(product.getProductId());
            productForAdmin.setPrice(product.getPrice());
            productForAdmin.setState(product.getState());
            productForAdmin.setImageUrl(product.getImageUrl());
            productForAdmin.setCategory(product.getCategory());
            productForAdmin.setIntroduction(product.getIntroduction());
            productForAdmin.setName(product.getName());
            productForAdmin.setRemainCount(getRemainCountForProduct(product.getProductId()));
            productForAdminList.add(productForAdmin);
        }
        return new ResponseEntity<>(gson.toJson(productForAdminList), HttpStatus.OK);
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
        List<IngredientForAdmin> ingredientForAdminList = new ArrayList<>();
        for(Ingredients ingredients: ingredientsList){
            IngredientForAdmin ingredientForAdmin = new IngredientForAdmin();
            ingredientForAdmin.setIngredientsId(ingredients.getIngredientsId());
            ingredientForAdmin.setCategory(ingredients.getCategory());
            ingredientForAdmin.setMerchant(ingredients.getMerchant());
            ingredientForAdmin.setPrice(ingredients.getPrice());
            ingredientForAdmin.setName(ingredients.getName());
            ingredientForAdmin.setIntroduction(ingredients.getIntroduction());
            //calculate remain number
            int remainCountForIngredient = getRemainCountForIngredients(ingredients.getIngredientsId());

            ingredientForAdmin.setNumber(remainCountForIngredient);
            ingredientForAdminList.add(ingredientForAdmin);
        }
        return new ResponseEntity<>(gson.toJson(ingredientForAdminList), HttpStatus.OK);
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

    @PostMapping("/purchaseIngredients")
    public ResponseEntity<String> purchaseIngredients(@RequestBody String body){
        JsonObject jsonObject = gson.fromJson(body, JsonObject.class);
        int ingredientsId = jsonObject.get("ingredientsId").getAsInt();
        int number = jsonObject.get("number").getAsInt();
        PurchaseIngredients purchaseIngredients = new PurchaseIngredients();
        purchaseIngredients.setIngredientsId(ingredientsId);
        purchaseIngredients.setNumber(number);
        purchaseIngredients.setAdminId(1);
        purchaseIngredients.setPurchaseTime(new Timestamp(System.currentTimeMillis()));
        purchaseIngredientsRepository.saveAndFlush(purchaseIngredients);
        return new ResponseEntity<>(gson.toJson("SUCCESSS"), HttpStatus.OK);
    }

    @GetMapping("/getIngredientsForProductTable")
    public ResponseEntity<String> getIngredientsForProductTable(@RequestParam int productId){
        List<IngredientForProduct> ingredientForProductList = ingredientsRepository.selectIngredientForProduct(productId);
        for(IngredientForProduct ingredientForProduct : ingredientForProductList){
            ingredientForProduct.setRemainCount(getRemainCountForIngredients(ingredientForProduct.getIngredientId()));
        }
        return new ResponseEntity<>(gson.toJson(ingredientForProductList),HttpStatus.OK);
    }

    @PostMapping("/makeProduct")
    public ResponseEntity<String> makeProduct(@RequestBody String body){
        JsonObject jsonObject = gson.fromJson(body, JsonObject.class);
        int productId = jsonObject.get("productId").getAsInt();
        int number = jsonObject.get("number").getAsInt();

        List<IngredientsList> ingredientsInfoList = ingredientsListRepository.getIngredientInfoForProduct(productId);
        for(IngredientsList curIngredientInfo: ingredientsInfoList){
            int remainCount = getRemainCountForIngredients(curIngredientInfo.getIngredientsId());
            int requiredCount = curIngredientInfo.getIngredientsNumber() * number;
            if(remainCount < requiredCount){
                return new ResponseEntity<>(gson.toJson("Ingredients not enough"), HttpStatus.NOT_IMPLEMENTED);
            }
        }
        Production production = new Production();
        production.setProductionTime(new Timestamp(System.currentTimeMillis()));
        production.setProductId(productId);
        production.setAdminId(1);
        production.setNumber(number);
        productionRepository.saveAndFlush(production);
        return new ResponseEntity<>(gson.toJson("SUCCESS"), HttpStatus.OK);
    }


    //计算原料库存
    private int getRemainCountForIngredients(int ingredientsId){
        Integer totalCount = purchaseIngredientsRepository.getTotalPurchaseIngredients(ingredientsId);
        if(totalCount == null){
            totalCount = 0;
        }
        int usedCount = 0;
        List<IngredientsList> productInfoForCurIngredients = ingredientsListRepository.getProductInfoForIngredients(ingredientsId);
        for(IngredientsList product : productInfoForCurIngredients){
            Integer productionCount = productionRepository.getProductionCountForProduct(product.getProductId());
            if(productionCount == null){
                productionCount = 0;
            }
            usedCount += productionCount * product.getIngredientsNumber();
        }

        return totalCount - usedCount;
    }

    private int getRemainCountForProduct(int productId){
        Integer productionCount = productionRepository.getProductionCountForProduct(productId); //null
        productionCount = productionCount == null ? 0 : productionCount;
        Integer orderCount = orderDetailRepository.getOrderCountForProduct(productId);
        orderCount = orderCount == null ? 0 : orderCount;
        return productionCount - orderCount;
    }

    @GetMapping("/getPurchaseIngredientsRecordById")
    public ResponseEntity<String> getPurchaseIngredientsRecordById(@RequestParam("ingredientsId") int ingredientsId){
        List<PurchaseIngredients> purchaseIngredients =  purchaseIngredientsRepository.getPurchaseIngredientsRecordById(ingredientsId);
        return new ResponseEntity<>(gson.toJson(purchaseIngredients), HttpStatus.OK);
    }

}
