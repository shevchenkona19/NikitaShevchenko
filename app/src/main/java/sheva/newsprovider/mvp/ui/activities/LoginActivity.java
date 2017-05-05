package sheva.newsprovider.mvp.ui.activities;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

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
    @BindView(R.id.clLoginLayout)
    ConstraintLayout layout;
    @BindView(R.id.tvRegister)
    TextView tvRegister;
    @InjectPresenter
    LoginActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.MANAGE_DOCUMENTS, Manifest.permission.READ_EXTERNAL_STORAGE}, 1243);
        }
        ButterKnife.bind(this);
        presenter.isUsingVk();
        btnSubmit.setOnClickListener(view -> {
            if (etUsername.getText().toString().equals("") ||
                    etPassword.getText().toString().equals("")) {
                presenter.showError("Please, fill in the forms first.");
                return;
            }
            presenter.login(etUsername.getText().toString(), etPassword.getText().toString());
        });
        tvRegister.setOnClickListener(view -> presenter.startRegisterActivity());
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
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void startRegisterActivity() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
}