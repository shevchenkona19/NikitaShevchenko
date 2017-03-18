package sheva.bank.mvp.view;


import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.bank.R;
import sheva.bank.app.adapter.Adapter;
import sheva.bank.mvp.presenter.MainActivityPresenter;
import sheva.bank.mvp.view.interfaces.IMainActivityView;

public class MainActivity extends AppCompatActivity implements IMainActivityView, DatePickDialog.IGetDateFromDialog {
    private String date;
    private MainActivityPresenter presenter;
    private Adapter adapter;
    @BindView(R.id.rvList)
    RecyclerView rvList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter = new Adapter();
        presenter = new MainActivityPresenter();
        presenter.bind(this);
        presenter.showDialogForDate();

    }

    @Override
    protected void onDestroy() {
        presenter.unbind();
        super.onDestroy();
    }

    //IMainActivityView
    @Override
    public void showText(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialogForDate() {
        DatePickDialog dialog = DatePickDialog.newInstance(this);
        dialog.setStyle(DialogFragment.STYLE_NORMAL, 0);
        dialog.show(this.getSupportFragmentManager(), "Date1");
    }

    @Override
    public void updateList() {

    }

    //IGetDateFromDialog
    @Override
    public void getDateFromDialog(String date) {
        this.date = date;
        presenter.updateList();
        Log.d("MY", "date is " + date);
    }


}
