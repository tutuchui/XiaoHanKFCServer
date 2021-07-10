package com.conehanor.kfcserver.dao;
import com.conehanor.kfcserver.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long>{
    @Query(value = "select * from Admin where number= :number", nativeQuery = true)
    Admin selectBynumber(@Param("number") String number);


}


