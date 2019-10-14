package com.codegym.controller;

import com.codegym.model.Note;
import com.codegym.model.TypeNote;
import com.codegym.service.NoteService;
import com.codegym.service.TypeNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class NoteController {
    @Autowired
    private NoteService noteService;

    @Autowired
    private TypeNoteService typeNoteService;

    @ModelAttribute("typeNotes")
    public Iterable<TypeNote> typeNotes(){
        return typeNoteService.findAll();
    }

    @RequestMapping("/")
    public String home(){
        return "redirect:view-note";
    }

    @GetMapping("/view-note")
    public ModelAndView listAllNote(@PageableDefault(value = 10,sort = "typeNote") Pageable pageable){
        Page<Note> notes;
        notes = noteService.findAll(pageable);

        ModelAndView modelAndView = new ModelAndView("/note/list");
        modelAndView.addObject("notes",notes);

        return modelAndView;
    }

    @PostMapping("/view-note")
    public ModelAndView listAllNote(@RequestParam("s")Optional<String> s,@RequestParam("id") Long id,
                                    @PageableDefault(value = 10,sort = "typeNote") Pageable pageable){
     Page<Note> notes;
    TypeNote typeNote = typeNoteService.findById(id);

        if(s.isPresent() && typeNote == null){
        notes = noteService.findAllByTitleContaining(s.get(),pageable);
    } else if(s.isPresent() && typeNote != null) {
        notes = noteService.findAllByTypeNoteAndTitleContaining(typeNote,s.get(),pageable);
    } else if(!s.isPresent() && typeNote != null) {
        notes = noteService.findAllByTypeNote(typeNote,pageable);
    } else {
        notes = noteService.findAll(pageable);
    }

    ModelAndView modelAndView = new ModelAndView("/note/list");
        modelAndView.addObject("notes",notes);

        return modelAndView;
    }

    @GetMapping("/create-note")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/note/create");
        modelAndView.addObject("note",new Note());

        return modelAndView;
    }

    @PostMapping("/save-note")
    public ModelAndView saveNote(@ModelAttribute Note note){
        noteService.save(note);

        ModelAndView modelAndView = new ModelAndView("/note/create");
        modelAndView.addObject("note",new Note());
        modelAndView.addObject("message","Created Note Successful!");

        return modelAndView;
    }

    @GetMapping("/edit-note/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Note note = noteService.findById(id);

        ModelAndView modelAndView = new ModelAndView("/note/edit");
        modelAndView.addObject("note",note);

        return modelAndView;
    }

    @PostMapping("/update-note")
    public ModelAndView updateNote(@ModelAttribute Note note){
        noteService.save(note);

        ModelAndView modelAndView = new ModelAndView("/note/edit");
        modelAndView.addObject("note",note);
        modelAndView.addObject("message","Updated note successful ");

        return modelAndView;
    }

    @GetMapping("/delete-note/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Note note = noteService.findById(id);

        ModelAndView modelAndView = new ModelAndView("/note/remove");
        modelAndView.addObject("note",note);

        return modelAndView;
    }

    @PostMapping("/delete-note")
    public String deleteNote(@ModelAttribute Note note){
        noteService.remove(note.getId());

        return "redirect:view-note";
    }

    @GetMapping("read-note/{id}")
    public ModelAndView readDetailNote(@PathVariable Long id) {
        Note note = noteService.findById(id);

        ModelAndView modelAndView = new ModelAndView("/note/read");
        modelAndView.addObject("note",note);

        return modelAndView;
    }
}
