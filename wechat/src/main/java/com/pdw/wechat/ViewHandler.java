package com.pdw.wechat;

import android.view.View;

import com.pdw.wechat.entity.Message;

/**
 * 类描述
 *
 * @author Corgi
 * @date 2021/2/16
 */
public interface ViewHandler {
    View getView(Message message);
}
