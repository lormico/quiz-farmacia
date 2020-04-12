package com.github.lormico.quizfarmacia.persistence;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuestionDAO {
    @Query("SELECT * FROM quesiti")
    List<Question> getAll();

    @Query("SELECT * FROM quesiti WHERE materia = :subject")
    List<Question> loadAllBySubject(String subject);

    @Query("SELECT DISTINCT materia FROM quesiti")
    List<String> getAllSubjects();

    @Query("SELECT * FROM quesiti WHERE materia = :subject AND id_domanda = :questionId")
    Question getFromPrimaryKeys(String subject, int questionId);
}
