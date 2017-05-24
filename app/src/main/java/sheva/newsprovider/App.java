package sheva.newsprovider;

import android.app.Application;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKSdk;

import javax.inject.Inject;

import sheva.newsprovider.di.component.AppComponent;
import sheva.newsprovider.di.component.DaggerAppComponent;
import sheva.newsprovider.di.modules.AppModule;
import sheva.newsprovider.mvp.model.datamanager.DataManager;
import sheva.newsprovider.mvp.ui.activities.MainActivity;
import sheva.newsprovider.mvp.ui.activities.RegisterActivity;
import sheva.newsprovider.utils.VKUtils;

/**
 * Created by shevc on 06.04.2017.
 * s
 */

public class App extends Application {
    VKAccessTokenTracker vkAccessTokenTracker = new VKAccessTokenTracker() {
        @Override
        public void onVKAccessTokenChanged(@Nullable VKAccessToken oldToken, @Nullable VKAccessToken newToken) {
            if (newToken == null) {
                Intent intent = new Intent(App.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }
    };
        protected static App instance;
        private AppComponent appComponent;
    @Inject
    DataManager dataManager;

    public static App get() {
        return instance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplicationContext());
        VKUtils.getFingerprint(this);
        instance = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(instance))
                .build();
        vkAccessTokenTracker.startTracking();
        VKSdk.initialize(this);
        appComponent.inject(this);
        if (dataManager.isRegistered()) {
            dataManager.inflateRegisteredUser();
            Intent intent = new Intent(instance, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Intent intent = new Intent(instance, RegisterActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
