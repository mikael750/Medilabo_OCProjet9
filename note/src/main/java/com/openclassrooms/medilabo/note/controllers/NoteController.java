package com.openclassrooms.medilabo.note.controllers;

import com.openclassrooms.medilabo.note.models.Note;
import com.openclassrooms.medilabo.note.services.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/note")
@Slf4j
public class NoteController {

    @Autowired
    private NoteService noteService;

    /**
     * Retrieves all notes from the service and returns them in the response.
     *
     * @return `ResponseEntity` containing a list of `Note` objects and an appropriate HTTP status
     */
    @GetMapping()
    public ResponseEntity<List<Note>> getAllNotes() {

        List<Note> notes = noteService.getAllNote();

        if (notes != null) {
            return new ResponseEntity<>(notes, HttpStatus.OK);
        } else {
            log.error("getAllNotes returned null");
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }
    }

    /**
     * Retrieves notes associated with a specific patient by their ID
     *
     * @param id ID of the patient
     * @return A `ResponseEntity` containing a list of `Note` objects associated with the specified patient and an HTTP status of 200 (OK).
     */
    @GetMapping("/{id}")
    public ResponseEntity<List<Note>> getNote(@PathVariable("id") int id) {

        return new ResponseEntity<>(noteService.getNotesByPatient(id),HttpStatus.OK);

    }

    /**
     *  Adds a new note to the system
     *
     * @param note The `Note` object to be added to the system
     * @return A `ResponseEntity` containing the saved `Note` object and an HTTP status of 201 (Created)
     */
    @PostMapping("/add")
    public ResponseEntity<Note> addNote(@RequestBody Note note) {

        return new ResponseEntity<>(noteService.saveNote(note), HttpStatus.CREATED);

    }

    /**
     * Updates an existing note for a specific patient
     *
     * @param note The `Note` object containing the updated details of the note
     * @param id ID of the patient
     * @return A `ResponseEntity` containing the updated `Note` object and an HTTP status of 200 (OK)
     */
    @PostMapping("/{id}/update")
    public ResponseEntity<Note> updateNote(@RequestBody Note note, @PathVariable("id") int id) {

        note.setPatId(String.valueOf(id));
        return new ResponseEntity<>(noteService.updateNote(note), HttpStatus.OK);

    }

    /**
     * Deletes a note associated with a specific patient
     *
     * @param id ID of the Patient
     */
    @PostMapping("/{id}/delete")
    public void deleteNote(@PathVariable("id") int id) {

        noteService.deleteNote(id);

    }

}
