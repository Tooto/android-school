package ru.handh.lesson_view;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.transitionseverywhere.TransitionManager;

public class MainActivity extends AppCompatActivity {


    boolean changed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //((MyCustomView)findViewById(R.id.myView)).setText("asdasdasdasdasdas");

                boolean enabled = false;
                if (enabled) {
                    AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getBaseContext(),
                            R.animator.property_animator);
                    set.setTarget(findViewById(R.id.textView));
                    set.start();
                    return;
                }

                if (enabled) {
                    ValueAnimator xmlAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(getBaseContext(),
                            R.animator.value_animator);
                    xmlAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator updatedAnimation) {
                            float animatedValue = (float) updatedAnimation.getAnimatedValue();
                            findViewById(R.id.textView).setTranslationX(animatedValue);
                        }
                    });

                    xmlAnimator.start();
                }
            }
        });


        final ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);

        final ConstraintSet constraintSet1 = new ConstraintSet();
        constraintSet1.clone(constraintLayout);

        final ConstraintSet constraintSet2 = new ConstraintSet();
        constraintSet2.clone(MainActivity.this, R.layout.alt);

        findViewById(R.id.twoLinesTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(constraintLayout);
                ConstraintSet constraint = changed ? constraintSet1 : constraintSet2;
                constraint.applyTo(constraintLayout);
                changed = !changed;
            }
        });
    }
}
