package sheva.newsprovider.mvp.ui.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.security.Permission;
import java.util.jar.Manifest;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.newsprovider.R;
import sheva.newsprovider.mvp.presenters.activities.LoginActivityPresenter;
import sheva.newsprovider.mvp.ui.base.ILoginActivityView;

public class LoginActivity extends MvpAppCompatActivity implements ILoginActivityView{
    @BindView(R.id.btnLoginSubmit)
    Button btnSubmit;
    @BindView(R.id.etLoginUsername)
    EditText etUsername;
    @BindView(R.id.etLoginPassword)
    EditText etPassword;
    @BindView(R.id.tbLoginToolbar)
    Toolbar tbToolbar;
    @BindView(R.id.clLoginLayout)
    ConstraintLayout layout;
    @InjectPresenter
    LoginActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        setSupportActionBar(tbToolbar);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etUsername.getText().toString().equals("") ||
                        etPassword.getText().toString().equals("")) {
                    presenter.showError("Please, fill in the forms first.");
                    return;
                }
                presenter.login(etUsername.getText().toString(), etPassword.getText().toString());
            }
        });
    }

    //ILoginActivityView
    @Override
    public void showError(String text) {
        Snackbar snackbar = Snackbar
                .make(layout, text, BaseTransientBottomBar.LENGTH_SHORT );
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.RED);
        snackbar.show();
    }

    @Override
    public void onLogged() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
}
