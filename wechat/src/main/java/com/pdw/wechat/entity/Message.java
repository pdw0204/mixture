package com.pdw.wechat.entity;

import android.content.Context;

import androidx.annotation.IntDef;

import com.pdw.wechat.ViewHandler;

/**
 * 类描述
 *
 * @author Corgi
 * @date 2021/2/16
 */
public abstract class Message {

    public static final int TEXT = 1;
    public static final int IMAGE = 2;
    public static final int VIDEO = 3;
    public static final int VOICE = 4;

    protected long id;
    protected long from;
    protected long to;
    protected Context ctx;
    protected int type;

    public long from() {
        return from;
    }

    public long to() {
        return to;
    }

    public Message setFrom(long from) {
        this.from = from;
        return this;
    }

    public Message setTo(long to) {
        this.to = to;
        return this;
    }

    public Context getCtx() {
        return ctx;
    }

    public int getType() {
        return type;
    }

    public Message setCtx(Context ctx) {
        this.ctx = ctx;
        return this;
    }

    public Message setType(int type) {
        this.type = type;
        return this;
    }

    public long getId() {
        return id;
    }

    public Message setId(long id) {
        this.id = id;
        return this;
    }

    public abstract ViewHandler getViewHandler();
}
