package com.project.imageboard.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Constructor;
import java.sql.Blob;

@Entity
public class Message {

    private static int id;

    @Id
    private int currentId;

    private String text;

    @Lob
    private Byte[] image;

    public Message() {
    }

    public Message(String text) {
        currentId = ++id;
        this.text = text;
        System.out.println("message " + currentId);
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

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }
}
