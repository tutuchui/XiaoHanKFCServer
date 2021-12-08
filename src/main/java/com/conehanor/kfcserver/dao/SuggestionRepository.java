package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.Employee;
import com.conehanor.kfcserver.entity.Suggestion;
import com.conehanor.kfcserver.model.SuggestionForEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SuggestionRepository extends JpaRepository<Suggestion, Integer> {

    @Query("select a from Suggestion a where a.customerId = :customerId")
    List<Suggestion> findByCustomerId(int customerId);

    @Query("select new com.conehanor.kfcserver.model.SuggestionForEmployee(s.suggestionId, s.content, c.phone, s.suggestTime, c.customerId, c.name) from Suggestion s, Customer c where s.customerId = c.customerId")
    List<SuggestionForEmployee> getAllSuggestionsForEmployee();

    @Query(value = "SELECT COUNT(s) FROM Suggestion s")
    int getSuggestionCount();

}
