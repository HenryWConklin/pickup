package hackuva15.pickup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import android.util.Log;
 
//import javax.net.ssl.HttpsURLConnection;

public class TestPOST {
   private final String USER_AGENT = "Mozilla/5.0";
	 
   public static void main(String[] args) throws Exception {
   
	   try{
	   URL url = new URL("http://www.tjhsst.edu/~2016myou/SportsBeacon/update.php");
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setReadTimeout(10000);
		conn.setConnectTimeout(15000);
		conn.setRequestMethod("POST");
		conn.setDoInput(true);
		conn.setDoOutput(true);

		String params = "name=leeisthegreatest";

		OutputStream os = conn.getOutputStream();
		BufferedWriter writer = new BufferedWriter(
		        new OutputStreamWriter(os, "UTF-8"));
		writer.write(params);
		writer.flush();
		writer.close();
		os.close();

		conn.connect();
		}
		catch (IOException e){
			//Log.wtf("BAD", "Unable to fetch alert information");
		}
//      TestPOST http = new TestPOST();
//   
//   	//System.out.println("Testing 1 - Send Http GET request");
//   	//http.sendGet();
//   
//      System.out.println("\nTesting 2 - Send Http POST request");
//      http.sendPost();
//      http.sendGet();
   
   }
 
	// HTTP GET request
   private void sendGet() throws Exception {
   
      String url = "http://www.tjhsst.edu/~2016myou/SportsBeacon/search.php?"+"725016";
   
      URL obj = new URL(url);
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();
   
   	// optional default is GET
      con.setRequestMethod("GET");
   
   	//add request header
      con.setRequestProperty("User-Agent", USER_AGENT);
   
      int responseCode = con.getResponseCode();
      System.out.println("\nSending 'GET' request to URL : " + url);
      System.out.println("Response Code : " + responseCode);
   
      BufferedReader in = new BufferedReader(
              new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();
   
      while ((inputLine = in.readLine()) != null) {
         response.append(inputLine);
      }
      in.close();
   
   	//print result
      System.out.println(response.toString());
   
   }
 
	// HTTP POST request
   void sendPost() throws Exception {
   
      String url = "http://www.tjhsst.edu/~2016myou/SportsBeacon/update.php";
      URL obj = new URL(url);
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();
   
   	//add request header
      con.setRequestMethod("POST");
   	//con.setRequestProperty("User-Agent", USER_AGENT);
   	//con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
   
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
   
      BufferedReader in = new BufferedReader(
              new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();
   
      while ((inputLine = in.readLine()) != null) {
         response.append(inputLine);
      }
      in.close();
   
   	//print result
      System.out.println(response.toString());
   
   }
	
}
