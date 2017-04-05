package sheva.economicprovider.mvp.ui.fragments;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.BufferedReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.economicprovider.R;

/**
 * Created by shevc on 05.04.2017.
 */

public class WifiDialog extends DialogFragment {
    @BindView(R.id.btnCancel)
    Button btnCancel;
    @BindView(R.id.btnOk)
    Button btnOk;
    public static WifiDialog newInstance(){
        return new WifiDialog();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_wifi, container, false);
        ButterKnife.bind(this, v);
        btnOk.setOnClickListener(view -> {
            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        });
        btnCancel.setOnClickListener(view -> getDialog().dismiss());
        return v;
    }

}
