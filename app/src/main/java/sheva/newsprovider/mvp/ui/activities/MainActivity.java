package sheva.newsprovider.mvp.ui.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.newsprovider.IConstants;
import sheva.newsprovider.R;
import sheva.newsprovider.mvp.presenters.activities.MainActivityPresenter;
import sheva.newsprovider.mvp.ui.adapters.FeedFragmentAdapter;
import sheva.newsprovider.mvp.ui.base.IMainActivityView;
import sheva.newsprovider.mvp.ui.fragments.FavoriteFragment;
import sheva.newsprovider.mvp.ui.fragments.FeedFragment;
import sheva.newsprovider.mvp.ui.fragments.NewsFragment;
import sheva.newsprovider.utils.SourcesUtils;

public class MainActivity extends MvpAppCompatActivity implements IMainActivityView, FeedFragmentAdapter.ILaunchFragment {
    @BindView(R.id.tbMainToolbar)
    Toolbar tbMain;
    @BindView(R.id.dwMainDrawer)
    DrawerLayout dwMain;
    @BindView(R.id.navMainNavigation)
    NavigationView navMain;
    private ImageView ivHeaderIMG;
    private TextView tvHeaderUsername;
    private FragmentManager manager;
    @InjectPresenter
    MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        manager = getSupportFragmentManager();
        setSupportActionBar(tbMain);
        presenter.checkInternet();
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, dwMain, tbMain, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dwMain.setDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setTitle("News");
        presenter.showFragment(NewsFragment.newInstance(IConstants.Sources.news[0]));
        navMain.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.drawer_feed:
                        getSupportActionBar().setTitle("My feed");
                        presenter.showFragment(FeedFragment.newInstance(MainActivity.this));
                        dwMain.closeDrawers();
                        presenter.checkInternet();
                        break;
                    case R.id.drawer_business:
                        getSupportActionBar().setTitle("Business");
                        presenter.showFragment(NewsFragment.newInstance(IConstants.Sources.business[0]));
                        dwMain.closeDrawers();
                        presenter.checkInternet();
                        break;
                    case R.id.drawer_entertainment:
                        getSupportActionBar().setTitle("Entertainment");
                        presenter.showFragment(NewsFragment.newInstance(IConstants.Sources.entertainment[0]));
                        dwMain.closeDrawers();
                        presenter.checkInternet();
                        break;
                    case R.id.drawer_finance:
                        getSupportActionBar().setTitle("Finance");
                        presenter.showFragment(NewsFragment.newInstance(IConstants.Sources.finance[0]));
                        dwMain.closeDrawers();
                        presenter.checkInternet();
                        break;
                    case R.id.drawer_football:
                        getSupportActionBar().setTitle("Football");
                        presenter.showFragment(NewsFragment.newInstance(IConstants.Sources.football[0]));
                        dwMain.closeDrawers();
                        presenter.checkInternet();
                        break;
                    case R.id.drawer_games:
                        getSupportActionBar().setTitle("Games");
                        presenter.showFragment(NewsFragment.newInstance(IConstants.Sources.games[0]));
                        dwMain.closeDrawers();
                        presenter.checkInternet();
                        break;
                    case R.id.drawer_geography:
                        getSupportActionBar().setTitle("Geography");
                        presenter.showFragment(NewsFragment.newInstance(IConstants.Sources.geography[0]));
                        dwMain.closeDrawers();
                        presenter.checkInternet();
                        break;
                    case R.id.drawer_music:
                        getSupportActionBar().setTitle("Music");
                        presenter.showFragment(NewsFragment.newInstance(IConstants.Sources.music[0]));
                        dwMain.closeDrawers();
                        presenter.checkInternet();
                        break;
                    case R.id.drawer_news:
                        getSupportActionBar().setTitle("News");
                        presenter.showFragment(NewsFragment.newInstance(IConstants.Sources.news[0]));
                        dwMain.closeDrawers();
                        presenter.checkInternet();
                        break;
                    case R.id.drawer_politics:
                        getSupportActionBar().setTitle("Politics");
                        presenter.showFragment(NewsFragment.newInstance(IConstants.Sources.politics[0]));
                        dwMain.closeDrawers();
                        presenter.checkInternet();
                        break;
                    case R.id.drawer_science:
                        getSupportActionBar().setTitle("Science");
                        presenter.showFragment(NewsFragment.newInstance(IConstants.Sources.science[0]));
                        dwMain.closeDrawers();
                        presenter.checkInternet();
                        break;
                    case R.id.drawer_sport:
                        getSupportActionBar().setTitle("Sport");
                        presenter.showFragment(NewsFragment.newInstance(IConstants.Sources.sport[0]));
                        dwMain.closeDrawers();
                        presenter.checkInternet();
                        break;
                    case R.id.drawer_tech:
                        getSupportActionBar().setTitle("Tech");
                        presenter.showFragment(NewsFragment.newInstance(IConstants.Sources.tech[0]));
                        dwMain.closeDrawers();
                        presenter.checkInternet();
                        break;
                    case R.id.drawer_favorite:
                        getSupportActionBar().setTitle("Favorite");
                        presenter.showFragment(FavoriteFragment.newInstance());
                        dwMain.closeDrawers();
                        presenter.checkInternet();
                        break;
                    case R.id.drawer_settings:
                        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                        dwMain.closeDrawers();
                        break;
                }
                return true;
            }
        });
        View v = navMain.getHeaderView(0);
        ivHeaderIMG = (ImageView) v.findViewById(R.id.ivHeaderUserIMG);
        tvHeaderUsername = (TextView) v.findViewById(R.id.tvHeaderUsername);
        presenter.inflatePhoto();
        ivHeaderIMG.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("*/*");
            startActivityForResult(intent, IConstants.RESULT_PIC);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == IConstants.RESULT_PIC) {
                Uri imageUri = data.getData();
                presenter.writeNewPhoto(imageUri);
                Picasso.with(getApplicationContext())
                        .load(imageUri)
                        .resize(400, 400)
                        .centerCrop()
                        .into(ivHeaderIMG);
            }
        }
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
    public void inflatePhoto(String name, String img) {
        tvHeaderUsername.setText(name);
        Uri image = Uri.parse(img);
        Picasso.with(this)
                .load(image)
                .resize(400, 400)
                .centerCrop()
                .into(ivHeaderIMG);

    }

    @Override
    public void showDialog(String text, String btnText, final String action) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Warning")
                .setMessage(text)
                .setPositiveButton(btnText, (dialogInterface, i) -> startActivity(new Intent(action)))
                .setNegativeButton("cancel", null)
                .show();
    }

    @Override
    public void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_activity_container, fragment)
                .commit();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void launchFragment(String name, String title) {
        presenter.showFragment(NewsFragment.newInstance(SourcesUtils.resolveSource(name)[0]));
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void showSnackbarError(String errorText) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Error")
                .setMessage(errorText)
                .setPositiveButton("Set some", (dialogInterface, i) -> startActivity(new Intent(MainActivity.this, InterestsActivity.class)))
                .setNegativeButton("Cancel", null)
                .show();
    }
}