package sheva.economicprovider.dagger.component;

import javax.inject.Singleton;

import dagger.Subcomponent;
import sheva.economicprovider.dagger.module.RetrofitModule;
import sheva.economicprovider.mvp.model.datamanager.DataManager;
import sheva.economicprovider.mvp.model.repositories.BusinessInsiderRepository;
import sheva.economicprovider.mvp.model.repositories.NationalBankRepository;
import sheva.economicprovider.mvp.model.repositories.PrivateBankRepository;

/**
 * Created by shevc on 18.03.2017.
 */
@Subcomponent(modules = RetrofitModule.class)
@Singleton
public interface RetrofitComponent {
    void inject(DataManager manager);
    void inject(PrivateBankRepository repository);
    void inject(BusinessInsiderRepository repository);
    void inject(NationalBankRepository repository);
}
