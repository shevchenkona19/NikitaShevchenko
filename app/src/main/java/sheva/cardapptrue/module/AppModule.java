package sheva.cardapptrue.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import sheva.cardapptrue.RVAdapter;

/**
 * Created by shevc on 15.03.2017.
 */
@Module
public class AppModule {
    Context context;

    public AppModule(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    Context providesContext(){
        return context;
    }

    @Provides
    RVAdapter providesRVAdapter(Context context){
        return new RVAdapter(context);
    }
}
