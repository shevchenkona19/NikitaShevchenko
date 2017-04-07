package sheva.newsprovider.mvp.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.newsprovider.R;

public class RegisterActivity extends MvpAppCompatActivity {
    @BindView(R.id.tbRegisterToolbar)
    Toolbar tbRegister;
    @BindView(R.id.ivRegisterUserIMG)
    ImageView ivUserIMG;
    @BindView(R.id.etRegisterName)
    EditText etName;
    @BindView(R.id.etRegisterUsername)
    EditText etUsername;
    @BindView(R.id.etRegisterPassword)
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setSupportActionBar(tbRegister);
    }
}
