package com.codegym.service;

import com.codegym.model.Note;

public interface NoteService {
    Iterable<Note> findAll();

    Note findById(Long id);

    void remove(Long id);

    void save(Note note);
}
