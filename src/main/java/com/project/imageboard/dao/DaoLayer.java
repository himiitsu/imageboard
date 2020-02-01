package com.project.imageboard.dao;

import com.project.imageboard.model.Message;
import com.project.imageboard.model.MessageThread;

import java.util.List;

public interface DaoLayer {

    List<MessageThread> getThreads();
    List<Message> getMessages(int id);
    int createThread(String header, String text);
    int postMessage(int id, String text);
}
