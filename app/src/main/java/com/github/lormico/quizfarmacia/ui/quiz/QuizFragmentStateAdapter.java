package com.github.lormico.quizfarmacia.ui.quiz;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.github.lormico.quizfarmacia.persistence.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizFragmentStateAdapter extends FragmentStateAdapter {

    private List<Question> mQuestions;

    public QuizFragmentStateAdapter(@NonNull Fragment fragment) {
        super(fragment);
        QuizViewModel viewModel = new ViewModelProvider(fragment.requireActivity())
                .get(QuizViewModel.class);

        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(fragment.getContext());
        Map<String, String> subjectNumbersMap = new HashMap<>();
        subjectNumbersMap.put("Biologia",
                sharedPreferences.getString("num_questions_biology", "0"));
        subjectNumbersMap.put("Chimica Generale ed Inorganica",
                sharedPreferences.getString("num_questions_general_chemistry", "0"));
        subjectNumbersMap.put("Chimica Organica",
                sharedPreferences.getString("num_questions_organic_chemistry", "0"));
        subjectNumbersMap.put("Fisica",
                sharedPreferences.getString("num_questions_physics", "0"));
        subjectNumbersMap.put("Matematica",
                sharedPreferences.getString("num_questions_maths", "0"));
        mQuestions = viewModel.getRandomizedQuestions(subjectNumbersMap);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new QuestionFragment();
        Question question = mQuestions.get(position);

        Bundle args = new Bundle();
        args.putString(QuestionFragment.QUESTION, question.getQuestion());
        args.putSerializable(QuestionFragment.ANSWERS, new ArrayList<>(Arrays.asList(
                question.getAnswerA(),
                question.getAnswerB(),
                question.getAnswerC(),
                question.getAnswerD(),
                question.getAnswerE()
        )));
        args.putInt(QuestionFragment.SOLUTION, question.getSolution().charAt(0) - 65);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return mQuestions.size();
    }
}
