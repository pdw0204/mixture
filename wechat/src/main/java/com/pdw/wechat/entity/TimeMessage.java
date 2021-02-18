package com.pdw.wechat.entity;

import android.content.Context;

import com.pdw.assets.DateUtils;
import com.pdw.wechat.ViewHandler;
import com.pdw.wechat.handler.TimeViewHandler;

/**
 * 类描述
 *
 * @author Corgi
 * @date 2021/2/18
 */
public class TimeMessage extends Message {

    private long time;

    public TimeMessage(Context ctx) {
        time = System.currentTimeMillis();
        setCtx(ctx);
    }

    public String getLocal() {
        return DateUtils.formatDate(time, DateUtils.FORMAT_YYYY_MM_DD_HH_MM_SS);
    }

    @Override
    public ViewHandler getViewHandler() {
        return new TimeViewHandler();
    }
}
