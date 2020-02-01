package com.project.imageboard.model;

import java.util.ArrayList;
import java.util.List;

public class MessageThread {

    private String header;
    private String text;
    private int id;

    private List<Message> messages = new ArrayList<>();

    public MessageThread(String header, String text) {
        this.header = header;
        messages.add(new Message(text));
        this.text = text;
        id = messages.get(0).getId();
    }

    public int getId() {
        return id;
    }
    public String getText() {
        return text;
    }
    public String getHeader() {
        return header;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void insertMessage(Message message){
        messages.add(message);
    }
}
