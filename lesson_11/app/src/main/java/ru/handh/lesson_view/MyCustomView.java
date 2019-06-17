package ru.handh.lesson_view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import androidx.annotation.Nullable;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FlingAnimation;

/**
 * Created by Igor Glushkov on 02.08.18.
 */
class MyCustomView extends View {

    private boolean showText;
    private int textPos;
    private String text;

    private int diameter;
    @Nullable private ValueAnimator animation;

    private Paint textPaint;
    private Paint circlePaint;
    Rect textBounds = new Rect();

    GestureDetector gestureDetector;

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.MyCustomView,
                0, 0);

        try {
            showText = a.getBoolean(R.styleable.MyCustomView_showText, false);
            textPos = a.getInteger(R.styleable.MyCustomView_labelPosition, 0);
            text = a.getString(R.styleable.MyCustomView_text);
            if (text == null) {
                text = "";
            }
        } finally {
            a.recycle();
        }

        init();
    }

    private void init() {

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.RED);
        int spSize = 16;
        float scaledSizeInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spSize, getResources().getDisplayMetrics());
        textPaint.setTextSize(scaledSizeInPixels);

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(Color.BLUE);

        gestureDetector = new GestureDetector(getContext(), gestureListener);
    }

    public boolean isShowText() {
        return showText;
    }

    public void setShowText(boolean showText) {
        this.showText = showText;
        //перерисовка
        invalidate();
        //показать диаграмму https://stackoverflow.com/questions/13856180/usage-of-forcelayout-requestlayout-and-invalidate
        //requestLayout();
    }

    public void setText(String text) {
        this.text = text;
        //перерисовка
        invalidate();
        //показать диаграмму https://stackoverflow.com/questions/13856180/usage-of-forcelayout-requestlayout-and-invalidate
        //requestLayout();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d("LES", "onSizeChanged w = " + w + ", h = " + h);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d("LES", "onMeasure");

        textPaint.getTextBounds(text, 0, text.length(), textBounds);

        int minw = getPaddingLeft() + getPaddingRight() + textBounds.width();
        int w = resolveSizeAndState(minw, widthMeasureSpec, 0);

        int minh = getPaddingBottom() + getPaddingTop() + textBounds.height();
        int h = resolveSizeAndState(minh, heightMeasureSpec, 0);

        Log.d("LES", "w= " + w + ", h= " + h);

        int side = Math.max(w, h);

        setMeasuredDimension(side, side);
        //setMeasuredDimension(w, h);

        // пояснялка
//        int desiredWidth = 100;
//        int desiredHeight = 100;
//
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//
//        int width;
//        int height;
//
//        //Measure Width
//        if (widthMode == MeasureSpec.EXACTLY) {
//            //Must be this size
//            width = widthSize;
//        } else if (widthMode == MeasureSpec.AT_MOST) {
//            //Can't be bigger than...
//            width = Math.min(desiredWidth, widthSize);
//        } else {
//            //Be whatever you want
//            width = desiredWidth;
//        }
//
//        //Measure Height
//        if (heightMode == MeasureSpec.EXACTLY) {
//            //Must be this size
//            height = heightSize;
//        } else if (heightMode == MeasureSpec.AT_MOST) {
//            //Can't be bigger than...
//            height = Math.min(desiredHeight, heightSize);
//        } else {
//            //Be whatever you want
//            height = desiredHeight;
//        }
//
//        //MUST CALL THIS
//        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("LES", "onDraw");
        super.onDraw(canvas);

        canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, diameter / 2, circlePaint);


        Paint.FontMetrics fm = textPaint.getFontMetrics();
        canvas.drawText(text, 0, textBounds.height() - fm.descent, textPaint);

        //https://stackoverflow.com/questions/3654321/measuring-text-height-to-be-drawn-on-canvas-android
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

//        // TODO показать документацию, рассказать про типы кликов
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            toggleMyAnimation();
            return true;
        } else {
            return false;
        }

        //TODO про физические анимации, как либа
       // return gestureDetector.onTouchEvent(event);
    }

    private void toggleMyAnimation() {

        boolean enabled = false;
        if (enabled) {
            //ValueAnimator
            if (animation != null) {
                animation.cancel();
                animation = null;
            } else {
                animation = ValueAnimator.ofInt(0, getWidth());
                animation.setDuration(1000);
                animation.setRepeatMode(ValueAnimator.REVERSE);
                animation.setRepeatCount(ValueAnimator.INFINITE);
                animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        diameter = (int) animation.getAnimatedValue();
                        invalidate();
                    }
                });
                animation.start();
            }
            return;
        }

        if (enabled) {
            //OBJECT ANIMATOR, рссказать про геттеры и сеттеры, интерполятор, слушатель
            ObjectAnimator transitionAnimator = ObjectAnimator.ofFloat(this, "translationX", 0, 300);
            transitionAnimator.setDuration(1000);
            transitionAnimator.setInterpolator(new OvershootInterpolator());
            transitionAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationCancel(Animator animation) {
                    super.onAnimationCancel(animation);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                    super.onAnimationRepeat(animation);
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                }

                @Override
                public void onAnimationPause(Animator animation) {
                    super.onAnimationPause(animation);
                }

                @Override
                public void onAnimationResume(Animator animation) {
                    super.onAnimationResume(animation);
                }
            });
            transitionAnimator.start();
            return;
        }

        if (enabled) {
            // ANIMATION SET
            ObjectAnimator fadeOut = ObjectAnimator.ofFloat(this, "alpha", 1f, 0f);
            fadeOut.setDuration(2000);

            ObjectAnimator fadeIn = ObjectAnimator.ofFloat(this, "alpha", 0f, 1f);
            fadeIn.setDuration(2000);

            final AnimatorSet mAnimationSet = new AnimatorSet();
            mAnimationSet.play(fadeIn).after(fadeOut);
            mAnimationSet.start();
            return;
        }
    }


    //PHYSICAL BASED ANIMATIONS
    private GestureDetector.OnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener() {

        //Constants
        private static final int MIN_DISTANCE_MOVED = 50;
        private static final float MIN_TRANSLATION = 0;
        private static final float FRICTION = 1.1f;

        @Override
        public boolean onDown(MotionEvent arg0) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float velocityX, float velocityY) {
            //downEvent : when user puts his finger down on the view
            //moveEvent : when user lifts his finger at the end of the movement
            float distanceInX = Math.abs(moveEvent.getRawX() - downEvent.getRawX());
            float distanceInY = Math.abs(moveEvent.getRawY() - downEvent.getRawY());

            //mTvFlingDistance.setText("distanceInX : " + distanceInX + "\n" + "distanceInY : " + distanceInY);
            float maxTranslationX = ((ViewGroup) MyCustomView.this.getParent()).getWidth() - getWidth();
            float maxTranslationY = ((ViewGroup) MyCustomView.this.getParent()).getHeight() - getHeight();

            if (distanceInX > MIN_DISTANCE_MOVED) {
                //Fling Right/Left
                FlingAnimation flingX = new FlingAnimation(MyCustomView.this, DynamicAnimation.TRANSLATION_X);
                flingX.setStartVelocity(velocityX)
                        .setMinValue(MIN_TRANSLATION) // minimum translationX property
                        .setMaxValue(maxTranslationX)  // maximum translationX property
                        .setFriction(FRICTION)
                        .start();
            } else if (distanceInY > MIN_DISTANCE_MOVED) {
                //Fling Down/Up
                FlingAnimation flingY = new FlingAnimation(MyCustomView.this, DynamicAnimation.TRANSLATION_Y);
                flingY.setStartVelocity(velocityY)
                        .setMinValue(MIN_TRANSLATION)  // minimum translationY property
                        .setMaxValue(maxTranslationY) // maximum translationY property
                        .setFriction(FRICTION)
                        .start();
            }

            return true;
        }
    };
}