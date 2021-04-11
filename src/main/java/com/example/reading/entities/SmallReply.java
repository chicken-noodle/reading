package com.example.reading.entities;

public class SmallReply {
    private int id;
    private String text;
    private String username;
    private int reply_id;

    public SmallReply() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getReply_id() {
        return reply_id;
    }

    public void setReply_id(int reply_id) {
        this.reply_id = reply_id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"text\":\"")
                .append(text).append('\"');
        sb.append(",\"username\":\"")
                .append(username).append('\"');
        sb.append(",\"reply_id\":")
                .append(reply_id);
        sb.append('}');
        return sb.toString();
    }
}
