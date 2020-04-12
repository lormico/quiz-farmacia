package com.github.lormico.quizfarmacia.ui.archive;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.lormico.quizfarmacia.R;
import com.github.lormico.quizfarmacia.persistence.Question;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Estrae dati dal dataset e li utilizza per creare e popolare
 * dei ViewHolder, da inviare al LayoutManager
 */
public class QuestionRecyclerViewAdapter extends RecyclerView.Adapter<QuestionRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Question> dataset;

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView questionText;
        private TextView questionId;
        private TextView answerA;
        private TextView answerB;
        private TextView answerC;
        private TextView answerD;
        private TextView answerE;

        private View subItem;

        ViewHolder(View itemView) {
            super(itemView);

            questionText = itemView.findViewById(R.id.list_item_question_text);
            questionId = itemView.findViewById(R.id.list_item_question_id);
            answerA = itemView.findViewById(R.id.list_item_question_answer_a);
            answerB = itemView.findViewById(R.id.list_item_question_answer_b);
            answerC = itemView.findViewById(R.id.list_item_question_answer_c);
            answerD = itemView.findViewById(R.id.list_item_question_answer_d);
            answerE = itemView.findViewById(R.id.list_item_question_answer_e);
            subItem = itemView.findViewById(R.id.list_item_question_collapsible_item);
        }

        private void bind(Question question) {
            boolean expanded = question.isExpanded();
            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);

            questionText.setText(question.getQuestion());
            questionId.setText("#" + question.getQuestionId());
            answerA.setText("A. " + question.getAnswerA());
            answerB.setText("B. " + question.getAnswerB());
            answerC.setText("C. " + question.getAnswerC());
            answerD.setText("D. " + question.getAnswerD());
            answerE.setText("E. " + question.getAnswerE());

            int correctColor = Color.parseColor("#1FAA00");
            int incorrectColor = Color.parseColor("#8A8A8A");
            String solution = question.getSolution();
            answerA.setTextColor("A".equals(solution) ? correctColor : incorrectColor);
            answerB.setTextColor("B".equals(solution) ? correctColor : incorrectColor);
            answerC.setTextColor("C".equals(solution) ? correctColor : incorrectColor);
            answerD.setTextColor("D".equals(solution) ? correctColor : incorrectColor);
            answerE.setTextColor("E".equals(solution) ? correctColor : incorrectColor);
        }
    }

    public QuestionRecyclerViewAdapter(Serializable questions) {
        dataset = (ArrayList<Question>) questions;
    }

    @Override
    public QuestionRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_question, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Question question = dataset.get(position);
        holder.bind(question);

        holder.itemView.setOnClickListener(v -> {
            boolean expanded = question.isExpanded();
            question.setExpanded(!expanded);
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}


