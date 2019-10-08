package com.codegym.controller;

import com.codegym.model.Note;
import com.codegym.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NoteController {
    @Autowired
    private NoteService noteService;

    @GetMapping("/view-note")
    public ModelAndView listAllNote(){
        Iterable<Note> notes = noteService.findAll();
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
}
