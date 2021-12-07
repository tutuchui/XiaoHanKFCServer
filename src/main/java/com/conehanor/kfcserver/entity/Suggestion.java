package com.conehanor.kfcserver.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Suggestion {
    private int suggestionId;
    private String content;
    private Timestamp suggestTime;
    private int customerId;

    @Id
    @Column(name = "suggestion_id")
    public int getSuggestionId() {
        return suggestionId;
    }

    public void setSuggestionId(int suggestionId) {
        this.suggestionId = suggestionId;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "customer_id")
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customer_id) {
        this.customerId = customer_id;
    }

    @Basic
    @Column(name = "suggest_time")
    public Timestamp getSuggestTime() {
        return suggestTime;
    }

    public void setSuggestTime(Timestamp suggestTime) {
        this.suggestTime = suggestTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Suggestion that = (Suggestion) o;
        return suggestionId == that.suggestionId && Objects.equals(content, that.content) && Objects.equals(suggestTime, that.suggestTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suggestionId, content, suggestTime);
    }
}
