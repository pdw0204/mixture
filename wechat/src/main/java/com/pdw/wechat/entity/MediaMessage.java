package com.pdw.wechat.entity;


/**
 * 类描述
 *
 * @author Corgi
 * @date 2021/2/18
 */
public abstract class MediaMessage extends Message {

    public final static String IMAGE = "media/image";
    public final static String VIDEO = "media/video";
    public final static String VOICE = "media/voice";

    private String mimeType;
    private String uri;

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
