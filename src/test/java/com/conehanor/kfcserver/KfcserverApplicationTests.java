package com.conehanor.kfcserver;

import com.conehanor.kfcserver.dao.AdminRepository;
import com.conehanor.kfcserver.dao.EmployeeRepository;
import com.conehanor.kfcserver.dao.ProductRepository;
import com.conehanor.kfcserver.entity.Admin;
import com.conehanor.kfcserver.entity.Employee;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KfcserverApplicationTests {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AdminRepository adminRepository;

    @Test
    void contextLoads() {
        Employee employee = new Employee();
        employee.setName("小汗");
        employee.setNumber("XH004");
        employee.setEmail("xh004@hanfc.com");
        employee.setPhone("15994991143");
        employee.setState(1);
        employee.setType("老板");

        employeeRepository.saveAndFlush(employee);
    }

    @Test
    void InsertAdmin() {
        Admin admin = new Admin();
        admin.setName("汗老板");
        admin.setNumber("XH0001");
        admin.setPassword("xxh12345");
        adminRepository.saveAndFlush(admin);
    }


}
