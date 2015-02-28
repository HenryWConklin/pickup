package hackuva15.pickup;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class CreateEvent extends Activity {
	private Button EnterNewEventButton;
	private EditText EventNameET;
	private TimePicker beginTimeTP;
	private TimePicker endTimeTP;
	private DatePicker datePicker;
	private EditText EventLocationET;
	private EditText EventDescriptionET;

	private String name;
	private int beginHour;
	private int beginMinute;
	private int endHour;
	private int endMinute;
	private int day;
	private int month;
	private int year;
	private String date;
	private String location;
	private String description;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_event);

		// get beginning and end times, calendar
		final TimePicker beginTimeTP = (TimePicker) findViewById(R.id.startTimeTP);
		final TimePicker endTimeTP = (TimePicker) findViewById(R.id.endTimeTP);
		final DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
		EnterNewEventButton = (Button) findViewById(R.id.enterNewEventButton);
		final EditText EventNameET = (EditText) findViewById(R.id.eventNameET);
		final EditText EventLocationET = (EditText) findViewById(R.id.eventLocationET);
		final EditText EventDescriptionET = (EditText) findViewById(R.id.eventDescriptionET);

		EnterNewEventButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				name = EventNameET.getText().toString();

				beginHour = Integer.parseInt(beginTimeTP.getCurrentHour().toString());
				beginMinute = Integer.parseInt(beginTimeTP.getCurrentMinute().toString());

				endHour = Integer.parseInt(endTimeTP.getCurrentHour().toString());
				endMinute = Integer.parseInt(endTimeTP.getCurrentMinute().toString());
				
				day = Integer.parseInt(datePicker.getDayOfMonth()+"");
				month = Integer.parseInt(datePicker.getMonth()+"");
				year = Integer.parseInt(datePicker.getYear()+"");
				date = day + "." +month + "." +year;
				
				location = EventLocationET.getText().toString();
				description = EventDescriptionET.getText().toString();

				Event retEvent = new Event(5023, name, new ArrayList<User>(), new User(), date, beginHour, beginMinute, endHour, endMinute, location, false, false, false, description); 
				Log.v("LOCATION", location);
				
				Intent i = new Intent();
				i.putExtra("retEvent", retEvent);
				setResult(Activity.RESULT_OK, i);
				finish();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_event, menu);
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

}
