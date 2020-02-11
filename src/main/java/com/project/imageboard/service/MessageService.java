package com.project.imageboard.service;

import com.project.imageboard.dao.MessageDao;
import com.project.imageboard.dao.ThreadsDao;
import com.project.imageboard.model.Message;
import com.project.imageboard.model.MessageThread;
import org.hibernate.MultiIdentifierLoadAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private ThreadsDao threadsDao;

    public void setIdFromDB(List<MessageThread> messageThreads){
        if (messageThreads.size() > 0){
            Message.setId(messageDao.findFirst());
        }
    }

    public void addThread(String header, String text){
        threadsDao.save(new MessageThread(header, text));
    }

    public int addMessage(int id, String text){
        int i = 0;
        Message message = new Message(text);
        messageDao.save(message);
        List<MessageThread> messageThreads = new ArrayList<>();
        threadsDao.findAll().forEach(messageThreads::add);
        setIdFromDB(messageThreads);
        while (messageThreads.get(i).getId() != id){
            i++;
        }
        messageThreads.get(i).getMessages().add(message);
        threadsDao.save(messageThreads.get(i));
        return 0;
    }

    public List<MessageThread> getThreads(){
        List<MessageThread> messageThreads = new ArrayList<>();
        threadsDao.findAll().forEach(messageThreads::add);
        setIdFromDB(messageThreads);
        if (messageThreads.size() > 0){
            System.out.println(messageDao.findFirst());
        }
        return messageThreads;
    }

    public List<Message> getMessages(int id){
        int i = 0;
        List<MessageThread> messageThreads = new ArrayList<>();
        threadsDao.findAll().forEach(messageThreads::add);
        setIdFromDB(messageThreads);
        while (messageThreads.get(i).getId() != id){
            i++;
        }
        return messageThreads.get(i).getMessages();
    }
}
