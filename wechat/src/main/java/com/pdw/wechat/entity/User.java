package com.pdw.wechat.entity;

/**
 * 类描述
 *
 * @author Corgi
 * @date 2021/2/16
 */
public class User {
    public static final long ME = 1000;
    private long id;

    public User(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static boolean isMe(long userId) {
        return userId == ME;
    }
}
