package com.conehanor.kfcserver.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@IdClass(FeedbackPK.class)
public class Feedback {
    private int suggestionId;
    private int employeeId;
    private Timestamp feedbackTime;
    private String content;
    private int state;

    @Id
    @Column(name = "suggestion_id")
    public int getSuggestionId() {
        return suggestionId;
    }

    public void setSuggestionId(int suggestionId) {
        this.suggestionId = suggestionId;
    }

    @Id
    @Column(name = "employee_id")
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int adminId) {
        this.employeeId = adminId;
    }

    @Column(name = "state")
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Basic
    @Column(name = "feedback_time")
    public Timestamp getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(Timestamp feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return suggestionId == feedback.suggestionId && employeeId == feedback.employeeId && Objects.equals(feedbackTime, feedback.feedbackTime) && Objects.equals(content, feedback.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suggestionId, employeeId, feedbackTime, content);
    }
}
