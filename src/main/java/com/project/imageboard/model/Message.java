package com.project.imageboard.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class Message {

    private static int id;

    private int currentId;

    @NotBlank
    private String text;

    public Message(@NotBlank String text) {
        currentId = ++id;
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
}
