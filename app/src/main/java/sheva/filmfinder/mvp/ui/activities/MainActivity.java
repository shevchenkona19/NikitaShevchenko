package sheva.filmfinder.mvp.ui.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.filmfinder.IConstants;
import sheva.filmfinder.R;
import sheva.filmfinder.mvp.model.entities.cinemas.Unmain;
import sheva.filmfinder.mvp.model.entities.kinoload.Result;
import sheva.filmfinder.mvp.presenter.activities.MainActivityPresenter;
import sheva.filmfinder.mvp.ui.fragments.CinemaFragment;
import sheva.filmfinder.mvp.ui.fragments.NowFragment;
import sheva.filmfinder.mvp.ui.fragments.SoonFragment;
import sheva.filmfinder.mvp.ui.interfaces.IMainActivityView;

public class MainActivity extends MvpAppCompatActivity implements IMainActivityView, NowFragment.ITransitionOptions, CinemaFragment.ITransitionListener{
    @BindView(R.id.tbMainToolbar)
    Toolbar mainToolbar;
    @BindView(R.id.bMainNav)
    BottomNavigationView mainNav;
    @InjectPresenter
    MainActivityPresenter presenter;
    private ActivityOptions transitionActivityOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mainToolbar);
        /*Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));*/
        presenter.showFragment(NowFragment.newInstance(MainActivity.this));
        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_cinemas:
                        presenter.showFragment(CinemaFragment.newInstance());
                        break;
                    case R.id.menu_now:
                        presenter.showFragment(NowFragment.newInstance(MainActivity.this));
                        break;
                    case R.id.menu_soon:
                        presenter.showFragment(SoonFragment.newInstance());
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainContainer, fragment)
                .commit();
    }

    @Override
    public void startTransitionFrom(ImageView ivPoster, Result result) {
        transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, ivPoster,
                IConstants.keys.ANIMATION_GOING_FILM);
        Intent intent = new Intent(MainActivity.this, GoingFilmActivity.class);
        intent.putExtra(IConstants.keys.RESULT_ENTITY, result);
        startActivity(intent, transitionActivityOptions.toBundle());
    }

    @Override
    public void transite(CardView view, Unmain entity) {
        transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, view,
                IConstants.keys.ANIMATION_GOING_FILM);
        Intent intent = new Intent(MainActivity.this, CinemaActivity.class);
        intent.putExtra(IConstants.keys.CINEMA_ENTITY_KEY, entity);
        startActivity(intent, transitionActivityOptions.toBundle());
    }
}
