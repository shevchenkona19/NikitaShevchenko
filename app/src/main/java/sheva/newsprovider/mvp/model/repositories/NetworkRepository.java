package sheva.newsprovider.mvp.model.repositories;

import android.content.Context;
import android.net.ConnectivityManager;

import javax.inject.Inject;

import sheva.newsprovider.App;

public class NetworkRepository {
    @Inject
    Context context;



    public NetworkRepository() {
        App.get().getAppComponent().inject(this);

    }

    public boolean isInternetAvailable() {
        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connMgr.getActiveNetworkInfo() != null;
    }

}
