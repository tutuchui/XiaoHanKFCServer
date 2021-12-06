package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.Admin;
import com.conehanor.kfcserver.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("select a from Employee a where a.number = :number")
    Employee findByNumber(String number);

    @Query("delete from Employee e where e.number = :number")
    @Modifying
    @Transactional
    int deleteByNumber(String number);

    @Query("update Employee  e set e.state = :state where e.number = :employeeNumber")
    @Transactional
    @Modifying
    int updateProductState(int state, String employeeNumber);

    @Query("select e from Employee e order by e.employeeId desc")
    List<Employee> findLatestEmployeeId();
}
