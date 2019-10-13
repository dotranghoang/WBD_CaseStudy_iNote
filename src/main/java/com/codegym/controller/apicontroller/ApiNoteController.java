package com.codegym.controller.apicontroller;


import com.codegym.model.Note;
import com.codegym.model.NoteConvert;
import com.codegym.model.TypeNote;
import com.codegym.service.NoteService;
import com.codegym.service.TypeNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiNoteController {
    @Autowired
    private NoteService noteService;

    @Autowired
    private TypeNoteService typeNoteService;

    @GetMapping("/api/notes")
    public ResponseEntity<List<Note>> listAllNote(){
        List<Note> notes = (List<Note>) noteService.findAll();

        if(notes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(notes,HttpStatus.OK);
    }

    @GetMapping("/api/notes/{id}")
    public ResponseEntity<Note> getNote(@PathVariable Long id) {
        Note note = noteService.findById(id);

        if(note == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(note,HttpStatus.OK);
    }

    @PostMapping("/api/notes")
    public ResponseEntity<Note> createNote(@RequestBody NoteConvert noteConvert) {
        TypeNote typeNote = typeNoteService.findById(noteConvert.getTypeNote());

        Note note = new Note();

        note.setTitle(noteConvert.getTitle());
        note.setContent(noteConvert.getContent());
        note.setTypeNote(typeNote);

        noteService.save(note);

        return new ResponseEntity<>(note,HttpStatus.CREATED);
    }

    @PutMapping("/api/notes/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id,@RequestBody NoteConvert noteConvert) {
        Note note = noteService.findById(id);
        TypeNote typeNote = typeNoteService.findById(noteConvert.getTypeNote());

        if(note == null ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        note.setTitle(noteConvert.getTitle());
        note.setContent(noteConvert.getContent());
        if(typeNote != null){
            note.setTypeNote(typeNote);
        }

        return new ResponseEntity<>(note,HttpStatus.OK);
    }

    @DeleteMapping("/api/notes/{id}")
    public ResponseEntity<Note> delelteNote(@PathVariable Long id){
        Note note = noteService.findById(id);

        if(note == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        noteService.remove(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
