package ru.handh.lessonactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ParamsActivity extends AppCompatActivity {

    private final static String EXTRA_TITLE = "title";

    public static Intent createInstance(Context context, String title) {
        Intent intent = new Intent(context, ParamsActivity.class);
        intent.putExtra(EXTRA_TITLE, title);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_params);
        TextView textView = findViewById(R.id.textViewParams);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            textView.setText(extras.getString(EXTRA_TITLE));
        }
    }
}
