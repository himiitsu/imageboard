package com.project.imageboard.dao;

import com.project.imageboard.model.MessageThread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThreadsDao extends JpaRepository<MessageThread, Integer> {
    @Query(nativeQuery = true, value = "SELECT DISTINCT message_thread_id FROM imageboard.message_thread_messages" +
            " GROUP BY message_thread_id ORDER BY MAX(messages_current_id) DESC;")
    List<Integer> sortThreads();
}
