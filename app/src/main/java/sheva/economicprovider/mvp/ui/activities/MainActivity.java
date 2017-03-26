    package sheva.economicprovider.mvp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.economicprovider.R;
import sheva.economicprovider.mvp.presenter.MainActivityPresenter;
import sheva.economicprovider.mvp.ui.fragments.CurrencyItemFragment;
import sheva.economicprovider.mvp.ui.fragments.DatePickDialog;
import sheva.economicprovider.mvp.ui.interfaces.IMainActivityView;

    public class MainActivity extends AppCompatActivity implements IMainActivityView, DatePickDialog.IGetDateFromDialog, CurrencyItemFragment.OnListFragmentInteractionListener{
        @BindView(R.id.toolbar)
        Toolbar toolbar;
        @BindView(R.id.navView)
        NavigationView navView;
        @BindView(R.id.dwLayout)
        DrawerLayout dwLayout;
        private MainActivityPresenter presenter;
        private CurrencyItemFragment currencyItemFragment;
        private String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        presenter = new MainActivityPresenter();
        presenter.bind(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dwLayout,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_closed);
        dwLayout.addDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navFeed:
                    Intent intent = new Intent(this, NewsActivity.class);
                    presenter.removeCurrencyFragment();
                    startActivity(intent);
                    break;
                case R.id.navCurrency:
                    presenter.showCurrencyFragment();
                    break;
            }
            dwLayout.closeDrawer(GravityCompat.START);
            return true;
        });
        currencyItemFragment = CurrencyItemFragment.newInstance(1);
        presenter.showDialogForDate();

    }

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
        public void showCurrencyFragment() {
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .add(R.id.activity_main,currencyItemFragment)
                    .commit();
        }

        @Override
        public void removeCurrencyFragment() {
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .remove(currencyItemFragment)
                    .commit();
        }

        @Override
        public void getDateFromDialog(String date) {
            Log.d("MY", "getDateFromDialog");
         this.date = date;
        }

        @Override
        public String setDateForRequest() {
            return date;
        }

        @Override
        protected void onDestroy() {
            presenter.unbind();
            super.onDestroy();
        }
    }
