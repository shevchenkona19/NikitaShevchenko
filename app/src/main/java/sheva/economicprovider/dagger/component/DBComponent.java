package sheva.economicprovider.dagger.component;

import javax.inject.Singleton;

import dagger.Subcomponent;
import sheva.economicprovider.dagger.module.DBModule;
import sheva.economicprovider.mvp.model.datamanager.DataManager;
import sheva.economicprovider.mvp.model.db.DBHelper;
import sheva.economicprovider.mvp.model.repositories.DBRepository;

/**
 * Created by shevc on 07.04.2017.
 * d
 */
@Subcomponent(modules = DBModule.class)
@Singleton
public interface DBComponent {
    void inject(DBHelper helper);
    void inject(DBRepository repository);
    void inject(DataManager manager);
}
