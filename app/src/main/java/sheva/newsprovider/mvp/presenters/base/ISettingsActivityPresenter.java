package sheva.newsprovider.mvp.presenters.base;

/**
 * Created by shevc on 06.04.2017.
 *
 */

public interface ISettingsActivityPresenter {
    void exit();
    void changeInterests();
    void usePhoneBrowser(boolean b);
    void useFahrenheit(boolean b);
    void setSwitchBrowser();
    void setSwitchFahrenheit();
    void setSwitchNotify();
    void useNotify(boolean b);
}
