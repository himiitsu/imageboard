package com.project.imageboard.dao;

import com.project.imageboard.model.MessageThread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadsDao extends JpaRepository<MessageThread, Integer> {
}
