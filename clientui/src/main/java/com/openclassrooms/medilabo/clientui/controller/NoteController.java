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

    /**
     * Retrieves all notes and adds them to the model
     *
     * @param model model
     * @return  A list of `NoteBean` objects
     */
    public List<NoteBean> getAllNotes(Model model) {
        ResponseEntity<List<NoteBean>> response = noteProxy.getAllNotes();
        List<NoteBean> notes = response.getBody();
        model.addAttribute("notes", notes);
        return notes;
    }

    /**
     * Retrieves notes associated with a specific entity and adds them to the model
     *
     * @param model model
     * @param id ID of the patient
     * @return A list of `NoteBean` objects
     */
    public List<NoteBean> getNotes(Model model, int id) {
        ResponseEntity<List<NoteBean>> response = noteProxy.getNote(id);
        List<NoteBean> notes = response.getBody();
        model.addAttribute("notes", notes);
        return notes;
    }

    /**
     * Handles the submission of a new note and adds it to the system
     *
     * @param note The `NoteBean` object containing the details of the note to be added
     * @param model model
     * @return redirection to the patient list
     */
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

    /**
     * Displays the form for adding a new note to a specific patient
     *
     * @param id ID of the patient
     * @param model model
     * @return displays the form for adding a new note
     */
    @GetMapping("/patient/{id}/addNote")
    public String showAddNoteForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("note", new NoteBean());
        model.addAttribute("patientId", id);
        return "addNote";
    }

    /**
     * Handles the submission of an updated note for a specific patient
     *
     * @param id ID of the patient
     * @param note The `NoteBean` object containing the updated details of the note
     * @param model model
     * @return redirection to the patient list
     */
    @PostMapping("/patient/{id}/addNote")
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
