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

    @GetMapping("/{id}")
    public ResponseEntity<List<Note>> getNote(@PathVariable("id") int id) {

        return new ResponseEntity<>(noteService.getNotesByPatient(id),HttpStatus.OK);

    }

    @PostMapping("/add")
    public ResponseEntity<Note> addNote(@RequestBody Note note) {

        return new ResponseEntity<>(noteService.saveNote(note), HttpStatus.CREATED);

    }

    @PostMapping("/{id}/update")
    public ResponseEntity<Note> updateNote(@RequestBody Note note, @PathVariable("id") int id) {

        note.setPatId(String.valueOf(id));
        return new ResponseEntity<>(noteService.updateNote(note), HttpStatus.OK);

    }

    @PostMapping("/{id}/delete")
    public void deleteNote(@PathVariable("id") int id) {

        noteService.deleteNote(id);

    }

}
