package sheva.cardapptrue;

import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemActivity extends AppCompatActivity {
    @BindView(R.id.ivTarget)
    ImageView ivTarget;
    @BindView(R.id.tbItem)
    Toolbar tbItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ButterKnife.bind(this);
        setSupportActionBar(tbItem);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            ivTarget.setTransitionName("animation1");
        }
    }
}
