package com.openclassrooms.medilabo.note.services;

import com.openclassrooms.medilabo.note.models.Note;

import java.util.List;

public interface NoteService {

    List<Note> getAllNote();

    List<Note> getNotesByPatient(int id);

    Note saveNote(Note note);

    Note updateNote(Note note);

    void deleteNote(int id);

}
