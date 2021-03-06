package com.github.lormico.quizfarmacia.ui.quiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.github.lormico.quizfarmacia.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class QuizContainerFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_container, container, false);

        // spostare tutto questo sotto in onViewCreated?
        tabLayout = view.findViewById(R.id.tab_layout_quiz);

        // L'adapter fornisce i Fragment che andranno a popolare il ViewPager
        QuizFragmentStateAdapter quizFragmentStateAdapter = new QuizFragmentStateAdapter(this);
        viewPager = view.findViewById(R.id.pager_quiz);
        viewPager.setAdapter(quizFragmentStateAdapter);

        FloatingActionButton fab = view.findViewById(R.id.fab_quiz);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle(getContext().getString(R.string.quiz_hand_in_confirmation_title));
                alertDialog.setMessage(getContext().getString(R.string.quiz_hand_in_confirmation_msg));
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,
                        getContext().getString(R.string.affirmative),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Navigation.findNavController(view).navigate(R.id.nav_quiz_result);
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,
                        getContext().getString(R.string.negative),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Il Mediator collega il TabLayout al ViewPager
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(String.valueOf(position + 1))
        ).attach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(QuizContainerFragment.class.getSimpleName(), "onDestroyView...");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(QuizContainerFragment.class.getSimpleName(), "onDestroy...");
    }
}
