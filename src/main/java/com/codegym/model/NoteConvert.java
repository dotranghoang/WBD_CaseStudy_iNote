package com.codegym.model;

public class NoteConvert {
    private Long id,typeNote;
    private String title,content;

    public NoteConvert() {
    }

    public NoteConvert(Long id, Long typeNote, String title, String content) {
        this.id = id;
        this.typeNote = typeNote;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeNote() {
        return typeNote;
    }

    public void setTypeNote(Long typeNote) {
        this.typeNote = typeNote;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
