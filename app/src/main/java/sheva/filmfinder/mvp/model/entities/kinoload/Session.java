
package sheva.filmfinder.mvp.model.entities.kinoload;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Session implements Parcelable{

    @SerializedName("k_id")
    @Expose
    private Integer kId;
    @SerializedName("k_name")
    @Expose
    private String kName;
    @SerializedName("k_url")
    @Expose
    private String kUrl;
    @SerializedName("k_type")
    @Expose
    private Integer kType;
    @SerializedName("k_bron")
    @Expose
    private String kBron;
    @SerializedName("k_adv")
    @Expose
    private Object kAdv;
    @SerializedName("k_adv_i")
    @Expose
    private Object kAdvI;
    @SerializedName("k_t0")
    @Expose
    private Boolean kT0;
    @SerializedName("k_t3")
    @Expose
    private Boolean kT3;
    @SerializedName("k_to")
    @Expose
    private Boolean kTo;
    @SerializedName("h_name")
    @Expose
    private String hName;
    @SerializedName("sessions")
    @Expose
    private String sessions;
    @SerializedName("h_is3d")
    @Expose
    private Boolean hIs3d;

    public Session() {
    }

    public Session(Integer kId, String kName, String kUrl, Integer kType, String kBron, Object kAdv, Object kAdvI, Boolean kT0, Boolean kT3, Boolean kTo, String hName, String sessions, Boolean hIs3d) {
        super();
        this.kId = kId;
        this.kName = kName;
        this.kUrl = kUrl;
        this.kType = kType;
        this.kBron = kBron;
        this.kAdv = kAdv;
        this.kAdvI = kAdvI;
        this.kT0 = kT0;
        this.kT3 = kT3;
        this.kTo = kTo;
        this.hName = hName;
        this.sessions = sessions;
        this.hIs3d = hIs3d;
    }

    protected Session(Parcel in) {
        kName = in.readString();
        kUrl = in.readString();
        kBron = in.readString();
        hName = in.readString();
        sessions = in.readString();
    }

    public static final Creator<Session> CREATOR = new Creator<Session>() {
        @Override
        public Session createFromParcel(Parcel in) {
            return new Session(in);
        }

        @Override
        public Session[] newArray(int size) {
            return new Session[size];
        }
    };

    public Integer getkId() {
        return kId;
    }

    public void setkId(Integer kId) {
        this.kId = kId;
    }

    public String getKName() {
        return kName;
    }

    public void setKName(String kName) {
        this.kName = kName;
    }

    public String getkUrl() {
        return kUrl;
    }

    public void setkUrl(String kUrl) {
        this.kUrl = kUrl;
    }

    public Integer getKType() {
        return kType;
    }

    public void setKType(Integer kType) {
        this.kType = kType;
    }

    public String getKBron() {
        return kBron;
    }

    public void setKBron(String kBron) {
        this.kBron = kBron;
    }

    public Object getKAdv() {
        return kAdv;
    }

    public void setKAdv(Object kAdv) {
        this.kAdv = kAdv;
    }

    public Object getKAdvI() {
        return kAdvI;
    }

    public void setKAdvI(Object kAdvI) {
        this.kAdvI = kAdvI;
    }

    public Boolean getKT0() {
        return kT0;
    }

    public void setKT0(Boolean kT0) {
        this.kT0 = kT0;
    }

    public Boolean getKT3() {
        return kT3;
    }

    public void setKT3(Boolean kT3) {
        this.kT3 = kT3;
    }

    public Boolean getKTo() {
        return kTo;
    }

    public void setKTo(Boolean kTo) {
        this.kTo = kTo;
    }

    public String getHName() {
        return hName;
    }

    public void setHName(String hName) {
        this.hName = hName;
    }

    public String getSessions() {
        return sessions;
    }

    public void setSessions(String sessions) {
        this.sessions = sessions;
    }

    public Boolean getHIs3d() {
        return hIs3d;
    }

    public void setHIs3d(Boolean hIs3d) {
        this.hIs3d = hIs3d;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(kName);
        parcel.writeString(kUrl);
        parcel.writeString(kBron);
        parcel.writeString(hName);
        parcel.writeString(sessions);
    }
}
