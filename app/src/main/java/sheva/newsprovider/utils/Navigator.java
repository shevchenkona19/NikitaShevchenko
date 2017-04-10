package sheva.newsprovider.utils;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import sheva.newsprovider.App;
import sheva.newsprovider.mvp.ui.activities.LoginActivity;
import sheva.newsprovider.mvp.ui.activities.RegisterActivity;

/**
 * Created by shevc on 09.04.2017.
 * s
 */

public class Navigator {
    @Inject
    Context context;

    public Navigator() {
        App.get().getAppComponent().inject(this);
    }

    public void launchRegisterActivity(){
        context.startActivity(new Intent(context, RegisterActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    public void launchLoginActivity() {
        context.startActivity(new Intent(context, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
