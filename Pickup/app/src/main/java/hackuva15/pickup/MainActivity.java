package hackuva15.pickup;

import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private GoogleApiClient apiClient;
    private GoogleMap myMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildGoogleApiClient();

        MapFragment mapFragment = (MapFragment)getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
    }


    // Create event
    @Override
    public void onMapLongClick(LatLng latLng) {
        //startActivity(new Intent(this, CreateEvent.class));


        Intent intent = new Intent(MainActivity.this, CreateEvent.class);
        startActivityForResult(intent, ActivityTwoRequestCode);

        myMap.addMarker(new MarkerOptions().position(latLng).title("Sports!"));
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

                    //start async task
                    YourClassExtendingJSONTask task = new YourClassExtendingJSONTask();

                    String url;//MUST ADJUST URL
                    task.execute(url);




                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context,
                            temp.getName().toString(), duration);
                    toast.show();
                }
                break;
            }
        }
    }

    public abstract class JSONTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... arg) {
            String linha = "";
            String retorno = "";
            String url = arg[0]; // Added this line

            mDialog = ProgressDialog.show(mContext, "Aguarde", "Carregando...", true);

            // Cria o cliente de conexão
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(mUrl);

            try {
                // Faz a solicitação HTTP
                HttpResponse response = client.execute(get);

                // Pega o status da solicitação
                StatusLine statusLine = response.getStatusLine();
                int statusCode = statusLine.getStatusCode();

                if (statusCode == 200) { // Ok
                    // Pega o retorno
                    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    // Lê o buffer e coloca na variável
                    while ((linha = rd.readLine()) != null) {
                        retorno += linha;
                    }
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return retorno; // This value will be returned to your onPostExecute(result) method
        }

            @Override
            protected void onPostExecute(String result) {
                // Create here your JSONObject...
                JSONObject json = createJSONObj(result);
            customMethod(json); // And then use the json object inside this method
            mDialog.dismiss();

        }

        // You'll have to override this method on your other tasks that extend from this one and use your JSONObject as needed
        public abstract customMethod(JSONObject json);
    }


}
