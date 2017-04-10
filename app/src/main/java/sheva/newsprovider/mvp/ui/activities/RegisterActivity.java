package sheva.newsprovider.mvp.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.newsprovider.IConstants;
import sheva.newsprovider.R;
import sheva.newsprovider.mvp.presenters.activities.RegisterActivityPresenter;
import sheva.newsprovider.mvp.ui.base.IRegisterActivityView;

public class RegisterActivity extends MvpAppCompatActivity implements IRegisterActivityView {
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
    @BindView(R.id.btnRegisterSubmit)
    Button btnSubmit;
    @InjectPresenter
    RegisterActivityPresenter presenter;

    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setSupportActionBar(tbRegister);
        ivUserIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), IConstants.RESULT_PIC);
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUri == null) {
                    imageUri = Uri.parse("android.resource://sheva.newsprovider/" + R.drawable.default_pic);
                }
                presenter.register(
                        etName.getText().toString(),
                        etUsername.getText().toString(),
                        etPassword.getText().toString(),
                        imageUri);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == IConstants.RESULT_PIC) {
                imageUri = data.getData();
                Picasso.with(RegisterActivity.this)
                        .load(imageUri)
                        .resize(120, 120)
                        .into(ivUserIMG);
            }
        }
    }

    //IRegisterActivityView
    @Override
    public void startLoginActivity() {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }

}
