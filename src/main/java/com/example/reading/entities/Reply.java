package com.example.reading.entities;

public class Reply {
    private Integer id;

    private String text;

    private String username;

    private Integer like;

    private String bright;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public String getBright() {
        return bright;
    }

    public void setBright(String bright) {
        this.bright = bright == null ? null : bright.trim();
    }
}