package com.github.lormico.quizfarmacia.persistence;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuesitoDAO {
    @Query("SELECT * FROM quesiti")
    List<Quesito> getAll();

    @Query("SELECT * FROM quesiti WHERE materia = :subject")
    List<Quesito> loadAllBySubject(String subject);

    @Query("SELECT DISTINCT materia FROM quesiti")
    List<String> getAllSubjects();
}
