package com.pdw.wechat.util;

import android.content.Context;
import android.view.WindowManager;

/**
 * 类描述
 *
 * @author Corgi
 * @date 2021/2/17
 */
public class DisplayUtils {
    private final static int[] WH = new int[2];

    public static int getWindowHeight(Context ctx) {
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }
}
