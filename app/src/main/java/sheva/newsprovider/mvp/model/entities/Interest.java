package sheva.newsprovider.mvp.model.entities;

import android.util.Log;

public class Interest {
    private String name;
    private String[] sources;

    public Interest(String name, String[] sources) {
        this.name = name;
        this.sources = sources;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSourceAt(int position) {
        if (position >= sources.length) {
            Log.e(Interest.class.getSimpleName(), "position is incorrect");
            return null;
        }
        return sources[position];
    }
}
