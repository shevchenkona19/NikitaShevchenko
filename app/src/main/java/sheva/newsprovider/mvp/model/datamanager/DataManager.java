package sheva.newsprovider.mvp.model.datamanager;

import android.net.Uri;

import javax.inject.Inject;

import sheva.newsprovider.App;
import sheva.newsprovider.mvp.model.repositories.SharedPreferencesRepository;
import sheva.newsprovider.mvp.model.repositories.UserRepository;

/**
 * c
 * Created by shevc on 07.04.2017.
 */

public class DataManager {
    @Inject
    SharedPreferencesRepository sharedPreferencesRepository;
    @Inject
    UserRepository user;

    public DataManager(){
        App.get().getAppComponent().inject(this);
    }

    public boolean isRegistered() {
        return sharedPreferencesRepository.isRegistered();
    }

    public void register(String name, String username, String password, Uri img) {
        user.registerMainUser(name, username, password, img);
        sharedPreferencesRepository.register(user);
    }

    public void unregisterCurrentUser() {
        user.unregisterMainUser();
        sharedPreferencesRepository.unregisterCurrentUser();
    }

    public boolean login(String username, String password) {
        return sharedPreferencesRepository.login(username, password);
    }

    public void inflateRegisteredUser(){
        String[] userInfo = sharedPreferencesRepository.getUserInfo();
        user.registerMainUser(userInfo[0], userInfo[1], userInfo[2], userInfo[3]);
    }

    public String[] getUserInfo() {
        String[] userInfo = new String[2];
        userInfo[0] = user.getName();
        userInfo[1] = user.getImg();
        return userInfo;
    }
}
