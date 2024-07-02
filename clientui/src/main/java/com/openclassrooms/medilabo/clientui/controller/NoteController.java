package com.openclassrooms.medilabo.clientui.controller;

import com.openclassrooms.medilabo.clientui.beans.NoteBean;
import com.openclassrooms.medilabo.clientui.proxies.MicroserviceNoteProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class NoteController {

    private final MicroserviceNoteProxy noteProxy;

    public NoteController(MicroserviceNoteProxy patientProxy) {
        this.noteProxy = patientProxy;
    }

    public List<NoteBean> getAllNotes(Model model) {
        ResponseEntity<List<NoteBean>> response = noteProxy.getAllNotes();
        List<NoteBean> notes = response.getBody();
        model.addAttribute("notes", notes);
        return notes;
    }

    @PostMapping("/note/add")
    public String addNote(@ModelAttribute NoteBean note, Model model) {
        ResponseEntity<NoteBean> response = noteProxy.addNote(note);
        if (response.getStatusCode().is2xxSuccessful()) {
            model.addAttribute("successMessage", "Note successfully added");
        } else {
            model.addAttribute("errorMessage", "Error when adding note");
        }

        return "redirect:/patient";
    }

    @PostMapping("/note/{id}/update")
    public String updateNote(@PathVariable("id") int id, @ModelAttribute NoteBean note, Model model) {
        ResponseEntity<NoteBean> response = noteProxy.updateNote(note, id);
        if (response.getStatusCode().is2xxSuccessful()) {
            model.addAttribute("successMessage", "Note successfully updated");
        } else {
            model.addAttribute("errorMessage", "Error when updating note");
        }
        return "redirect:/patient";
    }

}
