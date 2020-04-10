package com.github.lormico.quizfarmacia.ui.archive;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Adapter per fornire dati al ViewPager del Fragment "Archivio Quesiti",
 * nello specifico i sotto-Fragment contenenti le schede con i quesiti
 * ordinati per materia.
 */
public class ArchiveFragmentStateAdapter extends FragmentStateAdapter {

    private List<String> mSubjects;
    private Map<String, ArrayList<ArchiveViewModel.QuestionItem>> mQuestionsBySubjectMap;

    /**
     * Costruttore che oltre al comportamento della superclasse carica
     * dal ViewModel i dati ottenuti dal DB.
     *
     * @param fragment il fragment padre contenente ViewPager
     */
    public ArchiveFragmentStateAdapter(@NonNull Fragment fragment) {
        super(fragment);
        ArchiveViewModel viewModel = new ViewModelProvider(fragment.requireActivity())
                .get(ArchiveViewModel.class);
        mSubjects = viewModel.getSubjectList();
        mQuestionsBySubjectMap = viewModel.getQuestionsBySubjectMap();
    }

    /**
     * Fornisce il Fragment da inserire nel ViewPager alla posizione richiesta.
     *
     * @param position la posizione nel ViewPager
     * @return il Fragment
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new SubjectFragment();

        String subject = mSubjects.get(position);
        ArrayList<ArchiveViewModel.QuestionItem> questions = mQuestionsBySubjectMap.get(subject);

        Bundle args = new Bundle();
        args.putSerializable(SubjectFragment.QUESTIONS, questions);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return mSubjects.size();
    }
}
