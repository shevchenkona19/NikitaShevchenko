
package sheva.filmfinder.mvp.model.entities.soon;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SoonDataEntity {

    @SerializedName("succes")
    @Expose
    private Boolean succes;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("result")
    @Expose
    private List<Result> result = null;

    public SoonDataEntity() {
    }

    public SoonDataEntity(Boolean succes, Integer count, List<Result> result) {
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

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

}
