package com.github.lormico.quizfarmacia.ui.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.github.lormico.quizfarmacia.R;
import com.github.lormico.quizfarmacia.persistence.Question;

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
        QuizViewModel viewModel = new ViewModelProvider(requireActivity()).get(QuizViewModel.class);
        List<AnsweredQuestion> answeredQuestions = viewModel.getAnsweredQuestions();
        int unanswered, ok, failed;
        unanswered = ok = failed = 0;
        for (int position = 0; position < answeredQuestions.size(); position++) {
            AnsweredQuestion answeredQuestion = answeredQuestions.get(position);
            if (answeredQuestion.getAnswer() == -1) {
                unanswered++;
            } else {
                Question question = viewModel.getQuestion(
                        answeredQuestion.getSubject(),
                        answeredQuestion.getQuestionId()
                );
                int solutionIndex = question.getSolution().charAt(0) - 65;
                if (answeredQuestion.getAnswer() == solutionIndex) {
                    ok++;
                } else {
                    failed++;
                }
            }
        }
        double score = ok - (0.25 * failed);
        String scoreString = getContext().getString(R.string.quiz_results_score, score);
        String okString = getContext().getString(R.string.quiz_results_num_ok, ok);
        String failedString = getContext().getString(R.string.quiz_results_num_failed, failed);
        String unansweredString = getContext().getString(R.string.quiz_results_num_unanswered, unanswered);

        TextView scoreText = view.findViewById(R.id.quiz_results_score);
        TextView okText = view.findViewById(R.id.quiz_results_num_ok);
        TextView failedText = view.findViewById(R.id.quiz_results_num_failed);
        TextView unansweredText = view.findViewById(R.id.quiz_results_num_unanswered);

        scoreText.setText(scoreString);
        okText.setText(okString);
        failedText.setText(failedString);
        unansweredText.setText(unansweredString);

        return view;
    }
}
