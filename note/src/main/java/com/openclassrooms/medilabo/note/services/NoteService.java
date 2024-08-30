package com.openclassrooms.medilabo.note.services;

import com.openclassrooms.medilabo.note.models.Note;

import java.util.List;

public interface NoteService {

    /**
     * Retrieves all notes from the repository
     *
     * @return A list of all `Note` objects in the repository
     */
    List<Note> getAllNote();

    /**
     * Retrieves notes associated with a specific patient ID
     *
     * @param id ID of the patient
     * @return A list of `Note` objects associated with the specified patient ID
     */
    List<Note> getNotesByPatient(int id);

    /**
     * Saves a new note to the repository
     *
     * @param note The `Note` object to be saved
     * @return The saved `Note` object
     */
    Note saveNote(Note note);

    /**
     * Updates an existing note in the repository
     *
     * @param note The `Note` object with updated details
     * @return The updated `Note` object
     */
    Note updateNote(Note note);

    /**
     * Deletes a note by its ID from the repository
     *
     * @param id ID of the patient
     */
    void deleteNote(int id);

}
