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
//    private MutableLiveData<String> mText;
    private Map<String, ArrayList<Question>> mQuestionsBySubjectMap;

    public QuizViewModel(@NonNull Application application) {
        super(application);
        mRepository = new QuestionRepository(application);
        mQuestionsBySubjectMap = mRepository.getQuestionsBySubjectMap();
/*        mText = new MutableLiveData<>();
        mText.setValue("This is quiz home fragment");*/
    }

    public List<Question> getRandomizedQuestions(Map<String, String> subjectNumbersMap) {

        // TODO non sono ordinate!
        List<Question> randomizedQuestions = new ArrayList<>();
        for (Map.Entry<String, String> entry: subjectNumbersMap.entrySet()) {
            randomizedQuestions.addAll(
                    getRandomizedQuestionsWithConditions(entry.getKey(), Integer.valueOf(entry.getValue())));
        }

        return randomizedQuestions;
    }

    private List<Question> getRandomizedQuestionsWithConditions(String subject, int n) {
        List<Question> questionList = mQuestionsBySubjectMap.get(subject);
        List<Integer> randomQuestionIds = Util.getUniqueRandomNumbers(questionList.size(), n);
        List<Question> randomizedQuestions = new ArrayList<>();
        for (Integer i : randomQuestionIds) {
            randomizedQuestions.add(questionList.get(i));
        }

        return randomizedQuestions;
    }
}