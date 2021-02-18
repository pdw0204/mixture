package com.pdw.wechat.entity;


import com.pdw.wechat.ViewHandler;
import com.pdw.wechat.handler.ImageViewHandler;

/**
 * 类描述
 *
 * @author Corgi
 * @date 2021/2/18
 */
public class ImageMessage extends MediaMessage {

    public ImageMessage() {
        setMimeType(VOICE);
    }

    @Override
    public ViewHandler getViewHandler() {
        return new ImageViewHandler();
    }
}
