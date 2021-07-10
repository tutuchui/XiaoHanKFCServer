package com.conehanor.kfcserver.controller;

import com.conehanor.kfcserver.dao.*;
import com.conehanor.kfcserver.entity.Suggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SuggestionController {

    @Autowired
    SuggestionRepository suggestionRepository;

    @PostMapping("/suggest")
    @CrossOrigin
    public ResponseEntity<String> suggest(@RequestParam("content") String content,
                                          @RequestParam("customerId") String customerId)
    {
        try {
            java.sql.Timestamp d = new java.sql.Timestamp(System.currentTimeMillis());
            Suggestion suggestion = new Suggestion();
            suggestion.setContent(content);
            suggestion.setCustomerId(customerId);
            suggestion.setSuggestionTime(d);
            suggestionRepository.saveAndFlush(suggestion);
            return ResponseEntity.status(200).body("Success");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("error");
        }

    }

}
