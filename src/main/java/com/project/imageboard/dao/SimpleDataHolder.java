package com.project.imageboard.dao;

import com.project.imageboard.model.Message;
import com.project.imageboard.model.MessageThread;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("Simple")
public class SimpleDataHolder implements DaoLayer {

    private static List<MessageThread> threadHolder = new ArrayList<>();

    @Override
    public List<MessageThread> getThreads() {
        return threadHolder;
    }



    @Override
    public List<Message> getMessages(int id) {
        int i = 0;
        while (threadHolder.get(i).getId() != id){
            i++;
        }

        return threadHolder.get(i).getMessages();
    }

    @Override
    public int createThread(String header, String text) {
        threadHolder.add(new MessageThread(header, text));
        return 1;
    }

    @Override
    public int postMessage(int id, String text) {
        int i = 0;
        while (threadHolder.get(i).getId() != id){
            i++;
        }
        MessageThread messageThread = threadHolder.get(i);
        messageThread.insertMessage(new Message(text));
        return 1;
    }
}
