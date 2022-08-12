package com.loginexample.controller;


import com.loginexample.dto.Response;
import com.loginexample.model.Note;
import com.loginexample.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/all")
    public ResponseEntity<Response<List<Note>>> findAllNotes() {
        Response<List<Note>> noteResponseList = Response.<List<Note>>builder()
                .message("List of all available notes")
                .data(noteService.findAllNotes())
                .build();

        return new ResponseEntity<>(noteResponseList, HttpStatus.OK);
    }

    @GetMapping("/notebyusername")
    public ResponseEntity<Response<List<Note>>> findAllNotesByUsername(@RequestParam String username) {
        Response<List<Note>> noteResponseList = Response.<List<Note>>builder()
                .message("List of all notes for " + username)
                .data(noteService.findAllNotesByUsername(username))
                .build();

        return new ResponseEntity<>(noteResponseList, HttpStatus.OK);
    }

    @GetMapping("/activenotes")
    public ResponseEntity<Response<List<Note>>> findAllActiveNotesByUsername(@RequestParam String username) {
        Response<List<Note>> activeNotesList = Response.<List<Note>>builder()
                .message("Active list of notes for " + username)
                .data(noteService.findAllActiveNotesByUsername(username))
                .build();

        return new ResponseEntity<>(activeNotesList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Response<Note>> addNoteByUsername(@RequestParam String username, @RequestBody Note note) {

        Note note1 = Note.builder()
                .description(note.getDescription())
                .isCompleted(false)
                .build();

        Response<Note> noteAdditionResponse = Response.<Note>builder()
                .message("Note added successfully")
                .data(noteService.addNoteByUsername(username, note1))
                .build();

        return new ResponseEntity<>(noteAdditionResponse, HttpStatus.OK);
    }

    @PutMapping("/updatenotecompleted/{id}")
    public ResponseEntity<Response<Note>> updateNoteAsCompleted(@PathVariable Long id) {
        Response<Note> updatedNote = Response.<Note>builder()
                .message("Note status updated")
                .data(noteService.updateNoteAsCompleted(id))
                .build();

        return new ResponseEntity<>(updatedNote, HttpStatus.OK);
    }
}
