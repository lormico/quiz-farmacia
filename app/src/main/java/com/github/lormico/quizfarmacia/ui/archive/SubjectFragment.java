package com.github.lormico.quizfarmacia.ui.archive;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.lormico.quizfarmacia.R;
import com.github.lormico.quizfarmacia.persistence.Quesito;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che gestisce il Fragment base della sezione Archivio.
 *
 * Contiene un RecyclerView che mostra le domande in lista.
 */
public class SubjectFragment extends Fragment {
    public static final String QUESTIONS = "questions";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subject, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.question_recycler_view);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        Bundle args = getArguments();
        adapter = new QuestionRecyclerViewAdapter(args.getSerializable(QUESTIONS));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    }
}
