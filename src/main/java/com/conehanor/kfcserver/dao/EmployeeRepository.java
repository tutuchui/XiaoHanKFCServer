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

    @Query("select count(e) from Employee e where e.state = :state")
    int getCurrentEmployeeCount(int state);

    @Query("select count(e) from Employee e where e.type = :type")
    int getEmployeeByType(int type);

    @Query("select count(e) from Employee e where e.gender = :gender")
    int getEmployeeByGender(int gender);


    @Query(value = "SELECT e.name FROM Employee e WHERE e.employeeId = :employeeId")
    Integer getEmployeeNameByEmployeeId(int employeeId);

    //SELECT * FROM 表名 WHERE DATE_FORMAT( 时间字段名, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' )
    @Query(value = "select count(*) from manage_employee where date_format(manage_employee.manage_time, '%Y%m') = date_format(current_date(), '%Y%m') and manage_employee.manage_type = 0;", nativeQuery = true)
    int getEmployeeRecruitCurMonth();

    @Query(value = "select count(*) from manage_employee where date_format(manage_employee.manage_time, '%Y%m') = date_format(current_date(), '%Y%m') and manage_employee.manage_type = 1;", nativeQuery = true)
    int getEmployeeFireCurMonth();

}
