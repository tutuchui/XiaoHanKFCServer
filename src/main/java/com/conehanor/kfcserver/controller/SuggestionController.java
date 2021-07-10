package com.conehanor.kfcserver.controller;

import com.conehanor.kfcserver.dao.*;
import com.conehanor.kfcserver.entity.Suggestion;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SuggestionController {

    @Autowired
    SuggestionRepository suggestionRepository;

    @PostMapping("/suggest")
    @CrossOrigin
    public ResponseEntity<String> suggest(@RequestParam("content") String content,
                                          @RequestParam("customerId") String customerId,
                                          @RequestParam("customerName") String customerName)
    {
        try {
            java.sql.Timestamp d = new java.sql.Timestamp(System.currentTimeMillis());
            Suggestion suggestion = new Suggestion();
            suggestion.setContent(content);
            suggestion.setCustomerId(customerId);
            suggestion.setCustomerName(customerName);
            suggestion.setSuggestionTime(d);
            suggestionRepository.saveAndFlush(suggestion);
            return ResponseEntity.status(200).body("Success");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("error");
        }

    }

    @GetMapping("/getAllSuggestion")
    @CrossOrigin
    public String getAllSuggestion(){
        List<Suggestion> suggestions = suggestionRepository.findAll();
        return new Gson().toJson(suggestions);

    }

}
