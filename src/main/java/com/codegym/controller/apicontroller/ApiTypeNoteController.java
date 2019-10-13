package com.codegym.controller.apicontroller;

import com.codegym.model.Note;
import com.codegym.model.TypeNote;
import com.codegym.service.NoteService;
import com.codegym.service.TypeNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiTypeNoteController {
    @Autowired
    private NoteService noteService;

    @Autowired
    private TypeNoteService typeNoteService;

    @GetMapping("/api/type-note")
    public ResponseEntity<List<TypeNote>> listAllTypeNote(){
        List<TypeNote> typeNotes = (List<TypeNote>) typeNoteService.findAll();

        if(typeNotes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(typeNotes,HttpStatus.OK);
    }

    @GetMapping("/api/type-note/{id}")
    public ResponseEntity<TypeNote> getTypeNote(@PathVariable Long id){
        TypeNote typeNote = typeNoteService.findById(id);

        if(typeNote == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(typeNote,HttpStatus.OK);
    }

    @GetMapping("/api/type-note/{id}/notes")
    public ResponseEntity<List<Note>> getNotes(@PathVariable Long id){
        TypeNote typeNote = typeNoteService.findById(id);

        if(typeNote == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Note> notes = (List<Note>) noteService.findAllByTypeNote(typeNote);

        return new ResponseEntity<>(notes,HttpStatus.OK);
    }

    @PostMapping("/api/type-note")
    public ResponseEntity<TypeNote> createTypeNote(@RequestBody TypeNote typeNote){
        TypeNote typeNote1 = typeNoteService.save(typeNote);

        return new ResponseEntity<>(typeNote1,HttpStatus.CREATED);
    }

    @PutMapping("/api/type-note/{id}")
    public ResponseEntity<TypeNote> updateTypeNote(@RequestBody TypeNote typeNote,@PathVariable Long id){
        TypeNote typeNote1 = typeNoteService.findById(id);

        if(typeNote1 == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        typeNote1.setName(typeNote.getName());
        typeNote1.setDescription(typeNote.getDescription());

        typeNoteService.save(typeNote1);

        return new ResponseEntity<>(typeNote1,HttpStatus.OK);
    }

    @DeleteMapping("/api/type-note/{id}")
    public ResponseEntity<Void> deleteTypeNote(@PathVariable Long id){
        TypeNote typeNote = typeNoteService.findById(id);

        if(typeNote == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        typeNoteService.remove(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
