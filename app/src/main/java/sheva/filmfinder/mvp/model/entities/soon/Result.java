
package sheva.filmfinder.mvp.model.entities.soon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

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
    @SerializedName("countries")
    @Expose
    private String countries;
    @SerializedName("actors")
    @Expose
    private String actors;
    @SerializedName("rejisser")
    @Expose
    private String rejisser;
    @SerializedName("comments_count")
    @Expose
    private Integer commentsCount;
    @SerializedName("reviews_count")
    @Expose
    private Integer reviewsCount;
    @SerializedName("trailers_count")
    @Expose
    private Integer trailersCount;
    @SerializedName("photos_count")
    @Expose
    private Integer photosCount;
    @SerializedName("sess_has")
    @Expose
    private Integer sessHas;
    @SerializedName("is3d")
    @Expose
    private Object is3d;
    @SerializedName("before")
    @Expose
    private String before;
    @SerializedName("entered")
    @Expose
    private String entered;
    @SerializedName("worldwide")
    @Expose
    private Object worldwide;
    @SerializedName("is_b")
    @Expose
    private Boolean isB;
    @SerializedName("b_link")
    @Expose
    private Boolean bLink;
    @SerializedName("age_limit")
    @Expose
    private Object ageLimit;

    public Result() {
    }

    public Result(Integer id, String name, String url, String image, String countries, String actors, String rejisser, Integer commentsCount, Integer reviewsCount, Integer trailersCount, Integer photosCount, Integer sessHas, Object is3d, String before, String entered, Object worldwide, Boolean isB, Boolean bLink, Object ageLimit) {
        super();
        this.id = id;
        this.name = name;
        this.url = url;
        this.image = image;
        this.countries = countries;
        this.actors = actors;
        this.rejisser = rejisser;
        this.commentsCount = commentsCount;
        this.reviewsCount = reviewsCount;
        this.trailersCount = trailersCount;
        this.photosCount = photosCount;
        this.sessHas = sessHas;
        this.is3d = is3d;
        this.before = before;
        this.entered = entered;
        this.worldwide = worldwide;
        this.isB = isB;
        this.bLink = bLink;
        this.ageLimit = ageLimit;
    }

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

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public Integer getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(Integer reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public Integer getTrailersCount() {
        return trailersCount;
    }

    public void setTrailersCount(Integer trailersCount) {
        this.trailersCount = trailersCount;
    }

    public Integer getPhotosCount() {
        return photosCount;
    }

    public void setPhotosCount(Integer photosCount) {
        this.photosCount = photosCount;
    }

    public Integer getSessHas() {
        return sessHas;
    }

    public void setSessHas(Integer sessHas) {
        this.sessHas = sessHas;
    }

    public Object getIs3d() {
        return is3d;
    }

    public void setIs3d(Object is3d) {
        this.is3d = is3d;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getEntered() {
        return entered;
    }

    public void setEntered(String entered) {
        this.entered = entered;
    }

    public Object getWorldwide() {
        return worldwide;
    }

    public void setWorldwide(Object worldwide) {
        this.worldwide = worldwide;
    }

    public Boolean getIsB() {
        return isB;
    }

    public void setIsB(Boolean isB) {
        this.isB = isB;
    }

    public Boolean getBLink() {
        return bLink;
    }

    public void setBLink(Boolean bLink) {
        this.bLink = bLink;
    }

    public Object getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(Object ageLimit) {
        this.ageLimit = ageLimit;
    }

}
