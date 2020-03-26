package com.github.lormico.quizfarmacia.ui.archive;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.github.lormico.quizfarmacia.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * Classe per gestire la schermata principale: dovrò visualizzare
 * tutti i quesiti, suddivisi in schede a seconda della materia,
 * ordinati per id.
 */
public class ArchiveFragment extends Fragment {

    ArchiveFragmentStateAdapter archiveFragmentStateAdapter;
    ViewPager2 viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_archive, container, false);
        // spostare tutto questo sotto in onViewCreated?
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_archive);

        archiveFragmentStateAdapter = new ArchiveFragmentStateAdapter(this);
        viewPager = (ViewPager2) view.findViewById(R.id.pager_archive);
        viewPager.setAdapter(archiveFragmentStateAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TabLayout tabLayout = view.findViewById(R.id.tab_layout_archive);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText("OBJECT " + (position + 1))
        ).attach();
    }
}
