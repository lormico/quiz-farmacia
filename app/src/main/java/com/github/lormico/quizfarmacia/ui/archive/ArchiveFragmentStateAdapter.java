package com.github.lormico.quizfarmacia.ui.archive;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ArchiveFragmentStateAdapter extends FragmentStateAdapter {
    public ArchiveFragmentStateAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new SubjectFragment();
        Bundle args = new Bundle();
        args.putInt(SubjectFragment.ARG_OBJECT, position + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
