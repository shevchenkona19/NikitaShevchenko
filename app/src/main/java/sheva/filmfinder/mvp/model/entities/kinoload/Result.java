
package sheva.filmfinder.mvp.model.entities.kinoload;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Parcelable{

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
    @SerializedName("imdb")
    @Expose
    private String imdb;
    @SerializedName("countries")
    @Expose
    private String countries;
    @SerializedName("actors")
    @Expose
    private String actors;
    @SerializedName("rejisser")
    @Expose
    private String rejisser;
    @SerializedName("premier_ua")
    @Expose
    private String premierUa;
    @SerializedName("sessions")
    @Expose
    private List<Session> sessions = null;

    public Result() {
    }

    public Result(Integer id, String name, String url, String image, String vote, String countVote, String imdb, String countries, String actors, String rejisser, String premierUa, List<Session> sessions) {
        super();
        this.id = id;
        this.name = name;
        this.url = url;
        this.image = image;
        this.vote = vote;
        this.countVote = countVote;
        this.imdb = imdb;
        this.countries = countries;
        this.actors = actors;
        this.rejisser = rejisser;
        this.premierUa = premierUa;
        this.sessions = sessions;
    }

    protected Result(Parcel in) {
        name = in.readString();
        url = in.readString();
        image = in.readString();
        vote = in.readString();
        countVote = in.readString();
        imdb = in.readString();
        countries = in.readString();
        actors = in.readString();
        rejisser = in.readString();
        premierUa = in.readString();
        sessions = in.createTypedArrayList(Session.CREATOR);
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
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

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getRejisser() {
        return rejisser;
    }

    public void setRejisser(String rejisser) {
        this.rejisser = rejisser;
    }

    public String getPremierUa() {
        return premierUa;
    }

    public void setPremierUa(String premierUa) {
        this.premierUa = premierUa;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
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
        parcel.writeString(imdb);
        parcel.writeString(countries);
        parcel.writeString(actors);
        parcel.writeString(rejisser);
        parcel.writeString(premierUa);
        parcel.writeTypedList(sessions);
    }
}
