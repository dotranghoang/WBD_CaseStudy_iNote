package com.codegym.repository;

import com.codegym.model.TypeNote;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface TypeNoteRepository extends PagingAndSortingRepository<TypeNote,Long> {
}
