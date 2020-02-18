package com.project.imageboard.dao;

import com.project.imageboard.model.MessageThread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThreadsDao extends JpaRepository<MessageThread, Integer> {
    @Query(nativeQuery = true, value = "SELECT t.message_thread_id FROM\n" +
            "        (SELECT DISTINCT message_thread_id, MAX(messages_current_id) AS max FROM message_thread_messages\n" +
            "        GROUP BY message_thread_id)t\n" +
            "ORDER BY t.max DESC")
    List<Integer> sortThreads();
}