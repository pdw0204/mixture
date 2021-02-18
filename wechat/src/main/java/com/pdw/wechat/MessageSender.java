package com.pdw.wechat;

import com.pdw.wechat.entity.Message;

/**
 * 类描述
 *
 * @author Corgi
 * @date 2021/2/16
 */
public interface MessageSender {
    boolean send(Message message);
}
