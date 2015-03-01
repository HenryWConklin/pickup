package hackuva15.pickup;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class MainActivityList extends ActionBarActivity {
    private static final int ActivityTwoRequestCode = 0;
    private ArrayList<Event> eventList;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_list);

        eventList = new ArrayList<Event>();


        // create a list view of stuff
        lv = (ListView) findViewById(R.id.dayListView);

        ArrayAdapter<Event> arrayAdapter = new ArrayAdapter<Event>(this,
                android.R.layout.simple_list_item_1, eventList);

        // add stuff to list view
        eventList.add(new Event());
        eventList.add(new Event());
        eventList.add(new Event());

        refresh();
        lv.setAdapter(arrayAdapter);


        // update list view
        lv.setAdapter(arrayAdapter);

//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> arg0, View view, int pos,
//                                    long arg3) {
//                // get event
//                Log.v("position--", "" + pos);
//                Event tempEvent = (Event) arg0.getItemAtPosition(pos);
//
//                // start new intent to display info
//                Log.v("event--", tempEvent.toString());
//                Intent eventInfoIntent = new Intent(MainActivityList.this,
//                        EventInfo.class);
//
//                eventInfoIntent.putExtra("event", tempEvent);
//                startActivity(eventInfoIntent);
//            }
//        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();


            if (id == R.id.create_event) {
                Intent intent = new Intent(MainActivityList.this, CreateEvent.class);
                startActivityForResult(intent, ActivityTwoRequestCode);
            }
            if (id == R.id.map_view) {
                Intent intent = new Intent(MainActivityList.this, MainActivity.class);
                startActivity(intent);
            }

            if(id == R.id.refresh) {
                Intent intent = new Intent(MainActivityList.this, MainActivityList.class);
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
    }
}
