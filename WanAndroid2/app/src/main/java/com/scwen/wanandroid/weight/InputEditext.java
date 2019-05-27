package com.scwen.wanandroid.weight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;
import com.scwen.wanandroid.R;

/**
 * Created by Administrator on 2019/3/11.
 * QQ ：1210257136
 * 作用：自定义Edittext输入显示密码和清空内容
 */

@SuppressLint("AppCompatCustomView")
public class InputEditext extends EditText {
    private static final String TAG = "INputEdittext";
    private AttributeSet mAttrs;
    private Drawable mCloseDrawable;
    private Drawable mPasswordTogDrawableOpen;
    private Drawable passwordTogDrawableClose;
    private boolean mCloseEnabled;
    private boolean mPasswordEnabled = false;
    private boolean mPasswordVisible = false;

    Paint mPaint;
    Paint mPaint2;
//    Bitmap clearBtm;
//    Bitmap passwrodBtm1;
//    Bitmap passwrodBtm2;
//    private Drawable mPasswordDrawable2;


    public InputEditext(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
        Log.d(TAG, "InputEditext: " + 2);
    }

    private void init(Context context, AttributeSet attrs) {
        mPaint = getPaint();
        mPaint2 = getPaint();
        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inJustDecodeBounds = true;

        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.inputAttrs);
            mPasswordTogDrawableOpen = ta.getDrawable(R.styleable.inputAttrs_passwordTogDrawableOpen);
            passwordTogDrawableClose = ta.getDrawable(R.styleable.inputAttrs_passwordTogDrawableClose);
            mCloseDrawable = ta.getDrawable(R.styleable.inputAttrs_closeTogDrawable);

            if (mCloseDrawable != null) {
                mCloseDrawable.setBounds(0, 0, mCloseDrawable.getIntrinsicWidth(), mCloseDrawable.getIntrinsicHeight());
            }

            if (mPasswordTogDrawableOpen != null) {
                mPasswordTogDrawableOpen.setBounds(0, 0, mPasswordTogDrawableOpen.getIntrinsicWidth(), mPasswordTogDrawableOpen.getIntrinsicHeight());
            }
            if (passwordTogDrawableClose != null) {
                passwordTogDrawableClose.setBounds(0, 0, passwordTogDrawableClose.getIntrinsicWidth(), passwordTogDrawableClose.getIntrinsicHeight());
            }

            mCloseEnabled = ta.getBoolean(R.styleable.inputAttrs_closeTogEnabled, false);
            mPasswordEnabled = ta.getBoolean(R.styleable.inputAttrs_passwordTogEnabled, false);

            ta.recycle();
        }

//        clearBtm = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.btn_close);
//        passwrodBtm1 = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.btn_attention1);
//        passwrodBtm2 = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.btn_attention2);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCloseEnabled) {
            if (getText().toString().length() > 0)
                closeDraw(canvas);
//            canvas.drawBitmap(clearBtm, getWidth() - clearBtm.getWidth(), getHeight() / 2 - clearBtm.getHeight() / 2, mPaint);
        }

        if (mPasswordEnabled && getText().toString().length() > 0) {
            if (mPasswordVisible) {
//                canvas.drawBitmap(passwrodBtm2, getWidth() - passwrodBtm2.getWidth() * 2 - 12, getHeight() / 2 - passwrodBtm2.getHeight() / 2, mPaint2);
                passwrodDrawOpen(canvas);
            } else {
//                canvas.drawBitmap(passwrodBtm1, getWidth() - passwrodBtm1.getWidth() * 2 - 12, getHeight() / 2 - passwrodBtm1.getHeight() / 2, mPaint2);
                passwrodDrawClose(canvas);
            }
        }
    }

    private void closeDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(getWidth() - mCloseDrawable.getIntrinsicWidth(), getHeight() / 2 - mCloseDrawable.getIntrinsicHeight() / 2);
        if (mCloseDrawable != null) {
            mCloseDrawable.draw(canvas);
        }
        canvas.restore();
    }

    private void passwrodDrawOpen(Canvas canvas) {
        canvas.save();
        canvas.translate(getWidth() - mPasswordTogDrawableOpen.getIntrinsicWidth() * 2 - 15, getHeight() / 2 - mPasswordTogDrawableOpen.getIntrinsicHeight() / 2);
        if (mPasswordTogDrawableOpen != null) {
            mPasswordTogDrawableOpen.draw(canvas);
        }
        canvas.restore();
    }

    private void passwrodDrawClose(Canvas canvas) {
        canvas.save();
        canvas.translate(getWidth() - passwordTogDrawableClose.getIntrinsicWidth() * 2 - 15, getHeight() / 2 - passwordTogDrawableClose.getIntrinsicHeight() / 2);
        if (passwordTogDrawableClose != null) {
            passwordTogDrawableClose.draw(canvas);
        }
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (mCloseEnabled
                        && event.getX() > getWidth() - mCloseDrawable.getIntrinsicWidth()) {
                    setText("");
                    return true;
                }

                if (mPasswordEnabled
                        && event.getX() > getWidth() - mCloseDrawable.getIntrinsicWidth() * 2 - 15
                        && event.getY() > 0
                        && event.getY() < getHeight()
                        ) {
                    if (mPasswordVisible) {
                        setTransformationMethod(PasswordTransformationMethod.getInstance());
                        mPasswordVisible = false;
                    } else {
                        setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        mPasswordVisible = true;
                    }
                    return true;
                }
                break;

        }

        return super.onTouchEvent(event);
    }
}
