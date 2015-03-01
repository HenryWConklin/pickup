package hackuva15.pickup;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;
import java.util.Date;


public class CreateEvent extends ActionBarActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener, View.OnFocusChangeListener, View.OnClickListener {

    EditText nameInput;
    EditText timeInput;
    GoogleMap map;
    Spinner sportType;

    LatLng selectedLocation;
    Date date;

    GoogleApiClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        buildGoogleApiClient();

        MapFragment mapFragment = (MapFragment)getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        nameInput = (EditText)findViewById(R.id.event_name_input);

        timeInput = (EditText)findViewById(R.id.event_time_input);
        timeInput.setOnFocusChangeListener(this);
        timeInput.setOnClickListener(this);

        date = new Date(System.currentTimeMillis());
        timeInput.setText(date.toString());

        sportType = (Spinner)findViewById(R.id.event_sport_type_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.event_sport_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sportType.setAdapter(adapter);

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

    public void submit(View v) {
        // Submit event

        //intent with parcelable latitude/longitude object
        String name = nameInput.getText().toString();

        Date beginningTime = date;
        Double latitude = selectedLocation.latitude;
        Double longitude = selectedLocation.longitude;
        String sportType = (String)this.sportType.getSelectedItem();//.getText().toString();



        Event retEvent = new Event(name, beginningTime,latitude, longitude, sportType);
        Intent i = new Intent();

        i.putExtra("retEvent", retEvent);
        setResult(Activity.RESULT_OK, i);
        finish();

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            TimeDialog d = new TimeDialog(this);
            d.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    DatePicker datePicker = (DatePicker) ((Dialog)dialog).findViewById(R.id.event_date_picker);
                    TimePicker timePicker = (TimePicker) ((Dialog)dialog).findViewById(R.id.event_time_picker);
                    Calendar calendar = Calendar.getInstance();

                    calendar.set(
                            datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(),
                            timePicker.getCurrentHour(), timePicker.getCurrentHour());
                    date = (Date)calendar.getTime();
                    timeInput.setText(date.toString());
                }
            });
            d.show();
        }
    }

    @Override
    public void onClick(View v) {
        onFocusChange(v,true);
    }
}
