package sheva.newsprovider.mvp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.newsprovider.R;
import sheva.newsprovider.mvp.model.entities.Interest;
import sheva.newsprovider.mvp.presenters.activities.InterestsActivityPresenter;
import sheva.newsprovider.mvp.ui.adapters.InterestsActivityAdapter;
import sheva.newsprovider.mvp.ui.base.IInterestsActivityView;
import sheva.newsprovider.utils.ItemClickSupport;
import sheva.newsprovider.utils.SourcesUtils;

public class InterestsActivity extends MvpAppCompatActivity implements IInterestsActivityView {
    @BindView(R.id.tbInterestsToolbar)
    Toolbar tbInterestsToolbar;
    @BindView(R.id.rvListOfInterests)
    RecyclerView rvInterests;
    @BindView(R.id.btnInterestsDone)
    Button btnDone;
    private InterestsActivityAdapter adapter;
    private Map<String, Interest> interests;
    private boolean isChange;
    @InjectPresenter
    InterestsActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);
        ButterKnife.bind(this);
        isChange = getIntent().getBooleanExtra("CHANGE", false);
        interests = new ArrayMap<>();
        adapter = new InterestsActivityAdapter(this);
        setSupportActionBar(tbInterestsToolbar);
        rvInterests.setLayoutManager(new GridLayoutManager(this, 2));
        rvInterests.setAdapter(adapter);
        ItemClickSupport support = ItemClickSupport.addTo(rvInterests);
        support.setOnItemClickListener((recyclerView, position, v) -> {
            ImageView ivInterestImg = (ImageView) v.findViewById(R.id.ivItemInterestImg);
            ImageView ivDoneImg = (ImageView) v.findViewById(R.id.ivItemDoneImg);
            if (adapter.getList()[position]) {
                ivDoneImg.setVisibility(View.GONE);
                ivInterestImg.setVisibility(View.VISIBLE);
                adapter.getList()[position] = false;
                interests.remove(String.valueOf(position));
            } else {
                ivInterestImg.setVisibility(View.GONE);
                ivDoneImg.setVisibility(View.VISIBLE);
                TextView interestName = (TextView) v.findViewById(R.id.tvItemInterestName);
                interests.put(String.valueOf(position), new Interest(interestName.getText().toString(),
                        SourcesUtils.resolveSource(interestName.getText().toString())));
                adapter.getList()[position] = true;
            }
        });

        btnDone.setOnClickListener(view -> {
            List<Interest> list = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                if (interests.containsKey(String.valueOf(i))) {
                    list.add(interests.get(String.valueOf(i)));
                }
            }
            presenter.sendListOfInterests(list);
        });
    }

    @Override
    protected void onDestroy() {
        ItemClickSupport.removeFrom(rvInterests);
        super.onDestroy();
    }

    @Override
    public void startMainActivity() {
        if (isChange) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        } else {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }
    }
}
