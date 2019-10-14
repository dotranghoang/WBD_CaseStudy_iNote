package com.codegym.service;

import com.codegym.model.TypeNote;

import java.util.Optional;

public interface TypeNoteService {
    Iterable<TypeNote> findAll();

    TypeNote findById(Long id);

    void remove(Long id);

    TypeNote save(TypeNote typeNote);
}