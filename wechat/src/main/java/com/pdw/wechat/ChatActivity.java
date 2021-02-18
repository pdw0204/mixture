package com.pdw.wechat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pdw.wechat.view.KeyboardWatchLayout;

/**
 * 类描述
 *
 * @author Corgi
 * @date 2021/2/16
 */
public class ChatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        KeyboardWatchLayout container = findViewById(R.id.container);
        ChatFragment chatFragment = new ChatFragment();
        container.addKeyboardStateChangedListener(chatFragment);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, chatFragment, "chat_fragment")
                .commitAllowingStateLoss();

        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.e("pdw", "activity:onGlobalLayout");
            }
        });

        getWindow().getDecorView().getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                Log.e("pdw", "activity:onGlobalFocusChanged");
            }
        });
    }
}
