package sheva.newsprovider.mvp.presenters.base;

import android.net.Uri;
import android.support.v4.app.Fragment;

/**
 * Created by shevc on 06.04.2017.
 *
 */

public interface IMainActivityPresenter {
    void inflatePhoto();
    void checkInternet();
    void showFragment(Fragment fragment);
    void writeNewPhoto(Uri uri);
}