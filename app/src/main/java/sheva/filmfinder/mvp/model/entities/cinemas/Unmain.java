
package sheva.filmfinder.mvp.model.entities.cinemas;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Unmain implements Parcelable{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("vote")
    @Expose
    private String vote;
    @SerializedName("count_vote")
    @Expose
    private String countVote;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("address")
    @Expose
    private String address;

    public Unmain() {
    }

    public Unmain(Integer id, String name, String url, String image, String vote, String countVote, String phone, String address) {
        super();
        this.id = id;
        this.name = name;
        this.url = url;
        this.image = image;
        this.vote = vote;
        this.countVote = countVote;
        this.phone = phone;
        this.address = address;
    }

    protected Unmain(Parcel in) {
        name = in.readString();
        url = in.readString();
        image = in.readString();
        vote = in.readString();
        countVote = in.readString();
        phone = in.readString();
        address = in.readString();
    }

    public static final Creator<Unmain> CREATOR = new Creator<Unmain>() {
        @Override
        public Unmain createFromParcel(Parcel in) {
            return new Unmain(in);
        }

        @Override
        public Unmain[] newArray(int size) {
            return new Unmain[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public String getCountVote() {
        return countVote;
    }

    public void setCountVote(String countVote) {
        this.countVote = countVote;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(url);
        parcel.writeString(image);
        parcel.writeString(vote);
        parcel.writeString(countVote);
        parcel.writeString(phone);
        parcel.writeString(address);
    }
}
