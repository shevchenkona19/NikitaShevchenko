package sheva.newsprovider.mvp.model.repositories;

import android.content.SharedPreferences;

import javax.inject.Inject;

import sheva.newsprovider.App;
import sheva.newsprovider.IConstants;

/**
 * Created by shevc on 09.04.2017.
 * j
 */

public class SharedPreferencesRepository {
    @Inject
    SharedPreferences sp;

    public SharedPreferencesRepository(){
        App.get().getAppComponent().inject(this);
    }

    public boolean isRegistered(){
        return sp.getBoolean(IConstants.Preferences.PREFERENCES_ISREGISTRED, false);
    }

    public void register(UserRepository repository){
        sp.edit()
                .putString(IConstants.Preferences.PREFERENCES_USERNAME, repository.getUsername())
                .putString(IConstants.Preferences.PREFERENCES_PASSWORD, repository.getPassword())
                .putString(IConstants.Preferences.PREFERENCES_NAME, repository.getName())
//                .putString(IConstants.Preferences.PREFERENCES_IMG, repository.getStringBitmap())
                .putBoolean(IConstants.Preferences.PREFERENCES_ISREGISTRED, true)
                .apply();
    }

    public String[] getUserInfo() {
        String[] userInfo = new String[4];
        userInfo[0] = sp.getString(IConstants.Preferences.PREFERENCES_NAME, "");
        userInfo[1] = sp.getString(IConstants.Preferences.PREFERENCES_USERNAME, "");
        userInfo[2] = sp.getString(IConstants.Preferences.PREFERENCES_PASSWORD, "");
        userInfo[3] = sp.getString(IConstants.Preferences.PREFERENCES_IMG, null);
        return userInfo;
    }

    public void unregisterCurrentUser(){
        sp.edit()
                .putString(IConstants.Preferences.PREFERENCES_USERNAME, "")
                .putString(IConstants.Preferences.PREFERENCES_PASSWORD, "")
                .putString(IConstants.Preferences.PREFERENCES_NAME, "")
                .putString(IConstants.Preferences.PREFERENCES_IMG, "")
                .putBoolean(IConstants.Preferences.PREFERENCES_ISREGISTRED, false)
                .apply();
    }

    public boolean login(String username, String password) {
        return sp.getString(IConstants.Preferences.PREFERENCES_USERNAME, "").equals(username) &&
                sp.getString(IConstants.Preferences.PREFERENCES_PASSWORD, "").equals(password);
    }




}
