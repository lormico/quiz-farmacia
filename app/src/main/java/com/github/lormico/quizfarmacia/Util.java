package com.github.lormico.quizfarmacia;

import com.github.lormico.quizfarmacia.persistence.Quesito;

public abstract class Util {

    public static String getSolutionString(Quesito question) {
        String solution = question.getSoluzione();
        switch (solution) {
            case "A": return question.getRispostaA();
            case "B": return question.getRispostaB();
            case "C": return question.getRispostaC();
            case "D": return question.getRispostaD();
            case "E": return question.getRispostaE();
            default: return "";
        }
    }
}
