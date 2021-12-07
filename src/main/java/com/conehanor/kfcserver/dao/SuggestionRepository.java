package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.Employee;
import com.conehanor.kfcserver.entity.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SuggestionRepository extends JpaRepository<Suggestion, Integer> {

    @Query("select a from Suggestion a where a.customerId = :customerId")
    List<Suggestion> findByCustomerId(int customerId);
}
