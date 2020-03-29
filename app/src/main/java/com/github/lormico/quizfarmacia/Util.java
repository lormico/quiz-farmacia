package com.github.lormico.quizfarmacia;

import com.github.lormico.quizfarmacia.persistence.Question;

public abstract class Util {

    public static String getSolutionString(Question question) {
        String solution = question.getSolution();
        switch (solution) {
            case "A": return question.getAnswerA();
            case "B": return question.getAnswerB();
            case "C": return question.getAnswerC();
            case "D": return question.getAnswerD();
            case "E": return question.getAnswerE();
            default: return "";
        }
    }
}
