package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    @Query("select a from Admin a where a.number = :number")
    Admin findByNumber(String number);
}
