package com.codegym.controller;


import com.codegym.model.TypeNote;
import com.codegym.service.TypeNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TypeNoteController {

    @Autowired
    private TypeNoteService typeNoteService;

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
}
