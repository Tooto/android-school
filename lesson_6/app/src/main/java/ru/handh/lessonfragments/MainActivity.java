package ru.handh.lessonfragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements Fragment1.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onFragmentInteraction(String text) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.viewContainer, Fragment2.newInstance(text))
                .addToBackStack(null)
                .commit();
    }
}
