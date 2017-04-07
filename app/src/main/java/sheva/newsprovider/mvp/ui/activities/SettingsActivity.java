package sheva.newsprovider.mvp.ui.activities;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;

import sheva.newsprovider.R;

public class SettingsActivity extends MvpAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
}
