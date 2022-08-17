package com.loginexample.repository;

import com.loginexample.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query(value = "SELECT note_list_id FROM student_note_list WHERE student_id = :studentId", nativeQuery = true)
    List<Long> findNoteIdsByStudentId(Long studentId);

    @Query(value = "INSERT INTO student_note_list(student_id, note_list_id) VALUES (:studentId, :noteListId)", nativeQuery = true)
    @Modifying
    @Transactional
    Integer updateStudentNoteList(Long studentId, Long noteListId);

    @Query(value = "UPDATE note SET is_completed = 1 WHERE id = :id",nativeQuery = true)
    @Modifying
    @Transactional
    Integer updateNoteAsCompleted(Long id);
}
