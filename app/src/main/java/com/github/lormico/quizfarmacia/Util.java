package com.github.lormico.quizfarmacia;

import com.github.lormico.quizfarmacia.persistence.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    /**
     * Returns a list of unique random integers selected within the specified range.
     *
     * @param range the maximum value in the original integer list
     * @param n the number of items to extract from the randomized list
     * @return a List of integers, having size `n`
     */
    public static List<Integer> getUniqueRandomNumbers(int range, int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < range; i++) {
            list.add(i);
        }

        Collections.shuffle(list);
        return list.subList(0, n);
    }

}
