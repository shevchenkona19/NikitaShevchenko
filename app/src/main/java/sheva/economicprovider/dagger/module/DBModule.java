package sheva.economicprovider.dagger.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import sheva.economicprovider.mvp.model.db.DB;
import sheva.economicprovider.mvp.model.db.DBHelper;
import sheva.economicprovider.mvp.model.db.mappers.ArticleToContentValueMapper;
import sheva.economicprovider.mvp.model.repositories.DBRepository;

/**
 * Created by shevc on 07.04.2017.\
 * s
 */
@Module
public class DBModule {
    @Provides
    @Singleton
    public DB provideDB(Context context){
        return new DB(context);
    }

    @Provides
    @Singleton
    public DBHelper provideDBHelper(){
        return new DBHelper();
    }

    @Provides
    @Singleton
    public ArticleToContentValueMapper provideMapper(){
        return new ArticleToContentValueMapper();
    }

    @Provides
    @Singleton
    public DBRepository provideDBRepository(){
        return new DBRepository();
    }
}
