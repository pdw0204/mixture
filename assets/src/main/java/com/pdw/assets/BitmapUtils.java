package com.pdw.assets;

import android.graphics.BitmapFactory;
import android.util.Pair;

/**
 * 类描述
 *
 * @author Corgi
 * @date 2021/2/18
 */
public class BitmapUtils {
    public static Pair<Integer, Integer> getWH(String uri) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(uri, options);
        return new Pair<>(options.outWidth, options.outHeight);
    }
}
