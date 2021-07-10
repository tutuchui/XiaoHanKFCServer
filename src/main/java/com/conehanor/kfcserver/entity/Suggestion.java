package com.conehanor.kfcserver.entity;


import javax.persistence.*;

@Entity
@Table(name = "suggestion")
public class Suggestion {

  @Column(name = "suggestion_id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private long suggestionId;

  @Column(name = "customer_id", nullable = false)
  private String customerId;

  @Column(name = "suggestion_time", nullable = false)
  private java.sql.Timestamp suggestionTime;

  @Column(name = "content", nullable = false)
  private String content;


  public long getSuggestionId() {
    return suggestionId;
  }

  public void setSuggestionId(long suggestionId) {
    this.suggestionId = suggestionId;
  }


  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }


  public java.sql.Timestamp getSuggestionTime() {
    return suggestionTime;
  }

  public void setSuggestionTime(java.sql.Timestamp suggestionTime) {
    this.suggestionTime = suggestionTime;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
