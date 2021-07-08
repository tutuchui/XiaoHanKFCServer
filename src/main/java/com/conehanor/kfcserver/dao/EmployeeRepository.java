package com.conehanor.kfcserver.dao;
import com.conehanor.kfcserver.entity.Employee;
import com.conehanor.kfcserver.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface EmployeeRepository extends JpaRepository<Employee, Long>
{


}
