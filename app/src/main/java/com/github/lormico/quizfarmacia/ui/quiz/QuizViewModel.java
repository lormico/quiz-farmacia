package com.github.lormico.quizfarmacia.ui.quiz;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QuizViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public QuizViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is quiz home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}