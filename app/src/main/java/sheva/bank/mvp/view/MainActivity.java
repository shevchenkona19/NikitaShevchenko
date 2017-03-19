package sheva.bank.mvp.view;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.bank.App;
import sheva.bank.JSONObjects.BankCurrency;
import sheva.bank.R;
import sheva.bank.app.adapter.Adapter;
import sheva.bank.mvp.presenter.MainActivityPresenter;
import sheva.bank.mvp.view.interfaces.IMainActivityView;

public class MainActivity extends AppCompatActivity implements IMainActivityView, DatePickDialog.IGetDateFromDialog {
    private String date;
    private Adapter adapter;
    private static final int date_id = 101;
    @Inject
    MainActivityPresenter presenter;
    @BindView(R.id.rvList)
    RecyclerView rvList;
    @BindView(R.id.tbToolbar)
    android.widget.Toolbar tbToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.get().getAppComponent().inject(this);
        setActionBar(tbToolbar);
        adapter = new Adapter(this);
        presenter.bind(this);
        presenter.showDialogForDate();
        rvList.setAdapter(adapter);
        rvList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, date_id, Menu.NONE, "Изменить дату");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case date_id:
                presenter.showDialogForDate();
        }
        return super.onOptionsItemSelected(item);
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
    public void updateList(BankCurrency currency) {
        Log.d("MY", "update list in MainActivity");
        adapter.addBankCurrency(currency);
    }

    //IGetDateFromDialog
    @Override
    public void getDateFromDialog(String date) {
        this.date = date;
        Log.d("MY", "getDateFromDialog");
        presenter.updateList(date);
    }


}
