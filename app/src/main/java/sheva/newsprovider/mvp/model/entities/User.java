package sheva.newsprovider.mvp.model.entities;

import android.support.annotation.Nullable;

import java.util.List;

public class User {
    @Nullable
    private String name;
    @Nullable
    private String username;
    @Nullable
    private String img;
    @Nullable
    private String password;
    @Nullable
    private List<Interest> interestList;

    public User() {
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public String getUsername() {
        return username;
    }

    public void setUsername(@Nullable String username) {
        this.username = username;
    }

    @Nullable
    public String getPassword() {
        return password;
    }

    public void setPassword(@Nullable String password) {
        this.password = password;
    }

    public String getImgString() {
        return img;
    }

    public void setImg(@Nullable String uri) {
        img = uri;
    }

    @Nullable
    public List<Interest> getInterestList() {
        return interestList;
    }

    public void setInterestList(@Nullable List<Interest> interestList) {
        this.interestList = interestList;
    }
}