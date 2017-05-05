package sheva.newsprovider.mvp.model.repositories;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import javax.inject.Inject;

public class GeoRepository {
    private LocationManager manager;

    @Inject
    public GeoRepository(Context context) {
        manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    public double getLon() {
        Location location = manager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        return location.getLongitude();

    }

    public double getLat() {
        Location location = manager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        return location.getLatitude();
    }
}
