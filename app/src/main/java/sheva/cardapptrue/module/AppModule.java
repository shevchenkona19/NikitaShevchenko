package sheva.cardapptrue.module;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import sheva.cardapptrue.RVAdapter;

/**
 * Created by shevc on 15.03.2017.
 */
@Module
public class AppModule {
    private Context context;

    public AppModule(@NonNull Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    public Context providesContext(){
        return context;
    }

    @Provides
    public RVAdapter providesRVAdapter(Context context){
        return new RVAdapter(context);
    }
}
