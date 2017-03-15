package sheva.cardapptrue;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rvList)
    RecyclerView rvList;
    @BindView(R.id.toolbar)
    Toolbar tb;
    @BindView(R.id.dwLayout)
    DrawerLayout dwLayout;
    private RVAdapter adapter;


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

        adapter = new RVAdapter(this);
        rvList.setAdapter(adapter);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i < 100; i++) {
            adapter.addEntity("ENT");
        }
    }
}
