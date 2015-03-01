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
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.ExecutionException;


public class MainActivityList extends ActionBarActivity {
    private static final int ActivityTwoRequestCode = 0;
    private ArrayList<Event> eventList;
    private ListView lv;
    private Event[] eventListArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_list);

        eventList = new ArrayList<Event>();


        // create a list view of stuff
        lv = (ListView) findViewById(R.id.dayListView);

        ArrayAdapter<Event> arrayAdapter = new ArrayAdapter<Event>(this,
                android.R.layout.simple_list_item_1, eventList);

//        eventListArray = new Event[eventList.size()];
//        for(int i = 0; i < eventList.size(); i ++) {
//            eventListArray[i]=eventList.get(i);
//        }
//        LVArrayAdapterPartTwo arrayAdapter = new LVArrayAdapterPartTwo(this.getApplicationContext(),
//                R.layout.listview_item_row, eventListArray);

        // add stuff to list view
        eventList.add(new Event());
        eventList.add(new Event());
        eventList.add(new Event());
        refresh();
        Collections.sort(eventList, new TimeComparator());
        //sortByLocation();

        // update list view
//        eventListArray = new Event[eventList.size()];
//        for(int i = 0; i < eventList.size(); i ++) {
//            eventListArray[i]=eventList.get(i);
//        }
        lv.setAdapter(arrayAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity_list, menu);
        return true;
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
            if(id == R.id.refresh) {
                Intent intent = new Intent(MainActivityList.this, MainActivityList.class);
                startActivity(intent);
            }
            if (id == R.id.map_view) {
                Intent intent = new Intent(MainActivityList.this, MainActivity.class);
                startActivity(intent);
            }

            return super.onOptionsItemSelected(item);


    }

    }
