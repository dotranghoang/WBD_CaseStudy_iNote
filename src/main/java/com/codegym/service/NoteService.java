package com.codegym.service;

import com.codegym.model.Note;
import com.codegym.model.TypeNote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoteService {
    Page<Note> findAll(Pageable pageable);

    Iterable<Note>findAll();

    Note findById(Long id);

    void remove(Long id);

    void save(Note note);

    Page<Note> findAllByTypeNote(TypeNote typeNote,Pageable pageable);

    Page<Note> findAllByTitleContaining(String title, Pageable pageable);

    Iterable<Note> findAllByTypeNote(TypeNote typeNote);

    Page<Note> findAllByTypeNoteAndTitleContaining(TypeNote typeNote, String title,Pageable pageable);

}
