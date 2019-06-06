package ru.handh.lesson8;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class PrefsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefs);

        SharedPreferences prefs = getSharedPreferences("prefs", Context.MODE_PRIVATE);

        prefs.edit()
                .putInt("AGE", 18)
                .apply();

        int age = prefs.getInt("AGE", 0);

    }
}
