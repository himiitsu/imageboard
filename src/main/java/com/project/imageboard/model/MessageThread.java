package com.project.imageboard.model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MessageThread {

    @Id
    private int id;

    private String header;

    private String text;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Message> messages = new ArrayList<>();

    public MessageThread(){

    }

    public MessageThread(String header, String text) {
        this.header = header;
        messages.add(new Message(text));
        this.text = text;
        id = messages.get(0).getId();
    }

    public void setImage(byte[] image) {
        messages.get(0).setImage(image);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
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
