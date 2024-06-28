package com.openclassrooms.medilabo.note;

import com.openclassrooms.medilabo.note.models.Note;
import com.openclassrooms.medilabo.note.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class NoteApplication implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(NoteApplication.class);

	@Autowired
	private NoteRepository noteRepository;

	public static void main(String[] args) {
		SpringApplication.run(NoteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Start !");
/*
		Optional<Note> note = noteRepository.findById("666c6a803c4bb43992e64c67");
		if (note.isPresent()) {
			logger.info(note.get().getNote());
		} else {
			logger.info("Post not found");
		}

		List<Note> allPosts = noteRepository.findAll();
		allPosts.forEach((notes) -> logger.info(notes.getNote()));

		List<Note> result = noteRepository.findByPatId("1");
		result.forEach((notes) -> logger.info(notes.getNote()));


		List<Note> result = noteRepository.findPatientAndNote("1");
		result.forEach((notes) -> logger.info(String.valueOf(notes)));*/
/*
		Note newP = new Note();
		newP.setPatId("99");
		newP.setPatient("TestNew");
		newP.setNote("This patient has some symptoms");

		Note newP2 = new Note();
		newP2.setPatId("98");
		newP2.setPatient("TestNew2");
		newP2.setNote("This patient has other symptoms");

		//noteRepository.insert(newP);
		noteRepository.insert(List.of(newP, newP2));

		String newNote = newP2.getNote() + " and more";
		newP2.setNote(newNote);

		noteRepository.save(newP2);

		noteRepository.deleteById(newP.getId());
*/
	}
}
