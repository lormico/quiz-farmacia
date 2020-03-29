package com.github.lormico.quizfarmacia.persistence;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repository per accedere in modo "pulito" alla persistenza.
 * Nota che non dovrei MAI chiamare direttamente il DAO.
 */
public class QuestionRepository {

    private QuestionDAO mQuestionDAO;
    private List<String> mAllSubjects;
    private Map<String, ArrayList<Question>> mQuestionsBySubjectMap;

    public QuestionRepository(Application application) {
        QuestionDatabase db = QuestionDatabase.getDatabase(application);
        mQuestionDAO = db.questionDAO();
        mAllSubjects = mQuestionDAO.getAllSubjects();
        mQuestionsBySubjectMap = new HashMap<>();
        for (String subject : mAllSubjects) {
            List<Question> result = mQuestionDAO.loadAllBySubject(subject);
            ArrayList<Question> questionArrayList = new ArrayList<>(result.size());
            questionArrayList.addAll(result);
            mQuestionsBySubjectMap.put(subject, questionArrayList);
        }
    }

    public List<String> getAllSubjects() {
        return mAllSubjects;
    }

    public Map<String, ArrayList<Question>> getQuestionsBySubjectMap() {
        return mQuestionsBySubjectMap;
    }
}
