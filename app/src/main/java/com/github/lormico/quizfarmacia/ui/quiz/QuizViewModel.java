package com.github.lormico.quizfarmacia.ui.quiz;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.github.lormico.quizfarmacia.Util;
import com.github.lormico.quizfarmacia.persistence.Question;
import com.github.lormico.quizfarmacia.persistence.QuestionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuizViewModel extends AndroidViewModel {

    private QuestionRepository mRepository;
    private Map<String, ArrayList<Question>> mQuestionsBySubjectMap;
    private List<AnsweredQuestion> mAnsweredQuestions;

    public QuizViewModel(@NonNull Application application) {
        super(application);
        mRepository = new QuestionRepository(application);
        mQuestionsBySubjectMap = mRepository.getQuestionsBySubjectMap();
        mAnsweredQuestions = new ArrayList<>();
    }

    public List<Question> generateRandomizedQuestions(Map<String, String> subjectNumbersMap) {
        mAnsweredQuestions.clear();

        // TODO non sono ordinate!
        List<Question> randomizedQuestions = new ArrayList<>();
        for (Map.Entry<String, String> entry: subjectNumbersMap.entrySet()) {
            randomizedQuestions.addAll(
                    generateRandomizedQuestionsWithConditions(entry.getKey(), Integer.valueOf(entry.getValue())));
        }

        return randomizedQuestions;
    }

    private List<Question> generateRandomizedQuestionsWithConditions(String subject, int n) {
        List<Question> questionList = mQuestionsBySubjectMap.get(subject);
        List<Integer> randomQuestionIds = Util.getUniqueRandomNumbers(questionList.size(), n);
        List<Question> randomizedQuestions = new ArrayList<>();
        for (Integer id : randomQuestionIds) {
            randomizedQuestions.add(questionList.get(id));
            mAnsweredQuestions.add(new AnsweredQuestion(subject, id, -1));
        }

        return randomizedQuestions;
    }

    public void setAnswer(Integer position, int answer) {
        mAnsweredQuestions.get(position).setAnswer(answer);
    }

    public List<AnsweredQuestion> getAnsweredQuestions() {
        return mAnsweredQuestions;
    }
}