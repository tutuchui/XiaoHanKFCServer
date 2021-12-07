package com.conehanor.kfcserver.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FeedbackPK implements Serializable {
    private int suggestionId;
    private int employeeId;

    @Column(name = "suggestion_id")
    @Id
    public int getSuggestionId() {
        return suggestionId;
    }

    public void setSuggestionId(int suggestionId) {
        this.suggestionId = suggestionId;
    }

    @Column(name = "employee_id")
    @Id
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

}
