package com.github.lormico.quizfarmacia.ui.quiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.github.lormico.quizfarmacia.persistence.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuizFragmentStateAdapter extends FragmentStateAdapter {

    private List<Question> mQuestions;

    public QuizFragmentStateAdapter(@NonNull Fragment fragment) {
        super(fragment);
        QuizViewModel viewModel = new ViewModelProvider(fragment.requireActivity())
                .get(QuizViewModel.class);
        mQuestions = viewModel.getRandomizedQuestions();
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
