package com.conehanor.kfcserver.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FeedbackPK implements Serializable {
    private int suggestionId;
    private int adminId;

    @Column(name = "suggestion_id")
    @Id
    public int getSuggestionId() {
        return suggestionId;
    }

    public void setSuggestionId(int suggestionId) {
        this.suggestionId = suggestionId;
    }

    @Column(name = "admin_id")
    @Id
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackPK that = (FeedbackPK) o;
        return suggestionId == that.suggestionId && adminId == that.adminId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suggestionId, adminId);
    }
}
