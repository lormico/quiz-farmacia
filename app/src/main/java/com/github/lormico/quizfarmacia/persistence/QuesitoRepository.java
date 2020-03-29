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
public class QuesitoRepository {

    private QuesitoDAO mQuesitoDAO;
    private List<String> mAllSubjects;
    private Map<String, ArrayList<Quesito>> mQuestionsBySubjectMap;

    public QuesitoRepository(Application application) {
        QuesitoDatabase db = QuesitoDatabase.getDatabase(application);
        mQuesitoDAO = db.quesitoDAO();
        mAllSubjects = mQuesitoDAO.getAllSubjects();
        mQuestionsBySubjectMap = new HashMap<>();
        for (String subject : mAllSubjects) {
            List<Quesito> result = mQuesitoDAO.loadAllBySubject(subject);
            ArrayList<Quesito> questionArrayList = new ArrayList<>(result.size());
            questionArrayList.addAll(result);
            mQuestionsBySubjectMap.put(subject, questionArrayList);
        }
    }

    public List<String> getAllSubjects() {
        return mAllSubjects;
    }

    public Map<String, ArrayList<Quesito>> getQuestionsBySubjectMap() {
        return mQuestionsBySubjectMap;
    }
}
