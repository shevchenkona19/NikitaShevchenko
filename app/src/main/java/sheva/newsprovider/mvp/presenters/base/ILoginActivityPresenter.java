package sheva.newsprovider.mvp.presenters.base;

/**
 * Created by shevc on 06.04.2017.
 *
 */

public interface ILoginActivityPresenter {
    void login(String username, String password);
    void showError(String err);
    void startRegisterActivity();
    void isUsingVk();
}
