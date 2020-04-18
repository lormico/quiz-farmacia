package com.github.lormico.quizfarmacia.ui.quiz;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;

public class QuizRadioButton extends androidx.appcompat.widget.AppCompatRadioButton {
    public QuizRadioButton(Context context) {
        super(context);
    }

    public QuizRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public QuizRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void toggle() {

        if (isChecked()) {
            if (getParent() != null && getParent() instanceof RadioGroup) {
                ((RadioGroup) getParent()).clearCheck();
            }
        } else {
            super.toggle();
        }
    }

    @Override
    public CharSequence getAccessibilityClassName() {
        return QuizRadioButton.class.getName();
    }
}
