package sheva.economicprovider.mvp.model.repositories;

import android.content.SharedPreferences;

import javax.inject.Inject;

import sheva.economicprovider.App;

/**
 * Created by shevc on 01.04.2017.
 */

public class SharedPreferencesRepository {
    public final static String ISNOTYFIYNG = "notify";
    @Inject
    SharedPreferences preferences;

    public SharedPreferencesRepository(){
        App.get().getAppComponent().inject(this);
    }

    public boolean getSendNotification(){
        boolean b = false;
        try {
            return preferences.getBoolean(ISNOTYFIYNG, b);
        }catch (Exception e){
            //there is no boolean with that name. Need to init first
            setSendNotification(b);
            return false;
        }
    }

    public void setSendNotification(boolean b){
        preferences.edit()
                .putBoolean(ISNOTYFIYNG, b)
                .apply();
    }
}
