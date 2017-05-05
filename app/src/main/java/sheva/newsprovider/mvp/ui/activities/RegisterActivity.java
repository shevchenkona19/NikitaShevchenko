package sheva.newsprovider.mvp.ui.activities;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.squareup.picasso.Picasso;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.newsprovider.IConstants;
import sheva.newsprovider.R;
import sheva.newsprovider.mvp.presenters.activities.RegisterActivityPresenter;
import sheva.newsprovider.mvp.ui.base.IRegisterActivityView;

public class RegisterActivity extends MvpAppCompatActivity implements IRegisterActivityView {
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
    @BindView(R.id.btnRegisterVK)
    Button btnVk;
    @InjectPresenter
    RegisterActivityPresenter presenter;

    private String[] scopes = new String[]{
            VKScope.STATUS
    };

    private String User_ID;


    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.MANAGE_DOCUMENTS, Manifest.permission.READ_EXTERNAL_STORAGE}, 1243);
        }
        ButterKnife.bind(this);
        ivUserIMG.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("*/*");
            startActivityForResult(intent, IConstants.RESULT_PIC);
        });
        btnSubmit.setOnClickListener(view -> {
            if (imageUri == null) {
                Resources resources = getResources();
                imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                        + "://" + resources.getResourcePackageName(R.drawable.default_pic)
                        + '/' + resources.getResourceTypeName(R.drawable.default_pic)
                        + '/' + resources.getResourceEntryName(R.drawable.default_pic) );
            }
            presenter.setUsingVk(false);
            presenter.register(
                    etName.getText().toString(),
                    etUsername.getText().toString(),
                    etPassword.getText().toString(),
                    imageUri);
        });

        btnVk.setOnClickListener(view -> VKSdk.login(RegisterActivity.this, scopes));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == IConstants.RESULT_PIC) {
                imageUri = data.getData();
                Picasso.with(RegisterActivity.this)
                        .load(data.getData())
                        .resize(120, 120)
                        .into(ivUserIMG);
            }
        }
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // Пользователь успешно авторизовался
                User_ID = res.userId;
                presenter.putVKAccessToken(res.accessToken);
                getVKPhoto();
            }

            @Override
            public void onError(VKError error) {
                // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void getVKPhoto() {
        VKRequest request =
                VKApi.users().get(new VKParameters().from(VKApiConst.FIELDS, "photo_100"));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                VKList user1 = (VKList)response.parsedModel;
                VKApiUserFull userFull = (VKApiUserFull) user1.get(0);
                presenter.setUsingVk(true);
                presenter.register(
                        userFull.first_name,
                        "",
                        "",
                        Uri.parse(userFull.photo_100));
            }
        });
    }

    //IRegisterActivityView
    @Override
    public void startLoginActivity() {
        startActivity(new Intent(RegisterActivity.this, InterestsActivity.class));
        finish();
    }
}