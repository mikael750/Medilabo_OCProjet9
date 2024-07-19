package com.openclassrooms.medilabo.risks.services;

import com.openclassrooms.medilabo.risks.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "note", url = "http://localhost:8083/")
public interface NoteServiceProxy {

    @GetMapping("/note")
    ResponseEntity<List<NoteBean>> getAllNotes();

    @GetMapping("/note/{id}")
    ResponseEntity<List<NoteBean>> getNote(@PathVariable("id") int id);

    @PostMapping("/note/add")
    ResponseEntity<NoteBean> addNote(@RequestBody NoteBean note);

    @PostMapping("/note/{id}/update")
    ResponseEntity<NoteBean> updateNote(@RequestBody NoteBean note, @PathVariable("id") int id);

    @PostMapping("/note/{id}/delete")
    void deleteNote(@PathVariable("id") int id);

}
