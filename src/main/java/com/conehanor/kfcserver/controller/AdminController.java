package com.conehanor.kfcserver.controller;

import com.conehanor.kfcserver.dao.*;
import com.conehanor.kfcserver.entity.Admin;
import com.conehanor.kfcserver.entity.Ingredients;
import com.conehanor.kfcserver.entity.Product;
import com.conehanor.kfcserver.model.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    Gson gson;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    SuggestionRepository suggestionRepository;

    @Autowired
    ProductOrderRepository productOrderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ProductionRepository productionRepository;

    @Autowired
    IngredientsRepository ingredientsRepository;

    @Autowired
    PurchaseIngredientsRepository purchaseIngredientsRepository;

    @Autowired
    FeedbackRepository feedbackRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody String body) {
        Admin admin = gson.fromJson(body, Admin.class);
        Admin targetAdmin = adminRepository.findByNumber(admin.getNumber());
        if (targetAdmin != null && admin.getPassword().equals(targetAdmin.getPassword())) {
            return new ResponseEntity<>(gson.toJson(targetAdmin.getName()), HttpStatus.OK);
        } else if (targetAdmin == null) {
            return new ResponseEntity<>(gson.toJson("ERROR"), HttpStatus.NOT_IMPLEMENTED);
        } else if (!targetAdmin.getPassword().equals(admin.getPassword())) {
            return new ResponseEntity<>(gson.toJson("ERROR"), HttpStatus.BAD_GATEWAY);
        } else {
            return new ResponseEntity<>(gson.toJson("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getStatics")
    public ResponseEntity<String> getEmployeeData() {
        int curEmployee = employeeRepository.getCurrentEmployeeCount(0);
        int recruitEmployeeCurMonth = employeeRepository.getEmployeeRecruitCurMonth();
        int fireEmployeeCurMonth = employeeRepository.getEmployeeFireCurMonth();
        int customerCount = customerRepository.getCustomerCount();
        int orderCount = productOrderRepository.getProductOrderCount();
        int suggestionCount = suggestionRepository.getSuggestionCount();
        HanfcStatics hanfcStatics = new HanfcStatics();
        hanfcStatics.setCurEmployee(curEmployee);
        hanfcStatics.setRecruitEmployeeCurMonth(recruitEmployeeCurMonth);
        hanfcStatics.setFireEmployeeCurMonth(fireEmployeeCurMonth);
        hanfcStatics.setCustomerCount(customerCount);
        hanfcStatics.setOrderCount(orderCount);
        hanfcStatics.setSuggestionCount(suggestionCount);

        return new ResponseEntity<>(gson.toJson(hanfcStatics), HttpStatus.OK);
    }

    @GetMapping("/getChartStatics")
    public ResponseEntity<String> getChartStatics() {
        ChartStatics chartStatics = new ChartStatics();
        chartStatics.setActiveEmployee(employeeRepository.getCurrentEmployeeCount(0));
        chartStatics.setInactiveEmployee(employeeRepository.getCurrentEmployeeCount(1));
        chartStatics.setEmployeeType0Count(employeeRepository.getEmployeeByType(0));
        chartStatics.setEmployeeType1Count(employeeRepository.getEmployeeByType(1));
        chartStatics.setEmployeeType2Count(employeeRepository.getEmployeeByType(2));
        chartStatics.setEmployeeType3Count(employeeRepository.getEmployeeByType(3));

        chartStatics.setEmployeeBoy(employeeRepository.getEmployeeByGender(0));
        chartStatics.setEmployeeGirl(employeeRepository.getEmployeeByGender(1));

        chartStatics.setCustomerBoy(customerRepository.getCustomerCountByGender(1));
        chartStatics.setCustomerGirl(customerRepository.getCustomerCountByGender(0));
        List<Product> productList = productRepository.findAll();
        List<ProductStatics> productStaticsList = new ArrayList<>();
        for (Product product : productList) {
            ProductStatics productStatics = new ProductStatics();
            productStatics.setProductName(product.getName());
            int productSaleCount = orderDetailRepository.getOrderCountForProduct(product.getProductId()) == null ? 0 : orderDetailRepository.getOrderCountForProduct(product.getProductId());
            int productCreateCount = productionRepository.getProductionCountForProduct(product.getProductId()) == null ? 0 : productionRepository.getProductionCountForProduct(product.getProductId());
            productStatics.setSaleCount(productSaleCount);
            productStatics.setCreateCount(productCreateCount);
            productStaticsList.add(productStatics);
        }
        chartStatics.setProductStaticsList(productStaticsList);

        List<Ingredients> ingredientsList = ingredientsRepository.findAll();
        List<IngredientsStatics> ingredientsStaticsList = new ArrayList<>();
        for (Ingredients ingredients : ingredientsList) {
            IngredientsStatics ingredientsStatics = new IngredientsStatics();
            ingredientsStatics.setIngredientsId(ingredients.getIngredientsId());
            ingredientsStatics.setName(ingredients.getName());
            int count = purchaseIngredientsRepository.getTotalPurchaseIngredients(ingredients.getIngredientsId()) == null ? 0 : purchaseIngredientsRepository.getTotalPurchaseIngredients(ingredients.getIngredientsId());
            ingredientsStatics.setCount(count);
            ingredientsStaticsList.add(ingredientsStatics);
        }
        chartStatics.setIngredientsStaticsList(ingredientsStaticsList);

        SuggestionStatics suggestionStatics = new SuggestionStatics();
        suggestionStatics.setSuggestionCount(suggestionRepository.findAll().size());
        suggestionStatics.setFeedbackCount(feedbackRepository.findAll().size());
        chartStatics.setSuggestionStatics(suggestionStatics);

        chartStatics.setAllProductCount(productRepository.findAll().size());
        chartStatics.setActiveProductCount(productRepository.findAllValidProducts().size());

        chartStatics.setProductCountPriceInterval1(productRepository.getProductCountByPrice(0, 10));
        chartStatics.setProductCountPriceInterval2(productRepository.getProductCountByPrice(10, 20));
        chartStatics.setProductCountPriceInterval3(productRepository.getProductCountByPrice(20, 50));
        chartStatics.setProductCountPriceInterval4(productRepository.getProductCountByPrice(50, Integer.MAX_VALUE));

        chartStatics.setMainMealCount(productRepository.getProductCountByCategory("mainMeal"));
        chartStatics.setFriesChickenCount(productRepository.getProductCountByCategory("friesChicken"));
        chartStatics.setDessertCount(productRepository.getProductCountByCategory("dessert"));
        chartStatics.setSnackCount(productRepository.getProductCountByCategory("snacks"));

        RecentWeekDate recentWeekDate = new RecentWeekDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        List<OrderStatics> orderStaticsList = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            OrderStatics orderStatics = new OrderStatics();
            orderStatics.setDate(simpleDateFormat.format(recentWeekDate.getRecentWeek()[i]));
            orderStatics.setCount(productOrderRepository.getOrderCountByDate(recentWeekDate.getRecentWeek()[i+1], recentWeekDate.getRecentWeek()[i]));
            orderStaticsList.add(orderStatics);
        }
        chartStatics.setOrderStaticsList(orderStaticsList);

        chartStatics.setOrderCountInterval1(productOrderRepository.getOrderCountByPriceInterval(0, 50));
        chartStatics.setOrderCountInterval2(productOrderRepository.getOrderCountByPriceInterval(50, 100));
        chartStatics.setOrderCountInterval3(productOrderRepository.getOrderCountByPriceInterval(100, 200));
        chartStatics.setOrderCountInterval4(productOrderRepository.getOrderCountByPriceInterval(200, Double.MAX_VALUE));


        return new ResponseEntity<>(gson.toJson(chartStatics), HttpStatus.OK);
    }


}
