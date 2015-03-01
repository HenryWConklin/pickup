package hackuva15.pickup;

import android.os.AsyncTask;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Henry on 2/28/2015.
 */
public class PostEventTask extends AsyncTask<Event, Void, Boolean> {
    @Override
    protected Boolean doInBackground(Event... params) {
        Event event = params[0];
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://172.25.89.85:8000/games/game_list");
        JSONObject object = new JSONObject();
        try {
            object.put("game_name", event.getName());
            object.put("game_lati", event.getLatitude());
            object.put("game_lon", event.getLongitude());
            object.put("sport_type", event.getSportType());

            post.setEntity(new StringEntity(object.toString()));

            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json");

            client.execute(post);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return true;
        }
    }
}
