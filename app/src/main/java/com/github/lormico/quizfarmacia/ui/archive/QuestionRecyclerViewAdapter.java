package com.github.lormico.quizfarmacia.ui.archive;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.lormico.quizfarmacia.R;
import com.github.lormico.quizfarmacia.Util;
import com.github.lormico.quizfarmacia.persistence.Quesito;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Estrae dati dal dataset e li utilizza per creare e popolare
 * dei ViewHolder, da inviare al LayoutManager
 */
public class QuestionRecyclerViewAdapter extends RecyclerView.Adapter<QuestionRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Quesito> dataset;

    static class ViewHolder extends RecyclerView.ViewHolder {
        public View listItemView;
        public ViewHolder(View v) {
            super(v);
            listItemView = v;
        }
    }

    public QuestionRecyclerViewAdapter(Serializable questions) {
        dataset = (ArrayList<Quesito>) questions;
    }

    @Override
    public QuestionRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_question, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Quesito question = dataset.get(position);

        TextView questionText = holder.listItemView.findViewById(R.id.list_item_question_text);
        questionText.setText(question.getDomanda());

        TextView solutionText = holder.listItemView.findViewById(R.id.list_item_question_solution);
        String solutionString = question.getSoluzione() + ". " +
                Util.getSolutionString(question);
        solutionText.setText(solutionString);

        TextView idText = holder.listItemView.findViewById(R.id.list_item_question_id);
        idText.setText("#" + question.getIdDomanda());

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}