package sheva.newsprovider.mvp.model.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by shevc on 09.04.2017.
 * s
 */

public class User {
    @Nullable
    private String name;
    @Nullable
    private String username;
    @Nullable
    private Bitmap img;
    @Nullable
    private String password;

    public User() {
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Nullable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Bitmap getImgBitmap() {
        return img;
    }

    public String getBitmapString() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }
}
