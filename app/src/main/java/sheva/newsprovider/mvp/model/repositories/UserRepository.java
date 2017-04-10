package sheva.newsprovider.mvp.model.repositories;

import android.net.Uri;

import sheva.newsprovider.mvp.model.entities.User;

/**
 * Created by shevc on 09.04.2017.
 * d
 */

public class UserRepository {
    private User mainUser;

    public UserRepository(){
        mainUser = new User();
    }

    public User getMainUser() {
        if (mainUser == null) {
            mainUser = new User();
        }
        return mainUser;
    }

    public void registerMainUser(String name, String username, String password, Uri img) {
        if (mainUser == null) {
            mainUser = new User();
        }
        mainUser.setName(name);
        mainUser.setUsername(username);
        mainUser.setPassword(password);
        mainUser.setImg(img);
    }

    public void registerMainUser(String name, String username, String password, String img) {
        if (mainUser == null) {
            mainUser = new User();
        }
        mainUser.setName(name);
        mainUser.setUsername(username);
        mainUser.setPassword(password);
        mainUser.setImg(img);
    }

    public void unregisterMainUser() {
        mainUser = null;
    }

    public String getName() {
        return mainUser.getName();
    }

    public String getUsername() {
        return mainUser.getUsername();
    }

    public String getPassword() {
        return mainUser.getPassword();
    }

    public String getImg() {
        return mainUser.getImg();
    }
}
