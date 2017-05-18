package sheva.newsprovider.mvp.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sheva.newsprovider.R;

public class FacebookFragment extends Fragment{
    @BindView(R.id.btnFacebookLogin)
    LoginButton btnFacebookLogin;
    private AccessToken accessToken;
    private CallbackManager manager;
    private Unbinder unbinder;

    public FacebookFragment() {}

    public static FacebookFragment newInstance(IFacebookFragmentUtils utils1) {
        utils = utils1;
        return new FacebookFragment();
    }

    interface IFacebookFragmentUtils {
        void removeFacebookFragment();
        void showError(String text);
    }

    private static IFacebookFragmentUtils utils;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_facebook_login, container, false);
        unbinder = ButterKnife.bind(this, v);
        manager = CallbackManager.Factory.create();
        btnFacebookLogin.setFragment(this);
        btnFacebookLogin.setReadPermissions(Collections.singletonList("public_profile"));
        btnFacebookLogin.registerCallback(manager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                accessToken = loginResult.getAccessToken();
            }

            @Override
            public void onCancel() {
                utils.removeFacebookFragment();
            }

            @Override
            public void onError(FacebookException error) {
                utils.showError(error.getMessage());
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        manager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
