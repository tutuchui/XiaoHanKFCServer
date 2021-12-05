package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.Admin;
import com.conehanor.kfcserver.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("select a from Employee a where a.number = :number")
    Employee findByNumber(String number);
}
