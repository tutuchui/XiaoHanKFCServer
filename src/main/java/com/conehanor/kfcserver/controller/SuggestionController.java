package com.conehanor.kfcserver.controller;


import com.conehanor.kfcserver.dao.SuggestionRepository;
import com.conehanor.kfcserver.entity.Employee;
import com.conehanor.kfcserver.entity.Suggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/suggestion")
public class SuggestionController {

    @Autowired
    Gson gson;

    @Autowired
    SuggestionRepository suggestionRepository;

    @GetMapping("/getAllSuggestion")
    public ResponseEntity<String> getAllSuggestion() {
        List<Suggestion> suggestions = suggestionRepository.findAll();
        return new ResponseEntity<>(gson.toJson(suggestions), HttpStatus.OK);
    }

    @GetMapping("/getSuggestionByCustomerId")
    public ResponseEntity<String> getSuggestionByCustomerId(@RequestParam("customerId") int customerId) {
        List<Suggestion> suggestions = suggestionRepository.findByCustomerId(customerId);
        return new ResponseEntity<>(gson.toJson(suggestions), HttpStatus.OK);
    }

    @PostMapping("/addSuggestion")
    public ResponseEntity<String> addSuggestion(@RequestBody String body) {
        Suggestion suggestion = gson.fromJson(body, Suggestion.class);
        suggestion.setSuggestTime(new Timestamp(System.currentTimeMillis()));
        suggestionRepository.saveAndFlush(suggestion);
        return new ResponseEntity<>(gson.toJson("success"), HttpStatus.OK);
    }

}
