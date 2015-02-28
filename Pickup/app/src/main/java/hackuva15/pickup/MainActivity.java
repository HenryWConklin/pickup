package hackuva15.pickup;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	protected static final int ActivityTwoRequestCode = 0;
	private ListView lv;
	private ImageButton createEventButton;
	private ImageButton searchButton;
	private ImageButton refreshButton;
	private List<Event> eventList;
	private User user1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_main);
		
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context,
				"we", duration);
		toast.show();
		
		  HttpClient httpclient = new DefaultHttpClient();
	       HttpPost httppost = new HttpPost("http://www.tjhsst.edu/~2016myou/SportsBeacon/update.php");
		try {
			//TP.sendGet();			
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			
			nameValuePairs.add(new BasicNameValuePair("name", "Help"));
		    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			httpclient.execute(httppost);
			
			
			
			toast = Toast.makeText(context,
					"We did it", duration);
			toast.show();
		}  catch (ClientProtocolException e) {
	         // TODO Auto-generated catch block
	     } catch (IOException e) {
	         // TODO Auto-generated catch block
	     }

		// Log.wtf("POST", "PLEASE WORK");
		// try {
		// URL url = new URL(
		// "http://www.tjhsst.edu/~2016myou/SportsBeacon/update.php");
		// HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// conn.setReadTimeout(10000);
		// conn.setConnectTimeout(15000);
		// conn.setRequestMethod("POST");
		// conn.setDoInput(true);
		// conn.setDoOutput(true);
		//
		// String params = "name=leeisthegreatest";
		//
		// //OutputStream os = conn.getOutputStream();
		// BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
		// conn.getOutputStream(), "UTF-8"));
		// writer.write(params);
		// writer.flush();
		// writer.close();
		// conn.getOutputStream().close();
		//
		// conn.connect();
		// } catch (IOException e) {
		// Log.wtf("BAD", "Unable to fetch alert information");
		// }

		user1 = new User("Jon", "UVA", "Likes long walks on the beach", null,
				null, null, "hacking", null, null);
		eventList = new ArrayList<Event>();

		// setup calendars
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DATE);
		int year = c.get(Calendar.YEAR);
		String[] months = { "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"December" };

		// date
		TextView dayView = (TextView) findViewById(R.id.date);
		dayView.setText(months[month] + " " + Integer.toString(day) + ", "
				+ Integer.toString(year));

		// create a list view of stuff
		lv = (ListView) findViewById(R.id.dayListView);
		ArrayAdapter<Event> arrayAdapter = new ArrayAdapter<Event>(this,
				android.R.layout.simple_list_item_1, eventList);

		// add stuff to list view
		eventList.add(new Event(2173, "Ballin @ UVA", null, null, "01.12.15",
				5, 30, 7, 30, "newcomb", true, true, true,
				"UVA IS BETTER THAN TECH WOOOOO"));

		// update liist view
		lv.setAdapter(arrayAdapter);

		// open event info on click
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View view, int pos,
					long arg3) {
				// get event
				Log.v("position--", "" + pos);
				Event tempEvent = (Event) arg0.getItemAtPosition(pos);

				// start new intent to display info
				Log.v("event--", tempEvent.toString());
				Intent eventInfoIntent = new Intent(MainActivity.this,
						EventInfo.class);

				eventInfoIntent.putExtra("event", tempEvent);
				startActivity(eventInfoIntent);
			}
		});

		createEventButton = (ImageButton) findViewById(R.id.createEventButton);
		createEventButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// prepares to recieve extra back
				Intent intent = new Intent(MainActivity.this, CreateEvent.class);
				startActivityForResult(intent, ActivityTwoRequestCode);

			}

		});

		// NEED TO CREATE SEARCH FUNCTION
		searchButton = (ImageButton) findViewById(R.id.searchButton);
		searchButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

			}
		});

		// GET ALL EVENTS FROM ONLINE
		refreshButton = (ImageButton) findViewById(R.id.refreshButton);
		refreshButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
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
				eventList.add(temp);
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

}

class TP {
	public final static String USER_AGENT = "Mozilla/5.0";

	//static// public static void main(String[] args) throws Exception {
	//
	// TP http = new TP();
	//
	// //System.out.println("Testing 1 - Send Http GET request");
	// //http.sendGet();
	//
	// System.out.println("\nTesting 2 - Send Http POST request");
	// http.sendPost();
	// http.sendGet();
	//
	// }
	// HTTP GET request
	static void sendGet() throws Exception {

		String url = "http://www.tjhsst.edu/~2016myou/SportsBeacon/search.php?"
				+ "725016";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

	}

	// HTTP POST request
	void sendPost() throws Exception {

		String url = "http://www.tjhsst.edu/~2016myou/SportsBeacon/update.php";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// add request header
		con.setRequestMethod("POST");
		 con.setRequestProperty("User-Agent", USER_AGENT);
		 con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = "id=1&name=davidiscool&date=4/15/15&start=3&end=4&location=microsoft&username=john";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

	}

}

// Log.wtf("POST", "PLEASE WORK");
// try {
// URL url = new URL(
// "http://www.tjhsst.edu/~2016myou/SportsBeacon/update.php");
// HttpsURLConnection conn = (HttpsURLConnection) url
// .openConnection();
// conn.setReadTimeout(10000);
// conn.setConnectTimeout(15000);
// conn.setRequestMethod("POST");
// conn.setDoInput(true);
// conn.setDoOutput(true);
//
// String params = "name=leeisthegreatest";
//
// OutputStream os = conn.getOutputStream();
// BufferedWriter writer = new BufferedWriter(
// new OutputStreamWriter(os, "UTF-8"));
// writer.write(params);
// writer.flush();
// writer.close();
// os.close();
//
// conn.connect();
// } catch (IOException e) {
// Log.wtf("BAD", "Unable to fetch alert information");
// }
//

// TP http = new TP();
// TP.main();
// Log.wtf("REACHED", "right before");
// try {
// Log.wtf("REACHPOST","after");
// http.sendPost();
// } catch (Exception e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
