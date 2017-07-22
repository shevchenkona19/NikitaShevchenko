package sheva.filmfinder.mvp.model.entities.sessions;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shevc on 13.06.2017.
 * Let's GO!
 */

public class SessionEntity implements Parcelable{
    private String cinemaName;
    private String cinemaBron;
    private List<HallTimeEntity> list;
    private boolean expanded;

    public SessionEntity(String cinemaName, String cinemaBron, List<HallTimeEntity> list, boolean expanded) {
        this.cinemaName = cinemaName;
        this.cinemaBron = cinemaBron;
        this.list = list;
        this.expanded = expanded;
    }

    public SessionEntity(){
        list = new ArrayList<>();
    }

    public SessionEntity(SessionEntity entity) {
        cinemaName = entity.getCinemaName();
        cinemaBron = entity.getCinemaBron();
        list =  entity.getList();
        expanded = entity.isExpanded();
    }

    protected SessionEntity(Parcel in) {
        cinemaName = in.readString();
        cinemaBron = in.readString();
        list = in.createTypedArrayList(HallTimeEntity.CREATOR);
        expanded = in.readByte() != 0;
    }

    public static final Creator<SessionEntity> CREATOR = new Creator<SessionEntity>() {
        @Override
        public SessionEntity createFromParcel(Parcel in) {
            return new SessionEntity(in);
        }

        @Override
        public SessionEntity[] newArray(int size) {
            return new SessionEntity[size];
        }
    };

    public String getCinemaName() {
        return cinemaName;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getCinemaBron() {
        return cinemaBron;
    }

    public void setCinemaBron(String cinemaBron) {
        this.cinemaBron = cinemaBron;
    }

    public List<HallTimeEntity> getList() {
        return list;
    }

    public void setList(List<HallTimeEntity> list) {
        this.list = list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(cinemaName);
        parcel.writeString(cinemaBron);
        parcel.writeTypedList(list);
        parcel.writeByte((byte) (expanded ? 1 : 0));
    }
}