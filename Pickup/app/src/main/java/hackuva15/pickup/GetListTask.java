package hackuva15.pickup;

import android.os.AsyncTask;
import android.util.JsonReader;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Henry on 2/28/2015.
 */
public class GetListTask extends AsyncTask<Integer,Integer,ArrayList<Event>> {


    @Override
    protected ArrayList<Event> doInBackground(Integer... params) {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://172.25.89.85:8000/games/game_list");
        String responseText="";
        try {
            HttpResponse response = client.execute(request);
            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            responseText += in.readLine();
        } catch (IOException e) {
            return null;
        }
        ArrayList<Event> events = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(responseText);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i).getJSONObject("fields");
                Event event = new Event(
                        object.getString("game_name"),
                        new Date(object.getLong("game_time")),
                        object.getDouble("game_lat"),
                        object.getDouble("game_lon"),
                        object.getString("sport_type"));
                events.add(event);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return events;

    }
}
