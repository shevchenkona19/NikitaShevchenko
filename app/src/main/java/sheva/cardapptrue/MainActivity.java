package sheva.cardapptrue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvList;
    private RVAdapter adapter;
    private Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvList = (RecyclerView) findViewById(R.id.rvList);
        tb = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(tb);

        adapter = new RVAdapter(this);
        rvList.setAdapter(adapter);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        for(int i = 0; i < 100; i++){
            adapter.addEntity("ENT");
        }
    }
}
