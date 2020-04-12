package com.github.lormico.quizfarmacia.ui.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.github.lormico.quizfarmacia.R;

import java.util.List;

public class QuizResultFragment extends Fragment {

    public QuizResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz_result, container, false);
        TextView textView = view.findViewById(R.id.textView2);
        QuizViewModel viewModel = new ViewModelProvider(requireActivity()).get(QuizViewModel.class);
        List<AnsweredQuestion> answeredQuestions = viewModel.getAnsweredQuestions();
        textView.setText("Trovate " + answeredQuestions.size() + " domande");

        return view;
    }
}
