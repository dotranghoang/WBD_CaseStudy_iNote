package com.codegym.controller;


import com.codegym.model.Note;
import com.codegym.model.TypeNote;
import com.codegym.service.NoteService;
import com.codegym.service.TypeNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TypeNoteController {

    @Autowired
    private TypeNoteService typeNoteService;

    @Autowired
    private NoteService noteService;

    @GetMapping("/view-type")
    public ModelAndView listAllType(){
       Iterable<TypeNote> typeNotes = typeNoteService.findAll();

       ModelAndView modelAndView = new ModelAndView("/type/list");
       modelAndView.addObject("typeNotes",typeNotes);

       return modelAndView;
    }

    @GetMapping("/create-type-note")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/type/create");
        modelAndView.addObject("typeNote", new TypeNote());

        return modelAndView;
    }

    @PostMapping("/save-type-note")
    public ModelAndView saveTypeNote(@ModelAttribute TypeNote typeNote){
        typeNoteService.save(typeNote);

        ModelAndView modelAndView = new ModelAndView("/type/create");
        modelAndView.addObject("typeNote",new TypeNote());
        modelAndView.addObject("message","Created successful");
        return modelAndView;
    }

    @GetMapping("/edit-type-note/{id}")
    public ModelAndView editTypeNote(@PathVariable Long id){
        TypeNote typeNote = typeNoteService.findById(id);

        ModelAndView modelAndView = new ModelAndView("/type/edit");
        modelAndView.addObject("typeNote",typeNote);

        return modelAndView;
    }

   @PostMapping("/update-type-note")
    public ModelAndView updateTypeNote(@ModelAttribute TypeNote typeNote){
        typeNoteService.save(typeNote);

        ModelAndView modelAndView = new ModelAndView("/type/edit");
        modelAndView.addObject("typeNote",typeNote);
        modelAndView.addObject("message","Updated Type Note");

        return modelAndView;
    }

    @GetMapping("/delete-type-note/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        TypeNote typeNote = typeNoteService.findById(id);

        ModelAndView modelAndView = new ModelAndView("/type/remove");
        modelAndView.addObject("typeNote",typeNote);

        return modelAndView;
    }

    @PostMapping("/delete-type-note")
    public String deleteTypeNote(@ModelAttribute TypeNote typeNote){


        List<Note> notes = (List<Note>) noteService.findAllByTypeNote(typeNote);
        for (Note note: notes){
            noteService.remove(note.getId());
        }

        typeNoteService.remove(typeNote.getId());

        return "redirect:view-type";
    }
}
