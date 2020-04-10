package com.github.lormico.quizfarmacia.ui.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.lormico.quizfarmacia.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionFragment extends Fragment {

    public static final String QUESTION = "question";
    public static final String ANSWERS = "answers";
    public static final String SOLUTION = "solution";

    private List<Integer> viewIds = Arrays.asList(
            R.id.quiz_answer_a,
            R.id.quiz_answer_b,
            R.id.quiz_answer_c,
            R.id.quiz_answer_d,
            R.id.quiz_answer_e
    );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        TextView questionTextView = view.findViewById(R.id.quiz_question_text);
        RadioGroup answersRadioGroup = view.findViewById(R.id.quiz_answer_group);

        Bundle args = getArguments();
        questionTextView.setText(args.getString(QUESTION));
        ArrayList<String> answers = (ArrayList<String>) args.getSerializable(ANSWERS);
        for (int i = 0; i < answers.size(); i++) {
            RadioButton answerRadioButton = view.findViewById(viewIds.get(i));
            answerRadioButton.setText(answers.get(i));
        }

        return view;
    }
}
