package com.github.lormico.quizfarmacia.ui.quiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class QuizFragmentStateAdapter extends FragmentStateAdapter {

    public QuizFragmentStateAdapter(@NonNull Fragment fragment) {
        super(fragment);
        QuizViewModel viewModel = new ViewModelProvider(fragment.requireActivity())
                .get(QuizViewModel.class);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new QuestionFragment();

        Bundle args = new Bundle();
        args.putString(QuestionFragment.QUESTION, "domanda");
        args.putSerializable(QuestionFragment.ANSWERS, new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e")));
        args.putInt(QuestionFragment.SOLUTION, 4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
