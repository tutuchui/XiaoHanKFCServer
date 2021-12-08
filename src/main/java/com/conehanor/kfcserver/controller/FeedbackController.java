package com.conehanor.kfcserver.controller;

import com.conehanor.kfcserver.dao.FeedbackRepository;
import com.conehanor.kfcserver.entity.Employee;
import com.conehanor.kfcserver.entity.Feedback;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@CrossOrigin
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    Gson gson;

    @Autowired
    FeedbackRepository feedbackRepository;

    @GetMapping("/getNumberForFeedbackBox")
    public ResponseEntity<String> getNumberForFeedbackBox() {
        int a =feedbackRepository.getUnPassFeedbackCount();
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @PostMapping("/addFeedback")
    public  ResponseEntity<String> addFeedback(@RequestBody String body) {
        Feedback feedback = gson.fromJson(body, Feedback.class);
        feedback.setFeedbackTime(new Timestamp(System.currentTimeMillis()));
        feedback.setState(0);
        feedbackRepository.saveAndFlush(feedback);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @GetMapping("/getContentBySuggestionId")
    public ResponseEntity<String> getContentBySuggestionId(@RequestParam("suggestionId") int suggestionId) {
        String contetnt = feedbackRepository.getFeedbackContentBySuggestionId(suggestionId);
        return new ResponseEntity<>(contetnt, HttpStatus.OK);
    }

    @PostMapping("/updateContent")
    public  ResponseEntity<String> updateContent(@RequestBody String body) {
        Feedback feedback = gson.fromJson(body, Feedback.class);
        feedbackRepository.updateContent(feedback.getContent(),feedback.getSuggestionId());
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
}
