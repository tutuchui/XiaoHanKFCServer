package com.conehanor.kfcserver.controller;

import com.conehanor.kfcserver.dao.FeedbackRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return new ResponseEntity<>(gson.toJson(a), HttpStatus.OK);
    }
}
