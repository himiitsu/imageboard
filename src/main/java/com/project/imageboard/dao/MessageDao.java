package com.project.imageboard.dao;

import com.project.imageboard.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageDao extends JpaRepository<Message, Integer> {

    @Query(value = "SELECT current_id FROM message ORDER BY current_id DESC LIMIT 1", nativeQuery = true)
    int findFirst();
}
