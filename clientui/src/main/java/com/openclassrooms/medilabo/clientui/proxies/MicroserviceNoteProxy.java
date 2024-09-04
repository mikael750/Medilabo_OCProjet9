package com.openclassrooms.medilabo.clientui.proxies;

import com.openclassrooms.medilabo.clientui.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "note", url = "${api.gateway.url}/note")
public interface MicroserviceNoteProxy {

    @GetMapping("/note")
    ResponseEntity<List<NoteBean>> getAllNotes();

    @GetMapping("/note/{id}")
    ResponseEntity<List<NoteBean>> getNote(@PathVariable("id") int id);

    @PostMapping("/note/add")
    ResponseEntity<NoteBean> addNote(@RequestBody NoteBean note);

    @PutMapping("/note/{id}/update")
    ResponseEntity<NoteBean> updateNote(@RequestBody NoteBean note, @PathVariable("id") int id);

    @PostMapping("/note/{id}/delete")
    void deleteNote(@PathVariable("id") int id);

}