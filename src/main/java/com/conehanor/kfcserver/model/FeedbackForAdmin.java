package com.conehanor.kfcserver.model;

import java.util.Date;

public class FeedbackForAdmin {
    private int suggestionId;
    private int employeeId;
    private Date feedbackTime;
    private String content;
    private int state;
    private String employeeName;

    public FeedbackForAdmin(int suggestionId, int employeeId,Date feedbackTime, String content, int state, String employeeName ) {
        this.suggestionId = suggestionId;
        this.employeeId = employeeId;
        this.feedbackTime = feedbackTime;
        this.content = content;
        this.state = state;
        this.employeeName = employeeName;
    }

    public int getSuggestionId() { return suggestionId; }

    public void setSuggestionId(int suggestionId) { this.suggestionId = suggestionId; }

    public int getEmployeeId() { return employeeId; }

    public void setEmployeeId(int employeeId)  { this.employeeId = employeeId; }

    public Date getFeedbackTime() { return feedbackTime; }

    public void setFeedbackTime(Date feedbackTime) { this.feedbackTime = feedbackTime; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public int getState() { return state; }

    public void setState(int state) { this.state = state; }

    public String getEmployeeName() { return employeeName; }

    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }



}
