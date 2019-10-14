package com.codegym.service.impl;

import com.codegym.model.TypeNote;
import com.codegym.repository.TypeNoteRepository;
import com.codegym.service.TypeNoteService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TypeNoteServiceImpl implements TypeNoteService {

    @Autowired
    private TypeNoteRepository typeNoteRepository;

    @Override
    public Iterable<TypeNote> findAll() {
        return typeNoteRepository.findAll();
    }

    @Override
    public TypeNote findById(Long id) {
        return typeNoteRepository.findOne(id);
    }

    @Override
    public void remove(Long id) {
        typeNoteRepository.delete(id);
    }

    @Override
    public TypeNote save(TypeNote typeNote) {
        typeNoteRepository.save(typeNote);
        return typeNote;
    }

}
