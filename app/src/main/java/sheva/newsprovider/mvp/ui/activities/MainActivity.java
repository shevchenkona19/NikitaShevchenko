package sheva.newsprovider.mvp.ui.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.newsprovider.R;
import sheva.newsprovider.mvp.presenters.activities.MainActivityPresenter;
import sheva.newsprovider.mvp.ui.base.IMainActivityView;

public class MainActivity extends MvpAppCompatActivity implements IMainActivityView {
    @BindView(R.id.tbMainToolbar)
    Toolbar tbMain;
    @BindView(R.id.dwMainDrawer)
    DrawerLayout dwMain;
    @BindView(R.id.navMainNavigation)
    NavigationView navMain;
    private ImageView ivHeaderIMG;
    private TextView tvHeaderUsername;
    @InjectPresenter
    MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(tbMain);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, dwMain, tbMain, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dwMain.setDrawerListener(toggle);
        toggle.syncState();
        navMain.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.drawer_settings:
                        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                        break;
                }
                return true;
            }
        });
        View v = navMain.getHeaderView(0);
        ivHeaderIMG = (ImageView) v.findViewById(R.id.ivHeaderUserIMG);
        tvHeaderUsername = (TextView) v.findViewById(R.id.tvHeaderUsername);
        presenter.inflatePhoto();
    }
    //Options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 1, 1, "Settings");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //IMainActivityView
    @Override
    public void inflatePhoto(String name, Bitmap img) {
        tvHeaderUsername.setText(name);
        ivHeaderIMG.setImageDrawable(getResources().getDrawable(R.drawable.default_pic));
    }
}
