package com.example.reading.entities;

public class Topic {
    private Integer id;

    private String topic_title;

    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopic_title() {
        return topic_title;
    }

    public void setTopic_title(String topic_title) {
        this.topic_title = topic_title == null ? null : topic_title.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"topic_title\":\"")
                .append(topic_title).append('\"');
        sb.append(",\"username\":\"")
                .append(username).append('\"');
        sb.append('}');
        return sb.toString();
    }
}