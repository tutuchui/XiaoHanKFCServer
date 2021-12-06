package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.Employee;
import com.conehanor.kfcserver.entity.ManageEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;

public interface ManageEmployeeRepository  extends JpaRepository<ManageEmployee, Integer>{

    @Query("select a.manageTime from ManageEmployee a where a.employeeId = :employeeId and a.manageType = 0")
    Timestamp getAddTimeById(int employeeId);

    @Query("select a.manageTime from ManageEmployee a where a.employeeId = :employeeId and a.manageType = 1")
    Timestamp getFireTimeById(int employeeId);
}


