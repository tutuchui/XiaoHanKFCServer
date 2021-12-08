package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {

    @Query(value = "SELECT COUNT(*) FROM feedback WHERE feedback.state = 1;" ,nativeQuery = true)
    int getPassFeedbackCount();

    @Query(value = "SELECT COUNT(*) FROM feedback WHERE feedback.state = 0;" ,nativeQuery = true)
    int getUnPassFeedbackCount();
}
