package com.github.lormico.quizfarmacia.persistence;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuestionDAO {
    @Query("SELECT * FROM question")
    List<Question> getAll();

    @Query("SELECT * FROM question WHERE subject = :subject")
    List<Question> loadAllBySubject(String subject);

    @Query("SELECT DISTINCT subject FROM question")
    List<String> getAllSubjects();

    @Query("SELECT * FROM question WHERE subject = :subject AND question_id = :questionId")
    Question getFromPrimaryKeys(String subject, int questionId);
}
