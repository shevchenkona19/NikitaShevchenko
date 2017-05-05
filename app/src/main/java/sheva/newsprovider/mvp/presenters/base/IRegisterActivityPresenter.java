package sheva.newsprovider.mvp.presenters.base;

import android.net.Uri;

/**
 * Created by shevc on 06.04.2017.
 *
 */

public interface IRegisterActivityPresenter {
    void register(String name, String username, String password, Uri img);
    void putVKAccessToken(String token);
    void setUsingVk(boolean b);
}