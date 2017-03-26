package sheva.economicprovider.mvp.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import sheva.economicprovider.R;
import sheva.economicprovider.mvp.ui.interfaces.IDialogFragmentView;

/**
 * Created by shevc on 18.03.2017.
 */

public class DatePickDialog extends DialogFragment implements IDialogFragmentView{

    private static IGetDateFromDialog getDate;

    public interface IGetDateFromDialog{
        void getDateFromDialog(String date);
    }

    public DatePickDialog(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.pick_date_dialog, container, false);
        Button btnOk = (Button) v.findViewById(R.id.btnOk);
        final EditText etDate = (EditText) v.findViewById(R.id.etDate);
        btnOk.setOnClickListener(view -> {
            getDate.getDateFromDialog(etDate.getText().toString());
            getDialog().dismiss();
        });
        return v;
    }

    public static DatePickDialog newInstance(Context context) {

        Bundle args = new Bundle();
        getDate = (IGetDateFromDialog) context;
        DatePickDialog fragment = new DatePickDialog();
        fragment.setArguments(args);
        return fragment;
    }
}
