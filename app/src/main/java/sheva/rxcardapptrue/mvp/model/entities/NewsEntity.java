package sheva.rxcardapptrue.mvp.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shevc on 22.03.2017.
 */

public class NewsEntity implements Parcelable {
    private String title;
    private String text;
    private int time;

    public NewsEntity(String title, String text, int time) {
        this.title = title;
        this.text = text;
        this.time = time;
    }

    protected NewsEntity(Parcel in) {
        title = in.readString();
        text = in.readString();
        time = in.readInt();
    }

    public static final Creator<NewsEntity> CREATOR = new Creator<NewsEntity>() {
        @Override
        public NewsEntity createFromParcel(Parcel in) {
            return new NewsEntity(in);
        }

        @Override
        public NewsEntity[] newArray(int size) {
            return new NewsEntity[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(text);
        parcel.writeInt(time);
    }
}
