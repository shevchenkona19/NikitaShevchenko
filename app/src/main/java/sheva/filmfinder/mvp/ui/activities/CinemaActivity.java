package sheva.filmfinder.mvp.ui.activities;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.filmfinder.IConstants;
import sheva.filmfinder.R;
import sheva.filmfinder.mvp.model.entities.cinemas.CinemaDataEntity;
import sheva.filmfinder.mvp.model.entities.cinemas.Unmain;

public class CinemaActivity extends AppCompatActivity {

    @BindView(R.id.cinemMapView)
    MapView mapView;
    @BindView(R.id.cvCinemaMap)
    CardView cvMap;
    @BindView(R.id.fabCinemaBuildRoute)
    FloatingActionButton fabBuildRoute;
    @BindView(R.id.tvCinemaName)
    TextView tvName;
    @BindView(R.id.tvCinemaAddress)
    TextView tvAddress;
    @BindView(R.id.tvCinemaPhone)
    TextView tvPhone;
    @BindView(R.id.tvCinemaVoteCount)
    TextView tvVotecount;
    @BindView(R.id.tvCinemaVoteScore)
    TextView tvVoteScore;
    @BindView(R.id.rbVoteBar)
    RatingBar rbVoteBar;
    @BindView(R.id.ivCinemaPic)
    ImageView ivCinemaPic;
    @BindView(R.id.clCinemaLayout)
    ConstraintLayout constraintLayout;
    private GoogleMap googleMap;
    private double lat = 0, lon = 0;
    private Unmain entity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema);
        entity = getIntent().getParcelableExtra(IConstants.keys.CINEMA_ENTITY_KEY);
        ButterKnife.bind(this);
        constraintLayout.setTransitionName(IConstants.keys.ANIMATION_GOING_FILM);

        mapView.onCreate(savedInstanceState);
        if (isAddressValid(entity.getAddress().replace(" ", "+"))) {
            mapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    setGoogleMap(googleMap);
                    getGoogleMap().setLatLngBoundsForCameraTarget(new LatLngBounds(new LatLng(lat, lon), new LatLng(lat, lon)));
                    getGoogleMap().addMarker(new MarkerOptions()
                            .position(new LatLng(lat, lon))
                            .title("Cinema"));
                }
            });
        } else {
            cvMap.setVisibility(View.GONE);
            fabBuildRoute.setVisibility(View.VISIBLE);
        }

        tvName.setText(entity.getName());
        tvPhone.setText(entity.getPhone());
        tvAddress.setText(entity.getAddress());
        tvVotecount.setText(entity.getCountVote());
        tvVoteScore.setText(Double.toString(Double.parseDouble(entity.getVote().replace(",", ".")) / 2) + "/5");
        rbVoteBar.setRating((Float.parseFloat(entity.getVote().replace(",", ".")) / 2));
        Picasso.with(this)
                .load(IConstants.BASE_URL + entity.getImage())
                .into(ivCinemaPic);

        fabBuildRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(entity.getAddress()));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        mapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public boolean isAddressValid(String address) {
        Geocoder geocoder = new Geocoder(CinemaActivity.this);
        List<Address> addresses = new ArrayList<>();
        try {
            addresses.addAll(geocoder.getFromLocationName(address, 1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (addresses.size() > 0) {
            lat = addresses.get(0).getLatitude();
            lon = addresses.get(0).getLongitude();
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    public void setGoogleMap(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    public GoogleMap getGoogleMap() {
        return googleMap;
    }
}
