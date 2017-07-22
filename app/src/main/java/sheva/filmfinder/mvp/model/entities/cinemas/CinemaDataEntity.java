
package sheva.filmfinder.mvp.model.entities.cinemas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CinemaDataEntity {

    @SerializedName("succes")
    @Expose
    private Boolean succes;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("result")
    @Expose
    private Result result;

    public CinemaDataEntity() {
    }

    public CinemaDataEntity(Boolean succes, Integer count, Result result) {
        super();
        this.succes = succes;
        this.count = count;
        this.result = result;
    }

    public Boolean getSucces() {
        return succes;
    }

    public void setSucces(Boolean succes) {
        this.succes = succes;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}
