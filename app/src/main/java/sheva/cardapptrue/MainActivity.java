package sheva.cardapptrue;

import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rvList)
    RecyclerView rvList;
    @BindView(R.id.toolbar)
    Toolbar tb;
    @BindView(R.id.dwLayout)
    DrawerLayout dwLayout;
    @BindView(R.id.navView)
    NavigationView navView;
    private RVAdapter adapter;
    private Transition

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);
        ButterKnife.bind(this);
        setSupportActionBar(tb);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dwLayout,
                tb, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dwLayout.addDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navFeed:
                    Toast.makeText(MainActivity.this, "Your feed", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.navInterests:
                    Toast.makeText(MainActivity.this, "Your interests", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.navMoney:
                    Toast.makeText(MainActivity.this, "Your money", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.navPeople:
                    Toast.makeText(MainActivity.this, "Your people", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.navResources:
                    Toast.makeText(MainActivity.this, "Your Res", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.navSport:
                    Toast.makeText(MainActivity.this, "Your sport", Toast.LENGTH_SHORT).show();
                    break;
            }
            dwLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        adapter = new RVAdapter(this);
        rvList.setAdapter(adapter);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i < 100; i++) {
            adapter.addEntity("ENT");
        }
        ItemClickSupport.addTo(rvList).setOnItemClickListener((recyclerView, position, v) -> {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, ivStart,
                        "animation1");
                startActivity(new Intent(MainActivity.this, SecondActivity.class),
                        transitionActivityOptions.toBundle());
            }
        });
    }
}
