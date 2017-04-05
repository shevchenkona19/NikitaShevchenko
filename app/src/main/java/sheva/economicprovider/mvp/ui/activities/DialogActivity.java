package sheva.economicprovider.mvp.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import sheva.economicprovider.mvp.ui.fragments.WifiDialog;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WifiDialog dialog = WifiDialog.newInstance();
        dialog.show(getFragmentManager(), "TAG");
    }
}
