package com.loginexample.service;

import com.loginexample.model.Note;
import com.loginexample.model.Student;
import com.loginexample.repository.NoteRepository;
import com.loginexample.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<Note> findAllNotes() {
        return noteRepository.findAll();
    }

    public Note findNoteById(Long id) {
        return noteRepository.findById(id).get();
    }

    public List<Note> findAllNotesByUsername(String username) {
        Student student = studentRepository.findStudentByUsername(username);

        List<Long> noteIds = noteRepository.findNoteIdsByStudentId((long)student.getId());

        List<Note> noteList = new LinkedList<>();
        for(Long noteId : noteIds) {
            if(noteRepository.findById((long)noteId).isPresent()) {
                noteList.add(noteRepository.findById((long)noteId).get());
            }
        }

        return noteList;
    }

    public List<Note> findAllActiveNotesByUsername(String username) {
        Student student = studentRepository.findStudentByUsername(username);

        List<Long> noteIds = noteRepository.findNoteIdsByStudentId((long)student.getId());

        List<Note> noteList = new LinkedList<>();
        for(Long noteId : noteIds) {
            if(noteRepository.findById((long)noteId).isPresent()) {
                noteList.add(noteRepository.findById((long)noteId).get());
            }
        }

        List<Note> activeNoteList = new LinkedList<>();
        noteList.forEach(note -> {
            if(!note.getIsCompleted()) {
                activeNoteList.add(note);
            }
        });

        return activeNoteList;
    }

    public Note addNoteByUsername(String username, Note note) {
        Note note1 = noteRepository.save(note);
        Student student = studentRepository.findStudentByUsername(username);

        Integer result = noteRepository.updateStudentNoteList((long)student.getId(), note1.getId());

        return note;
    }

    public Note updateNoteAsCompleted(Long id) {
        Integer result = noteRepository.updateNoteAsCompleted(id);
        return noteRepository.findById(id).get();
    }
}
