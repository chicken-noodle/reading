package com.example.reading.entities;

public class FileMp3 {
    private Integer id;

    private Integer user_id;

    private String username;

    private Integer poem_id;

    public FileMp3() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getPoem_id() {
        return poem_id;
    }

    public void setPoem_id(Integer poem_id) {
        this.poem_id = poem_id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"user_id\":")
                .append(user_id);
        sb.append(",\"username\":\"")
                .append(username).append('\"');
        sb.append(",\"poem_id\":")
                .append(poem_id);
        sb.append('}');
        return sb.toString();
    }
}