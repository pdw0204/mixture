package com.pdw.wechat.entity;

import com.pdw.wechat.ViewHandler;
import com.pdw.wechat.handler.TextViewHandler;

/**
 * 类描述
 *
 * @author Corgi
 * @date 2021/2/16
 */
public class TextMessage extends Message {

    private String text;

    public TextMessage() {
        type = TEXT;
    }

    public String getText() {
        return text;
    }

    public TextMessage setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public ViewHandler getViewHandler() {
        return new TextViewHandler();
    }
}
