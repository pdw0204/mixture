package com.pdw.wechat.handler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pdw.wechat.R;
import com.pdw.wechat.ViewHandler;
import com.pdw.wechat.entity.Message;
import com.pdw.wechat.entity.TextMessage;
import com.pdw.wechat.entity.User;

/**
 * 类描述
 *
 * @author Corgi
 * @date 2021/2/16
 */
public class TextViewHandler implements ViewHandler {
    @Override
    public View getView(Message message) {
        int resId;
        boolean isMe = User.isMe(message.from());
        if (isMe) {
            resId = R.layout.view_text_message_sended;
        } else {
            resId = R.layout.view_text_message_received;
        }
        View view = View.inflate(message.getCtx(), resId, null);
        ((ImageView) view.findViewById(R.id.iv_icon)).setImageResource(isMe ? R.mipmap.ic_user_me : R.mipmap.ic_user_default);
        ((TextView) view.findViewById(R.id.tv_message_content)).setText(((TextMessage) message).getText());
        return view;
    }
}
