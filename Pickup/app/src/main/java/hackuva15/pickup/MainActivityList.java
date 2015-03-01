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

import java.util.ArrayList;


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

            return super.onOptionsItemSelected(item);


    }
}
