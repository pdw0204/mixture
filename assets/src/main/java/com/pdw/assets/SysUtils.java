package com.pdw.assets;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;

import androidx.fragment.app.Fragment;

/**
 * 类描述
 *
 * @author Corgi
 * @date 2021/2/18
 */
public class SysUtils {
    public static void openGallery(Fragment ctx, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        ctx.startActivityForResult(intent, requestCode);
    }

    public static void openGallery(Activity ctx, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        ctx.startActivityForResult(intent, requestCode);
    }

    public static Activity findActivity(Context ctx) {
        while (ctx instanceof ContextWrapper) {
            if (ctx instanceof Activity) {
                return (Activity) ctx;
            }
            ctx = ((ContextWrapper) ctx).getBaseContext();
        }
        return null;
    }
}
