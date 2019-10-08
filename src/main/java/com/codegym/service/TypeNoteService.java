package com.codegym.service;

import com.codegym.model.TypeNote;

public interface TypeNoteService {
    Iterable<TypeNote> findAll();

    TypeNote findById(Long id);

    void remove(Long id);

    void save(TypeNote typeNote);
}