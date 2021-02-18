package com.pdw.assets;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 类描述
 *
 * @author Corgi
 * @date 2021/2/18
 */
public class DateUtils {

    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static String formatDate(long date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.US);
        return simpleDateFormat.format(new Date(date));
    }
}
