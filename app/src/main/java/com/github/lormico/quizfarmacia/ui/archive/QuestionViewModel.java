package com.github.lormico.quizfarmacia.ui.archive;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.github.lormico.quizfarmacia.persistence.Question;
import com.github.lormico.quizfarmacia.persistence.QuestionRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe che fa da tramite fra il Repository e l'interfaccia utente.
 */
public class QuestionViewModel extends AndroidViewModel {

    /**
     * Classe estesa da Question solamente per aggiungere l'attributo
     * `expanded`, utile esclusivamente nello scope della sezione Archivio
     */
    class QuestionItem extends Question {

        private boolean expanded = false;

        public QuestionItem(int questionId, @NonNull String subject, @NonNull String question, @NonNull String answerA, @NonNull String answerB, @NonNull String answerC, @NonNull String answerD, @NonNull String answerE, @NonNull String solution) {
            super(questionId, subject, question, answerA, answerB, answerC, answerD, answerE, solution);
        }

        public boolean isExpanded() {
            return expanded;
        }

        public void setExpanded(boolean expanded) {
            this.expanded = expanded;
        }
    }

    private QuestionRepository mRepository;
    private List<String> mSubjectList;
    private Map<String, ArrayList<QuestionItem>> mQuestionsBySubjectMap;

    public QuestionViewModel(@NonNull Application application) {
        super(application);
        mRepository = new QuestionRepository(application);
        mSubjectList = mRepository.getAllSubjects();

        // Convertiamo i Question estratti dal Repository in QuestionItem
        Map<String, ArrayList<Question>> originalMap = mRepository.getQuestionsBySubjectMap();
        Map<String, ArrayList<QuestionItem>> convertedMap = new HashMap<>();
        for (String subject : originalMap.keySet()) {
            ArrayList<QuestionItem> convertedList = new ArrayList<>();
            for (Question question : originalMap.get(subject)) {
                convertedList.add(new QuestionItem(
                        question.getQuestionId(),
                        question.getSubject(),
                        question.getQuestion(),
                        question.getAnswerA(),
                        question.getAnswerB(),
                        question.getAnswerC(),
                        question.getAnswerD(),
                        question.getAnswerE(),
                        question.getSolution()
                ));
            }
            convertedMap.put(subject, convertedList);
        }
        mQuestionsBySubjectMap = convertedMap;
    }

    public List<String> getSubjectList() {
        return mSubjectList;
    }

    public Map<String, ArrayList<QuestionItem>> getQuestionsBySubjectMap() {
        return mQuestionsBySubjectMap;
    }
}
