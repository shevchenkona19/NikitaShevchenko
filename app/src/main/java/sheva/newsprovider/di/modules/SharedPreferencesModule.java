package sheva.newsprovider.di.modules;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import sheva.newsprovider.IConstants;
import sheva.newsprovider.mvp.model.repositories.SharedPreferencesRepository;

/**
 * Created by shevc on 09.04.2017.
 * s
 */
@Module
public class SharedPreferencesModule {

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(Context context){
        return context.getSharedPreferences(IConstants.Preferences.APP_PREFERENCES,
                Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public SharedPreferencesRepository provideSharedPreferencesRepository(){
        return new SharedPreferencesRepository();
    }
}