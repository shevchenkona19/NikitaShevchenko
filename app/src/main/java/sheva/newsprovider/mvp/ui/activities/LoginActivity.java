package sheva.newsprovider.mvp.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.newsprovider.R;

public class LoginActivity extends MvpAppCompatActivity {
    @BindView(R.id.btnLoginSubmit)
    Button btnSubmit;
    @BindView(R.id.etLoginUsername)
    EditText etUsername;
    @BindView(R.id.etLoginPassword)
    EditText etPassword;
    @BindView(R.id.tbLoginToolbar)
    Toolbar tbToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setSupportActionBar(tbToolbar);
    }
}
