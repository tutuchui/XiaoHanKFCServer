package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {

    @Query(value = "SELECT COUNT(*) FROM feedback WHERE feedback.state = 1 or feedback.state = 0;" ,nativeQuery = true)
    int getPassFeedbackCount();

    @Query(value = "SELECT COUNT(*) FROM feedback WHERE feedback.state = 0;" ,nativeQuery = true)
    int getUnPassFeedbackCount();

    @Query(value = "SELECT f.state FROM Feedback f WHERE f.suggestionId = :suggestionId")
    Integer getFeedbackStateBySuggestionId(int suggestionId);

    @Query(value = "SELECT f.content FROM Feedback f WHERE f.suggestionId = :suggestionId")
    String getFeedbackContentBySuggestionId(int suggestionId);

    @Query(value = "update Feedback f set f.content = :content ,f.state = 0 where f.suggestionId = :suggestionId")
    @Modifying
    @Transactional
    int updateContent(String content,int suggestionId);
}