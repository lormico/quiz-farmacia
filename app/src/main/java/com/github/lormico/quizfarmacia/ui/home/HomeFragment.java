package com.github.lormico.quizfarmacia.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import com.github.lormico.quizfarmacia.R;
import com.google.android.material.tabs.TabLayout;

/**
 * Classe per gestire la schermata principale: dovrò visualizzare
 * tutti i quesiti, suddivisi in schede a seconda della materia,
 * ordinati per id.
 */
public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_home);
        tabLayout.addTab(tabLayout.newTab().setText("AO"));
        tabLayout.addTab(tabLayout.newTab().setText("Tester Toasteroni"));
        tabLayout.addTab(tabLayout.newTab().setText("Humma Guvula"));
        tabLayout.addTab(tabLayout.newTab().setText("Raperino de Raperinis"));

        final ViewPager2 viewPager = (ViewPager2) view.findViewById(R.id.pager_home);
        return view;
    }
}
