package ru.handh.lesson_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Igor Glushkov on 09.08.18.
 */
public class TwoLinesTextView extends LinearLayout {

    private String title;
    private String message;

    private TextView textViewTitle;
    private TextView textViewMessage;

    public TwoLinesTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {
        extractAttrs(attrs);

        inflate(getContext(), R.layout.view_two_lines, this);

        setOrientation(VERTICAL);

        textViewTitle = findViewById(R.id.textViewTitle);
        textViewMessage = findViewById(R.id.textViewMessage);

        textViewTitle.setText(title);
        textViewMessage.setText(message);
    }

    private void extractAttrs(@Nullable AttributeSet attrs) {

        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TwoLinesTextView,
                0, 0);

        try {
            title = a.getString(R.styleable.TwoLinesTextView_title);
            message = a.getString(R.styleable.TwoLinesTextView_message);
        } finally {
            a.recycle();
        }
    }
}
