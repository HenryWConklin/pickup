package hackuva15.pickup;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class MainActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private static final int ActivityTwoRequestCode = 0;

    private GoogleApiClient apiClient;
    private GoogleMap myMap;

    ArrayList<Event> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildGoogleApiClient();

        MapFragment mapFragment = (MapFragment)getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        eventList = new ArrayList<>();
    }

    protected synchronized void buildGoogleApiClient() {
        apiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .build();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.create_event) {
            Intent intent = new Intent(MainActivity.this, CreateEvent.class);
            startActivityForResult(intent, ActivityTwoRequestCode);
        }

        if (id == R.id.refresh) {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        }


        if (id == R.id.list_view) {
            Intent intent = new Intent(MainActivity.this, MainActivityList.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);

    }

    public void refresh() {
        GetListTask task = new GetListTask();
        task.execute();
        try {
            eventList.addAll(task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        myMap.clear();
        for (Event e : eventList) {
            MarkerOptions marker = new MarkerOptions();
            marker = marker.position(new LatLng(e.getLatitude(),e.getLongitude()))
                    .title(e.getName())
                    .snippet(e.getSportType() + e.getBeginningTime().toString());
        }
    }

    // Do pre-display map stuff here
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

        googleMap.setOnMapLongClickListener(this);

        myMap = googleMap;
        refresh();
    }


    // Create event
    @Override
    public void onMapLongClick(LatLng latLng) {
        //startActivity(new Intent(this, CreateEvent.class));


        Intent intent = new Intent(MainActivity.this, CreateEvent.class);
        startActivityForResult(intent, ActivityTwoRequestCode);

    }

    /**
     * Method to get the exra back from the create event activity NEED TO SEND
     * TO SERVER AND NOT PHONE
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (ActivityTwoRequestCode): {
                if (resultCode == Activity.RESULT_OK) {
                    // TODO Extract the data returned from the child Activity.
                    Event temp = (Event) data.getExtras().get("retEvent");
                    Toast.makeText(getApplicationContext(), temp.toString(),
                            Toast.LENGTH_LONG).show();
                    //start async task
                    //final JSONTask task = new JSONTask();

                    //task.execute(url);

                    PostEventTask task = new PostEventTask();
                    task.execute(temp);



                }
                break;
            }
        }
    }

//    public class JSONTask extends AsyncTask<String, Void, String> {
//        @Override
//        protected String doInBackground(String... arg) {
//            String linha = "";
//            String retorno = "";
//            String url = arg[0]; // Added this line
//
//            mDialog = ProgressDialog.show(mContext, "Aguarde", "Carregando...", true);
//
//            // Cria o cliente de conexão
//            HttpClient client = new DefaultHttpClient();
//            HttpGet get = new HttpGet(mUrl);
//
//            try {
//                // Faz a solicitação HTTP
//                HttpResponse response = client.execute(get);
//
//                // Pega o status da solicitação
//                StatusLine statusLine = response.getStatusLine();
//                int statusCode = statusLine.getStatusCode();
//
//                if (statusCode == 200) { // Ok
//                    // Pega o retorno
//                    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//
//                    // Lê o buffer e coloca na variável
//                    while ((linha = rd.readLine()) != null) {
//                        retorno += linha;
//                    }
//                }
//            } catch (ClientProtocolException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return retorno; // This value will be returned to your onPostExecute(result) method
//        }
//
//            @Override
//            protected void onPostExecute(String result) {
//                // Create here your JSONObject...
//                JSONObject json = createJSONObj(result);
//            customMethod(json); // And then use the json object inside this method
//            mDialog.dismiss();
//
//        }
//
//        // You'll have to override this method on your other tasks that extend from this one and use your JSONObject as needed
//        public abstract customMethod(JSONObject json){
//            //SEND JSON STUF
//        }
//    }


}
