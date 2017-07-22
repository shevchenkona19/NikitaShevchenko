
package sheva.filmfinder.mvp.model.entities.cinemas;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("main")
    @Expose
    private List<Object> main = null;
    @SerializedName("unmain")
    @Expose
    private List<Unmain> unmain = null;

    public Result() {
    }

    public Result(List<Object> main, List<Unmain> unmain) {
        super();
        this.main = main;
        this.unmain = unmain;
    }

    public List<Object> getMain() {
        return main;
    }

    public void setMain(List<Object> main) {
        this.main = main;
    }

    public List<Unmain> getUnmain() {
        return unmain;
    }

    public void setUnmain(List<Unmain> unmain) {
        this.unmain = unmain;
    }

}
