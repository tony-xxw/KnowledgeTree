package com.wynne.knowledge.mark;

import android.os.Parcel;
import android.os.Parcelable;

public class MessageBean implements Parcelable {
    private String content;
    private int level;

    public MessageBean(String content, int level) {
        this.content = content;
        this.level = level;
    }

    public MessageBean() {

    }

    protected MessageBean(Parcel in) {
        content = in.readString();
        level = in.readInt();
    }

    public static final Creator<MessageBean> CREATOR = new Creator<MessageBean>() {
        @Override
        public MessageBean createFromParcel(Parcel in) {
            return new MessageBean(in);
        }

        @Override
        public MessageBean[] newArray(int size) {
            return new MessageBean[size];
        }
    };

    public void readFromParcel(Parcel dest) {
        this.content = dest.readString();
        this.level = dest.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
        dest.writeInt(level);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
