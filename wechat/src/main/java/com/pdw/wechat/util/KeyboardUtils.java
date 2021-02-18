package com.pdw.wechat.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 类描述
 *
 * @author Corgi
 * @date 2021/2/17
 */
public class KeyboardUtils {
    public static void hideKeyboard(Context ctx, View input) {
        InputMethodManager inputMethodManager = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(input.getWindowToken(), 0);
    }

    public static void showKeyboard(Context ctx, View input) {
        input.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(input, 0);
    }
}
