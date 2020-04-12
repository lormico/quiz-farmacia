package com.github.lormico.quizfarmacia.ui.archive;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.github.lormico.quizfarmacia.persistence.Question;
import com.github.lormico.quizfarmacia.persistence.QuestionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Classe che fa da tramite fra il Repository e l'interfaccia utente.
 */
public class ArchiveViewModel extends AndroidViewModel {

    private QuestionRepository mRepository;
    private List<String> mSubjectList;
    private Map<String, ArrayList<Question>> mQuestionsBySubjectMap;

    public ArchiveViewModel(@NonNull Application application) {
        super(application);
        mRepository = new QuestionRepository(application);
        mSubjectList = mRepository.getAllSubjects();
        mQuestionsBySubjectMap = mRepository.getQuestionsBySubjectMap();
    }

    public List<String> getSubjectList() {
        return mSubjectList;
    }

    public Map<String, ArrayList<Question>> getQuestionsBySubjectMap() {
        return mQuestionsBySubjectMap;
    }
}
