package hackuva15.pickup;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class CreateEvent extends ActionBarActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener, TextView.OnEditorActionListener {

    EditText nameInput;
    GoogleMap map;
    LatLng selectedLocation;
    GoogleApiClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        buildGoogleApiClient();

        MapFragment mapFragment = (MapFragment)getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        nameInput = (EditText)findViewById(R.id.event_name_input);
        nameInput.setOnEditorActionListener(this);
    }

    protected synchronized void buildGoogleApiClient() {
        apiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMyLocationEnabled(true);

        // Get an initial location to center the map on
        LocationManager locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider =locationManager.getBestProvider(criteria, true);
        Location myLocation = locationManager.getLastKnownLocation(provider);

        // getLocation returns null sometimes
        if (myLocation != null)
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myLocation.getLatitude(), myLocation.getLongitude()), 16));

        googleMap.setOnMapClickListener(this);
        googleMap.setOnMapLongClickListener(this);

        map = googleMap;
        if (selectedLocation == null) {
            selectedLocation = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        map.clear();
        map.addMarker(new MarkerOptions().position(latLng));
        selectedLocation = latLng;
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        onMapClick(latLng);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            submit(v);
            return true;
        }
        return false;
    }

    public void submit(View v) {
        // Submit event

        //intent with parcelable latitude/longitude object
        String name = __.getText().toString();
        String beginningTime = LocalDateTime.of();
        String endTime = LocalDateTime.of();
        String location = __.getText().toString();
        Double latitude = selectedLocation.getLatitude();
        Double longitude = selectedLocation.getLongitude();
        String sportType = __.getText().toString();
        String description = __.getText().toString();

        Event retEvent = new Event(name, beginningTime, endTime, location, latitude, longitude, sportType, description);
        Intent i = new Intent();

        i.putExtra("retEvent", retEvent);
        setResult(Activit.RESULT_OK, i);
        finish();

    }
}
