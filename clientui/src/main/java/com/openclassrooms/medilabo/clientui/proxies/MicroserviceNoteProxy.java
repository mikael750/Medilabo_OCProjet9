package com.openclassrooms.medilabo.clientui.proxies;

import com.openclassrooms.medilabo.clientui.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "note", url = "http://gateway:8080/")
public interface MicroserviceNoteProxy {

    @GetMapping("/note/note")
    ResponseEntity<List<NoteBean>> getAllNotes();

    @GetMapping("/note/note/{id}")
    ResponseEntity<List<NoteBean>> getNote(@PathVariable("id") int id);

    @PostMapping("/note/note/add")
    ResponseEntity<NoteBean> addNote(@RequestBody NoteBean note);

    @PostMapping("/note/note/{id}/update")
    ResponseEntity<NoteBean> updateNote(@RequestBody NoteBean note, @PathVariable("id") int id);

    @PostMapping("/note/note/{id}/delete")
    void deleteNote(@PathVariable("id") int id);

}