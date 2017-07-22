package sheva.filmfinder.mvp.model.entities.sessions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shevc on 13.06.2017.
 * Let's GO!
 */

public class HallTimeEntity implements Parcelable{
    private String hallName;
    private String sessions;
    private boolean is3D;

    protected HallTimeEntity(Parcel in) {
        hallName = in.readString();
        sessions = in.readString();
        is3D = in.readByte() != 0;
    }

    public HallTimeEntity(String hallName, String sessions, boolean is3D) {
        this.hallName = hallName;
        this.sessions = sessions;
        this.is3D = is3D;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getSessions() {
        return sessions;
    }

    public void setSessions(String sessions) {
        this.sessions = sessions;
    }

    public boolean is3D() {
        return is3D;
    }

    public void setIs3D(boolean is3D) {
        this.is3D = is3D;
    }

    public static final Creator<HallTimeEntity> CREATOR = new Creator<HallTimeEntity>() {
        @Override
        public HallTimeEntity createFromParcel(Parcel in) {
            return new HallTimeEntity(in);
        }

        @Override
        public HallTimeEntity[] newArray(int size) {
            return new HallTimeEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(hallName);
        parcel.writeString(sessions);
        parcel.writeByte((byte) (is3D ? 1 : 0));
    }
}
