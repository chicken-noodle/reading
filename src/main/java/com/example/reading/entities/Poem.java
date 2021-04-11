package com.example.reading.entities;

public class Poem {
    private Integer id;

    private String title;

    private String guide_lang;

    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getGuide_lang() {
        return guide_lang;
    }

    public void setGuide_lang(String guide_lang) {
        this.guide_lang = guide_lang == null ? null : guide_lang.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"title\":\"")
                .append(title).append('\"');
        sb.append(",\"guide_lang\":\"")
                .append(guide_lang).append('\"');
        sb.append(",\"text\":\"")
                .append(text).append('\"');
        sb.append('}');
        return sb.toString();
    }
}