package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.ManageEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManageEmployeeRepository  extends JpaRepository<ManageEmployee, Integer>{
}


