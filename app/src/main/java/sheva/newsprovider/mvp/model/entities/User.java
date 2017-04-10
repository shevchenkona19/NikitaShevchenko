package sheva.newsprovider.mvp.model.entities;

import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by shevc on 09.04.2017.
 * s
 */

public class User {
    @Nullable
    private String name;
    @Nullable
    private String username;
    @Nullable
    private String img;
    @Nullable
    private String password;

    public User() {
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Nullable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Nullable
    public String getImg() {
        return img;
    }

    public void setImg(Uri img) {
        this.img = img.toString();
    }

    public void setImg(String img) {
        this.img = img;
    }
}
