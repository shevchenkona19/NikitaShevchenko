package sheva.newsprovider.mvp.model.repositories;

import android.content.SharedPreferences;
import android.net.Uri;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import sheva.newsprovider.App;
import sheva.newsprovider.IConstants;
import sheva.newsprovider.mvp.model.entities.Interest;
import sheva.newsprovider.utils.SourcesUtils;

public class SharedPreferencesRepository {
    @Inject
    SharedPreferences sp;

    public SharedPreferencesRepository(){
        App.get().getAppComponent().inject(this);
    }

    public boolean isRegistered(){
        return sp.getBoolean(IConstants.Preferences.PREFERENCES_ISREGISTRED, false);
    }

    public void setUsingVK(boolean b) {
        sp.edit()
                .putBoolean(IConstants.Vk.ISUSINGVK, b)
                .apply();
    }

    public boolean isUsingVk() {
        return sp.getBoolean(IConstants.Vk.ISUSINGVK, false);
    }

    public boolean isNotify(){
        return sp.getBoolean(IConstants.Preferences.PREFERENCES_NOTIFICATIONS, false);
    }

    public void setVKAccessToken(String token) {
        sp.edit()
                .putString(IConstants.Vk.ACCESSTOKEN, token)
                .apply();
    }

    public String getVKAccessToken() {
        return sp.getString(IConstants.Vk.ACCESSTOKEN, "");
    }

    public void setPhoto(Uri uri) {
        sp.edit()
                .putString(IConstants.Preferences.PREFERENCES_IMG, uri.toString())
                .apply();
    }

    public void setNotify(boolean b) {
        sp.edit()
                .putBoolean(IConstants.Preferences.PREFERENCES_NOTIFICATIONS, b)
                .apply();
    }

    public void register(UserRepository repository){
        sp.edit()
                .putString(IConstants.Preferences.PREFERENCES_USERNAME, repository.getUsername())
                .putString(IConstants.Preferences.PREFERENCES_PASSWORD, repository.getPassword())
                .putString(IConstants.Preferences.PREFERENCES_NAME, repository.getName())
                .putString(IConstants.Preferences.PREFERENCES_IMG, repository.getUserImgString())
                .putBoolean(IConstants.Preferences.PREFERENCES_ISREGISTRED, true)
                .apply();
    }

    public void saveInterests(UserRepository user) {
        Set<String> set = new HashSet<>();
        for(Interest i : user.getListOfInterests()) {
            set.add(i.getName());
        }
        sp.edit()
                .putStringSet(IConstants.Preferences.PREFERENCES_INTERESTS, set)
                .apply();
    }

    public void deleteInterests() {
        sp.edit()
                .putStringSet(IConstants.Preferences.PREFERENCES_INTERESTS, null)
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

    public List<Interest> getSavedListOfInterests() {
        List<Interest> list = new ArrayList<>();
        Set<String> set = sp.getStringSet(IConstants.Preferences.PREFERENCES_INTERESTS, null);
        if (set != null) {
            for (String s:set) {
                list.add(new Interest(s, SourcesUtils.resolveSource(s)));
            }
        } else {
            return null;
        }
        return list;
    }

    public void unregisterCurrentUser(){
        sp.edit()
                .putString(IConstants.Preferences.PREFERENCES_USERNAME, "")
                .putString(IConstants.Preferences.PREFERENCES_PASSWORD, "")
                .putString(IConstants.Preferences.PREFERENCES_NAME, "")
                .putString(IConstants.Preferences.PREFERENCES_IMG, "")
                .putBoolean(IConstants.Preferences.PREFERENCES_ISREGISTRED, false)
                .putStringSet(IConstants.Preferences.PREFERENCES_INTERESTS, null)
                .apply();
    }

    public boolean login(String username, String password) {
        return sp.getString(IConstants.Preferences.PREFERENCES_USERNAME, "").equals(username) &&
                sp.getString(IConstants.Preferences.PREFERENCES_PASSWORD, "").equals(password);
    }

    public void usePhoneBrowser(boolean b) {
        sp.edit()
                .putBoolean(IConstants.Preferences.PREFERENCES_BROWSER, b)
                .apply();
    }

    public boolean isUsingPhoneBrowser() {
        return sp.getBoolean(IConstants.Preferences.PREFERENCES_BROWSER, false);
    }

    public void setIsUsingFarenheit(boolean b) {
        sp.edit()
                .putBoolean(IConstants.Preferences.PREFERENCES_UNITS, b)
                .apply();
    }

    public boolean isUsingFarenheit() {
        return sp.getBoolean(IConstants.Preferences.PREFERENCES_UNITS, false);
    }

}