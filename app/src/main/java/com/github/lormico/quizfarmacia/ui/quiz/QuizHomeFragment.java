package com.github.lormico.quizfarmacia.ui.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.github.lormico.quizfarmacia.R;

public class QuizHomeFragment extends Fragment {

    private QuizViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_home, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(QuizViewModel.class);

        final Button button = view.findViewById(R.id.quiz_start_button);
/*        viewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                button.setText(s);
            }
        });*/

        button.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.nav_quiz_container);
        });

        return view;
    }
}
