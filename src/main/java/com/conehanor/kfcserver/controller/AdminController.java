package com.conehanor.kfcserver.controller;

import com.conehanor.kfcserver.dao.*;
import com.conehanor.kfcserver.entity.Admin;
import com.conehanor.kfcserver.entity.Product;
import com.conehanor.kfcserver.model.ChartStatics;
import com.conehanor.kfcserver.model.HanfcStatics;
import com.conehanor.kfcserver.model.ProductStatics;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getStatics")
    public ResponseEntity<String> getEmployeeData(){
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
    public ResponseEntity<String> getChartStatics(){
        ChartStatics chartStatics = new ChartStatics();
        chartStatics.setActiveEmployee(employeeRepository.getCurrentEmployeeCount(0));
        chartStatics.setInactiveEmployee(employeeRepository.getCurrentEmployeeCount(1));
        chartStatics.setEmployeeType0Count(employeeRepository.getEmployeeByType(0));
        chartStatics.setEmployeeType1Count(employeeRepository.getEmployeeByType(1));
        chartStatics.setEmployeeType2Count(employeeRepository.getEmployeeByType(2));
        chartStatics.setEmployeeType3Count(employeeRepository.getEmployeeByType(3));

        chartStatics.setEmployeeBoy(employeeRepository.getEmployeeByGender(0));
        chartStatics.setEmployeeGirl(employeeRepository.getEmployeeByGender(1));

        List<Product> productList = productRepository.findAll();
        List<ProductStatics> productStaticsList = new ArrayList<>();
        for(Product product: productList){
            ProductStatics productStatics = new ProductStatics();
            productStatics.setProductName(product.getName());
            int productSaleCount = orderDetailRepository.getOrderCountForProduct(product.getProductId()) == null ? 0 : orderDetailRepository.getOrderCountForProduct(product.getProductId());
            int productCreateCount = productionRepository.getProductionCountForProduct(product.getProductId()) == null ? 0 : productionRepository.getProductionCountForProduct(product.getProductId());
            productStatics.setSaleCount(productSaleCount);
            productStatics.setCreateCount(productCreateCount);
            productStaticsList.add(productStatics);
        }

        chartStatics.setProductStaticsList(productStaticsList);

        return new ResponseEntity<>(gson.toJson(chartStatics), HttpStatus.OK);
    }


}
