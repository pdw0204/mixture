package com.pdw.wechat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.pdw.wechat.view.KeyboardWatchLayout;

/**
 * 类描述
 *
 * @author Corgi
 * @date 2021/2/16
 */
public class ChatActivity extends AppCompatActivity {
    public final static int PERMISSION_REQUEST_CODE = 10001;

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

    @Override
    protected void onResume() {
        super.onResume();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
