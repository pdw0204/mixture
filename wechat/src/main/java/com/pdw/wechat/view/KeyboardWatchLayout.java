package com.pdw.wechat.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pdw.wechat.util.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述
 *
 * @author Corgi
 * @date 2021/2/17
 */
public class KeyboardWatchLayout extends FrameLayout {
    private boolean mActive;
    private int mKeyboardHeight;

    private List<KeyboardStateChangedListener> mKeyboardStateChangedListeners = new ArrayList<>();

    public KeyboardWatchLayout(@NonNull Context context) {
        this(context, null);
    }

    public KeyboardWatchLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KeyboardWatchLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void addKeyboardStateChangedListener(KeyboardStateChangedListener listener) {
        mKeyboardStateChangedListeners.add(listener);
    }

    private void init() {
        getViewTreeObserver().addOnGlobalLayoutListener(new KeyboardChangeListener(this, (active, keyboardHeight) -> {
            mActive = active;
            mKeyboardHeight = keyboardHeight;
            Log.e("pdw", "keyboard height:" + keyboardHeight + "(" + mActive + ")");

            for (KeyboardStateChangedListener listener : mKeyboardStateChangedListeners) {
                listener.onKeyboardStateChanged(active, keyboardHeight);
            }
        }));
    }

    private static class KeyboardChangeListener implements ViewTreeObserver.OnGlobalLayoutListener {
        private final View mWatchView;
        private final Rect mHintRect = new Rect();
        private final int mScreenHeight;
        private final KeyboardStateChangedListener mListener;

        KeyboardChangeListener(View mWatchView, KeyboardStateChangedListener listener) {
            this.mWatchView = mWatchView;
            mScreenHeight = DisplayUtils.getWindowHeight(mWatchView.getContext());
            this.mListener = listener;
        }

        @Override
        public void onGlobalLayout() {
            Log.e("pdw", "onGlobalLayout");
            mWatchView.getWindowVisibleDisplayFrame(mHintRect);

            int keyboardHeight = mScreenHeight - mHintRect.bottom;
            boolean active = false;
            if (keyboardHeight > mScreenHeight / 5) {
                active = true;
            }

            mListener.onKeyboardStateChanged(active, keyboardHeight);
        }
    }

    public interface KeyboardStateChangedListener {
        void onKeyboardStateChanged(boolean active, int keyboardHeight);
    }
}
