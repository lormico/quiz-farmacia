package com.github.lormico.quizfarmacia.ui.archive;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.github.lormico.quizfarmacia.R;
import com.github.lormico.quizfarmacia.persistence.QuesitoViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

/**
 * Classe per gestire la schermata principale: dovrò visualizzare
 * tutti i quesiti, suddivisi in schede a seconda della materia,
 * ordinati per id.
 */
public class ArchiveFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_archive, container, false);
        // spostare tutto questo sotto in onViewCreated?
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_archive);

        // L'adapter fornisce i Fragment che andranno a popolare il ViewPager
        ArchiveFragmentStateAdapter archiveFragmentStateAdapter = new ArchiveFragmentStateAdapter(this);
        viewPager = (ViewPager2) view.findViewById(R.id.pager_archive);
        viewPager.setAdapter(archiveFragmentStateAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /* TODO avere questo anche nel FragmentStateAdapter è ridondante? È meglio definirlo
           solo da una parte? */
        QuesitoViewModel viewModel = new ViewModelProvider(requireActivity())
                .get(QuesitoViewModel.class);
        List<String> subjectList = viewModel.getSubjectList();

        // Il Mediator collega il TabLayout al ViewPager
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(subjectList.get(position))
        ).attach();
    }
}
