package com.project.imageboard.model;

import javax.persistence.*;
import java.util.Base64;

@Entity
public class Message {

    private static int id;

    @Id
    private int currentId;

    private String text;

    @Lob
    private byte[] image;

    public Message() {
    }

    public Message(String text) {
        currentId = ++id;
        this.text = text;
    }

    public static void setId(int id) {
        Message.id = id;
    }

    public void setCurrentId(int currentId) {
        this.currentId = currentId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCurrentId() {
        return currentId;
    }
    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public byte[] getImage() {
        return image;
    }

    public String getBase64String(){
        if(image != null){
            String encodedFile = Base64.getEncoder().encodeToString(image);
            return encodedFile;
        }
        else return "";
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
