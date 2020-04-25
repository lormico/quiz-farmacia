package com.github.lormico.quizfarmacia.ui.quiz;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.github.lormico.quizfarmacia.persistence.Question;

import java.util.List;

public class QuizFragmentStateAdapter extends FragmentStateAdapter {

    private List<Question> mQuestions;

    public QuizFragmentStateAdapter(@NonNull Fragment fragment) {
        super(fragment);
        Log.d(this.getClass().getSimpleName(), "creating instance");
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
