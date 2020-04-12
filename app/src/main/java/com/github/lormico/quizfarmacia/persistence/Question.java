package com.github.lormico.quizfarmacia.persistence;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

import java.io.Serializable;

@Entity(tableName = "quesiti", primaryKeys = {"id_domanda", "materia"})
public class Question implements Serializable {

    public Question(@NonNull int questionId, @NonNull String subject, @NonNull String question,
                    @NonNull String answerA, @NonNull String answerB, @NonNull String answerC,
                    @NonNull String answerD, @NonNull String answerE, @NonNull String solution) {
        this.questionId = questionId;
        this.subject = subject;
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.answerE = answerE;
        this.solution = solution;
    }

    @ColumnInfo(name = "id_domanda")
    @NonNull
    private int questionId;

    @ColumnInfo(name = "materia")
    @NonNull
    private String subject;

    @ColumnInfo(name = "domanda")
    @NonNull
    private String question;

    @ColumnInfo(name = "risposta_a")
    @NonNull
    private String answerA;

    @ColumnInfo(name = "risposta_b")
    @NonNull
    private String answerB;

    @ColumnInfo(name = "risposta_c")
    @NonNull
    private String answerC;

    @ColumnInfo(name = "risposta_d")
    @NonNull
    private String answerD;

    @ColumnInfo(name = "risposta_e")
    @NonNull
    private String answerE;

    @ColumnInfo(name = "risposta_esatta")
    @NonNull
    private String solution;

    /* property useful to the Archive section */
    @Ignore
    private boolean expanded;

    @NonNull
    public int getQuestionId() {
        return questionId;
    }

    @NonNull
    public String getSubject() {
        return subject;
    }

    @NonNull
    public String getQuestion() {
        return question;
    }

    public String getAnswerA() {
        return answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public String getAnswerE() {
        return answerE;
    }

    public String getSolution() {
        return solution;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

}
