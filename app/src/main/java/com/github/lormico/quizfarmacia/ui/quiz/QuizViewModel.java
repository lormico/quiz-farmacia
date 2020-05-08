package com.github.lormico.quizfarmacia.ui.quiz;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.preference.PreferenceManager;

import com.github.lormico.quizfarmacia.Util;
import com.github.lormico.quizfarmacia.persistence.Question;
import com.github.lormico.quizfarmacia.persistence.QuestionRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class QuizViewModel extends AndroidViewModel {

    private QuestionRepository mRepository;
    private Map<String, ArrayList<Question>> mQuestionsBySubjectMap;
    private List<AnsweredQuestion> mAnsweredQuestions;
    private List<Question> mRandomizedQuestions;
    private SharedPreferences mSharedPreferences;
    private boolean mQuizInProgress;
    private CountDownTimer mCountDownTimer;

    public QuizViewModel(@NonNull Application application) {
        super(application);
        mRepository = new QuestionRepository(application);
        mQuestionsBySubjectMap = mRepository.getQuestionsBySubjectMap();
        mAnsweredQuestions = new ArrayList<>();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(
                application.getApplicationContext());
    }

    public Question getQuestion(String subject, int questionId) {
        return mRepository.getQuestion(subject, questionId);
    }

    public void generateRandomizedQuestions() {
        Log.d(this.getClass().getSimpleName(), "Generating new random questions");
        mAnsweredQuestions.clear(); // why? TODO remind why we're doing this

        Map<String, String> subjectNumbersMap = new LinkedHashMap<>();
        subjectNumbersMap.put("Biologia",
                mSharedPreferences.getString("num_questions_biology", "0"));
        subjectNumbersMap.put("Chimica Generale ed Inorganica",
                mSharedPreferences.getString("num_questions_general_chemistry", "0"));
        subjectNumbersMap.put("Chimica Organica",
                mSharedPreferences.getString("num_questions_organic_chemistry", "0"));
        subjectNumbersMap.put("Fisica",
                mSharedPreferences.getString("num_questions_physics", "0"));
        subjectNumbersMap.put("Matematica",
                mSharedPreferences.getString("num_questions_maths", "0"));

        List<Question> randomizedQuestions = new ArrayList<>();
        for (Map.Entry<String, String> entry: subjectNumbersMap.entrySet()) {
            randomizedQuestions.addAll(
                    generateRandomizedQuestionsWithConditions(entry.getKey(), Integer.valueOf(entry.getValue())));
        }

        mRandomizedQuestions = randomizedQuestions;
    }

    private List<Question> generateRandomizedQuestionsWithConditions(String subject, int n) {
        Log.d(this.getClass().getSimpleName(), "Generating " + n + " random questions " +
                "for subject " + subject);
        List<Question> questionList = mQuestionsBySubjectMap.get(subject);
        List<Integer> randomQuestionIds = Util.getUniqueRandomNumbers(questionList.size(), n);
        List<Question> randomizedQuestions = new ArrayList<>();
        for (Integer id : randomQuestionIds) {
            randomizedQuestions.add(questionList.get(id));
            mAnsweredQuestions.add(new AnsweredQuestion(subject, id + 1, -1));
        }

        return randomizedQuestions;
    }

    public void setAnswer(Integer position, int answer) {
        mAnsweredQuestions.get(position).setAnswer(answer);
    }

    public List<AnsweredQuestion> getAnsweredQuestions() {
        return mAnsweredQuestions;
    }

    public List<Question> getRandomizedQuestions() {
        return mRandomizedQuestions;
    }

    public void setQuizInProgress(boolean quizInProgress) {
        mQuizInProgress = quizInProgress;
    }

    public boolean isQuizInProgress() {
        return mQuizInProgress;
    }

    public void setCountDownTimer(CountDownTimer countDownTimer) {
        Log.d(QuizViewModel.class.getSimpleName(), "Avviato conto alla rovescia!");
        mCountDownTimer = countDownTimer;
    }

    public void cancelCountDownTimer() {
        Log.d(QuizViewModel.class.getSimpleName(), "Annullato conto alla rovescia!");
        mCountDownTimer.cancel();
    }
}