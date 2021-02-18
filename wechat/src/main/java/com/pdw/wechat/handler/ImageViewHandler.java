package com.pdw.wechat.handler;

import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pdw.assets.BitmapUtils;
import com.pdw.assets.glide.GlideRoundTransformation;
import com.pdw.wechat.R;
import com.pdw.wechat.ViewHandler;
import com.pdw.wechat.entity.MediaMessage;
import com.pdw.wechat.entity.Message;
import com.pdw.wechat.entity.TextMessage;
import com.pdw.wechat.entity.User;

import java.io.File;

/**
 * 类描述
 *
 * @author Corgi
 * @date 2021/2/16
 */
public class ImageViewHandler implements ViewHandler {
    @Override
    public View getView(Message message) {
        int resId;
        boolean isMe = User.isMe(message.from());
        if (isMe) {
            resId = R.layout.view_image_message_sended;
        } else {
            resId = R.layout.view_image_message_received;
        }
        View view = View.inflate(message.getCtx(), resId, null);
        ((ImageView) view.findViewById(R.id.iv_icon)).setImageResource(isMe ? R.mipmap.ic_user_me : R.mipmap.ic_user_default);
        ImageView image = view.findViewById(R.id.iv_message_content);
        String uri = ((MediaMessage) message).getUri();
        Pair<Integer, Integer> wh = BitmapUtils.getWH(uri);
        ViewGroup.LayoutParams lp = image.getLayoutParams();
        lp.width = message.getCtx().getResources().getDimensionPixelSize(R.dimen.dp_100);
        lp.height = (int) (lp.width * wh.second * 1.f / wh.first);
        image.setLayoutParams(lp);
        Glide.with(image).load(new File(uri)).apply(new RequestOptions()
                .transform(new GlideRoundTransformation(message.getCtx()))).into(image);
        return view;
    }
}
