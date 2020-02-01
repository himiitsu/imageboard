package com.project.imageboard.service;

import com.project.imageboard.dao.DaoLayer;
import com.project.imageboard.model.Message;
import com.project.imageboard.model.MessageThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final DaoLayer daoLayer;

    @Autowired
    public MessageService(@Qualifier("Simple") DaoLayer daoLayer) {
        this.daoLayer = daoLayer;
    }

    public int addThread(String header, String text){
        return daoLayer.createThread(header, text);
    }

    public int addMessage(int id, String text){
        return daoLayer.postMessage(id, text);
    }

    public List<MessageThread> getThreads(){
        return daoLayer.getThreads();
    }

    public List<Message> getMessages(int id){
        return daoLayer.getMessages(id);
    }
}
