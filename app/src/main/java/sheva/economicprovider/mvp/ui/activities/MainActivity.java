package sheva.economicprovider.mvp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.economicprovider.R;
import sheva.economicprovider.mvp.presenter.MainActivityPresenter;
import sheva.economicprovider.mvp.ui.fragments.CurrencyItemFragment;
import sheva.economicprovider.mvp.ui.fragments.DatePickDialog;
import sheva.economicprovider.mvp.ui.interfaces.IMainActivityView;

public class MainActivity extends MvpAppCompatActivity implements IMainActivityView, DatePickDialog.IGetDateFromDialog, CurrencyItemFragment.OnListFragmentInteractionListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.navView)
    NavigationView navView;
    @BindView(R.id.dwLayout)
    DrawerLayout dwLayout;
    @InjectPresenter
    MainActivityPresenter presenter;
    private CurrencyItemFragment currencyItemFragment;
    private String date;
    private String currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        ButterKnife.bind(this);
        currency = null;
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dwLayout,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_closed);
        dwLayout.addDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navFeed:
                    Intent intent = new Intent(this, NewsActivity.class);
                    presenter.removeCurrencyFragment();
                    startActivity(intent);
                    break;
                case R.id.navCurrency:
                    presenter.showCurrencyFragment();
                    break;
                case R.id.navSettings:
                    startActivity(new Intent(this, SettingsActivity.class));
                    break;
            }
            dwLayout.closeDrawer(GravityCompat.START);
            return true;
        });
        presenter.showCurrencyFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_changedate:
                presenter.showDialogForDate();
                break;
            case R.id.menu_eur:
                currency = "eur";
                break;
            case R.id.menu_usd:
                currency = "usd";
                break;
            case R.id.menu_gbp:
                currency = "gbp";
                break;
            case R.id.menu_rub:
                currency = "rub";
                break;
            case R.id.menu_other:
                currency = null;
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showText(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialogForDate() {
        presenter.removeCurrencyFragment();
        DatePickDialog dialog = DatePickDialog.newInstance(this);
        dialog.setStyle(DialogFragment.STYLE_NORMAL, 0);
        dialog.show(this.getSupportFragmentManager(), "Date1");
    }

    @Override
    public void showCurrencyFragment() {
        currencyItemFragment = currencyItemFragment.newInstance(1);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.activity_main, currencyItemFragment)
                .commit();
    }

    @Override
    public void removeCurrencyFragment() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .remove(currencyItemFragment)
                .commit();
        currencyItemFragment = null;
    }

    @Override
    public void getDateFromDialog(String date) {
        Log.d("MY", "getDateFromDialog");
        this.date = date;
        presenter.showCurrencyFragment();
    }

    @Override
    public String[] setDateForRequest() {
        return new String[] {date, currency};
    }
}
