package sheva.newsprovider.mvp.model.datamanager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;

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

    public DataManager() {
        App.get().getAppComponent().inject(this);
    }

    public boolean isRegistered() {
        return sharedPreferencesRepository.isRegistered();
    }

    public void register(String name, String username, String password, Bitmap img) {
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

    public void inflateRegisteredUser() {
        String[] userInfo = sharedPreferencesRepository.getUserInfo();
        Bitmap b = null;
        try {
            byte[] encodeByte = Base64.decode(userInfo[3], Base64.DEFAULT);
            b = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        } catch (Exception e) {
            e.getMessage();
        }
        user.registerMainUser(userInfo[0], userInfo[1], userInfo[2], b);
    }

    public Object[] getUserInfo() {
        Object[] userInfo = new String[2];
        Log.d("MY", "Bitmap:" + user.getImg());
        userInfo[0] = user.getName();
        userInfo[1] = user.getImg();
        return userInfo;
    }
}
