package com.github.lormico.quizfarmacia.ui.quiz;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.github.lormico.quizfarmacia.R;
import com.github.lormico.quizfarmacia.persistence.Question;

import java.util.Arrays;
import java.util.List;

public class QuestionFragment extends Fragment {

    public static final String POSITION = "position";
    public static final String SUBJECT = "subject";
    public static final String QUESTION_ID = "questionId";

    private QuizViewModel viewModel;
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
        viewModel = new ViewModelProvider(requireActivity()).get(QuizViewModel.class);

        Bundle args = getArguments();
        int position = args.getInt(POSITION);
        String subject = args.getString(SUBJECT);
        int questionId = args.getInt(QUESTION_ID);

        Log.d(QuestionFragment.class.getSimpleName(), "Querying the DB for question [" +
                subject + ":" + questionId + "]");
        Question question = viewModel.getQuestion(subject, questionId);
        questionTextView.setText(question.getQuestion());
        String[] answers = question.getAnswers();
        for (int i = 0; i < answers.length; i++) {
            QuizRadioButton answerRadioButton = view.findViewById(viewIds.get(i));
            answerRadioButton.setText(answers[i]);
        }

        RadioGroup answersRadioGroup = view.findViewById(R.id.quiz_answer_group);
        answersRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.d("answersRadioGroup.OnCheckedChangeListener", "Changing answer to question");
                int answer = viewIds.contains(checkedId) ? viewIds.indexOf(checkedId) : -1;
                viewModel.setAnswer(position, answer);
            }
        });

        TextView subjectText = view.findViewById(R.id.quiz_subject);
        TextView questionIdText = view.findViewById(R.id.quiz_question_id);
        String questionIdString = getContext().getString(R.string.question_id_placeholder, questionId);
        subjectText.setText(subject);
        questionIdText.setText(questionIdString);

        return view;
    }
}
