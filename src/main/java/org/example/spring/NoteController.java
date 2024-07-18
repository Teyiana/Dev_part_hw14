package org.example.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView model = new ModelAndView("index");
        List<Note> all = noteService.listAll();
        model.addObject("action", "findAllNotes");
        model.addObject("notes", all);
        return model;
    }

    @PostMapping("/delete")
    public String delete(@RequestParam long id) {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam Long id) {
        ModelAndView model = new ModelAndView("index");
        Note note = noteService.getById(id);
        model.addObject("action", "edit");
        model.addObject("note", note);
        return model;
    }


    @PostMapping("/edit")
    public String edit(@RequestParam Long id,
                       @RequestParam String title,
                       @RequestParam String content) {
        Note note = new Note();
        note.setId(id);
        note.setTitle(title);
        note.setContent(content);
        noteService.update(note);
        return "redirect:/note/list";
    }
}
