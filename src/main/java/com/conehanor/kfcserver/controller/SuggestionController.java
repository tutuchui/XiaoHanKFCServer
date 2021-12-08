package com.conehanor.kfcserver.controller;


import com.conehanor.kfcserver.dao.FeedbackRepository;
import com.conehanor.kfcserver.dao.SuggestionRepository;
import com.conehanor.kfcserver.entity.Employee;
import com.conehanor.kfcserver.entity.Suggestion;
import com.conehanor.kfcserver.model.SuggestionForEmployee;
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

    @Autowired
    FeedbackRepository feedbackRepository;

    @GetMapping("/getAllSuggestion")
    public ResponseEntity<String> getAllSuggestion() {
        List<SuggestionForEmployee> suggestions = suggestionRepository.getAllSuggestionsForEmployee();
        for(SuggestionForEmployee suggestion: suggestions){
            Integer state = feedbackRepository.getFeedbackStateBySuggestionId(suggestion.getSuggestionId());
            suggestion.setState(state);
        }

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

    @GetMapping("/getNumberForSuggestionBox")
    public ResponseEntity<String> getNumberForSuggestionBox() {
        int a =suggestionRepository.getSuggestionCount();
        int b =feedbackRepository.getPassFeedbackCount();
        int c = a - b;
        return new ResponseEntity<>(gson.toJson(c), HttpStatus.OK);
    }


}
