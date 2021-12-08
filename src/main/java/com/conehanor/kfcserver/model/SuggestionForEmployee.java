package com.conehanor.kfcserver.model;

import java.sql.Timestamp;
import java.util.Date;

public class SuggestionForEmployee {
    private int suggestionId;
    private String content;
    private Date suggestTime;
    private String phone;
    private int customerId;
    private String customerName;

    public SuggestionForEmployee(int suggestionId, String content, String phone, Date suggestTime, int customerId, String customerName) {
        this.suggestionId = suggestionId;
        this.content = content;
        this.phone = phone;
        this.suggestTime = suggestTime;
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSuggestionId() {
        return suggestionId;
    }

    public void setSuggestionId(int suggestionId) {
        this.suggestionId = suggestionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSuggestTime() {
        return suggestTime;
    }

    public void setSuggestTime(Date suggestTime) {
        this.suggestTime = suggestTime;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
