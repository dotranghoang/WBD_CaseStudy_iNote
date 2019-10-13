package com.codegym.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "type")
public class TypeNote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Lob
    private String description;

    @OneToMany(targetEntity = Note.class)
    private List<Note> Notes;

    public List<Note> getNotes() {
        return Notes;
    }

    public void setNotes(List<Note> notes) {
        Notes = notes;
    }

    public TypeNote() {
    }

    public TypeNote(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
