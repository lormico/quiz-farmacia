package com.github.lormico.quizfarmacia.ui.settings;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceFragmentCompat;

import com.github.lormico.quizfarmacia.R;

import java.util.Arrays;
import java.util.List;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings_pref);

        List<String> numberPreferences = Arrays.asList(
                "num_questions_biology",
                "num_questions_general_chemistry",
                "num_questions_organic_chemistry",
                "num_questions_physics",
                "num_questions_maths"
        );
        for (String numberPreference : numberPreferences) {
            EditTextPreference preference = findPreference(numberPreference);

            if (preference != null) {
                preference.setOnBindEditTextListener(
                        new EditTextPreference.OnBindEditTextListener() {
                            @Override
                            public void onBindEditText(@NonNull EditText editText) {
                                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                            }
                        });
            }
        }
    }
}
