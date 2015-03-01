package hackuva15.pickup;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Henry on 2/28/2015.
 */
public class PostEventTask extends AsyncTask<Event, Void, Boolean> {
    @Override
    protected Boolean doInBackground(Event... params) {
        Event event = params[0];
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://172.25.89.85:8000/games/new_game/");
        JSONObject object = new JSONObject();
        try {
//            object.put("game_name", event.getName());
//            object.put("game_lati", event.getLatitude());
//            object.put("game_long", event.getLongitude());
//            object.put("sport_type", event.getSportType());
//            object.put("game_time", event.getBeginningTime().getTime());
//
//            post.setEntity(new StringEntity(object.toString()));
//            post.setParams(new BasicHttpParams().setParameter("POST", object.toString()));
//            BasicHttpParams parameters = new BasicHttpParams();
//            parameters.setParameter("game_name", event.getName());
//            parameters.setParameter("game_lati", event.getLatitude());
//            parameters.setParameter("game_long", event.getLongitude());
//            parameters.setParameter("sport_type", event.getSportType());
//            parameters.setParameter("game_time", event.getBeginningTime().getTime());
//            post.setParams(parameters);

            List<NameValuePair> pairs = new ArrayList<>();
            pairs.add(new BasicNameValuePair("game_name", event.getName()));
            pairs.add(new BasicNameValuePair("game_lati", Double.toString(event.getLatitude())));
            pairs.add(new BasicNameValuePair("game_long", Double.toString(event.getLongitude())));
            pairs.add(new BasicNameValuePair("sport_type", event.getSportType()));
            pairs.add(new BasicNameValuePair("game_time", Long.toString(event.getBeginningTime().getTime())));
            post.setEntity(new UrlEncodedFormEntity(pairs));
//            post.setHeader("Accept", "application/json");
//            post.setHeader("Content-type", "application/json");


            HttpResponse response = client.execute(post);
            Log.wtf("POST RESPONSE", response.getStatusLine().toString());
            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            Log.wtf("POST RESPONSE", in.readLine());
            if (response.getStatusLine().getStatusCode() != 200)
                throw new Exception("Error posting result");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }
}
