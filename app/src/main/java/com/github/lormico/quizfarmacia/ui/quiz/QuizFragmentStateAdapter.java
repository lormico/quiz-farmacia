package com.github.lormico.quizfarmacia.ui.quiz;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.github.lormico.quizfarmacia.persistence.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizFragmentStateAdapter extends FragmentStateAdapter {

    private List<Question> mQuestions;

    public QuizFragmentStateAdapter(@NonNull Fragment fragment) {
        super(fragment);
        Log.d(this.getClass().getSimpleName(), "creating instance");
        QuizViewModel viewModel = new ViewModelProvider(fragment.requireActivity())
                .get(QuizViewModel.class);

        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(fragment.getContext());
        Map<String, String> subjectNumbersMap = new HashMap<>(); // TODO rename variable
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
        mQuestions = viewModel.generateRandomizedQuestions(subjectNumbersMap);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new QuestionFragment();
        Question question = mQuestions.get(position);

        Bundle args = new Bundle();
        args.putInt(QuestionFragment.POSITION, position);
        args.putString(QuestionFragment.SUBJECT, question.getSubject());
        args.putInt(QuestionFragment.QUESTION_ID, question.getQuestionId());

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return mQuestions.size();
    }
}
