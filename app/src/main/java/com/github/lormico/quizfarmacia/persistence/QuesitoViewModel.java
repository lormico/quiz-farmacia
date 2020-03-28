package com.github.lormico.quizfarmacia.persistence;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe che fa da tramite fra il Repository e l'interfaccia utente.
 * TODO spostare nel pacchetto ui.archive?
 */
public class QuesitoViewModel extends AndroidViewModel {

    private QuesitoRepository mRepository;
    private List<String> mSubjectList;
    private Map<String, ArrayList<Quesito>> mQuestionsBySubjectMap;

    public QuesitoViewModel(@NonNull Application application) {
        super(application);
        mRepository = new QuesitoRepository(application);
        mSubjectList = mRepository.getAllSubjects();
        mQuestionsBySubjectMap = mRepository.getQuestionsBySubjectMap();
    }

    public List<String> getSubjectList() {
        return mSubjectList;
    }

    public Map<String, ArrayList<Quesito>> getQuestionsBySubjectMap() {
        return mQuestionsBySubjectMap;
    }
}
