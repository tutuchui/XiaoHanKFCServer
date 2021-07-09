package com.conehanor.kfcserver.dao;
import com.conehanor.kfcserver.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
    @Query(value = "select password from Employee where number= :number", nativeQuery = true)
    String selectByNumber(@Param("number") String number);

}
