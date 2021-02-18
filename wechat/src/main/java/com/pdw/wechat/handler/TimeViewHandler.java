package com.pdw.wechat.handler;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pdw.wechat.R;
import com.pdw.wechat.ViewHandler;
import com.pdw.wechat.entity.Message;
import com.pdw.wechat.entity.TimeMessage;

/**
 * 类描述
 *
 * @author Corgi
 * @date 2021/2/18
 */
public class TimeViewHandler implements ViewHandler {
    @Override
    public View getView(Message message) {
        ViewGroup view = (ViewGroup) View.inflate(message.getCtx(), R.layout.view_datetime, null);
        ((TextView)view.findViewById(R.id.tv_time)).setText(((TimeMessage) message).getLocal());
        return view;
    }
}
