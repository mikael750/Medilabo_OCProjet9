package com.openclassrooms.medilabo.risks.services;

import com.openclassrooms.medilabo.risks.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "note", url = "http://gateway:8080/")
public interface NoteServiceProxy {

    @GetMapping("/note/note/{id}")
    List<NoteBean> getNote(@PathVariable("id") int id);

}
