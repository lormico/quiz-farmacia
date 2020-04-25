package com.github.lormico.quizfarmacia.ui.settings;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.lormico.quizfarmacia.R;

// TODO informare l'utente che le modifiche avranno effetto solo al prossimo quiz (o fornire la possibilità di riavviare subito?)
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
}
