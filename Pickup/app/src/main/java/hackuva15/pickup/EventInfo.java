package hackuva15.pickup;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class EventInfo extends Activity {

	
	final String[] months = { "January", "February", "March", "April", "May",
			"June", "July", "August", "September", "October", "November",
			"December" };
	DecimalFormat format = new DecimalFormat("00");

	protected void onCreate(Bundle savedInstanceState) {
		// ADD ADDITIONAL VARIABLES
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_info);

		// get event
		Bundle data = getIntent().getExtras();
		Event tempEvent1 = (Event) data.getParcelable("event");

		TextView nameTV = (TextView) findViewById(R.id.nameTV);
		TextView dateTV = (TextView) findViewById(R.id.dateTV);
		TextView timeTV = (TextView) findViewById(R.id.timeTV);
		TextView locationTV = (TextView) findViewById(R.id.locationTV);
		TextView descriptionTV = (TextView) findViewById(R.id.descriptionTV);
		
		Button InviteButton = (Button) findViewById(R.id.inviteButton);

		nameTV.setText(tempEvent1.getName());
		dateTV.setText(tempEvent1.getDate());
		timeTV.setText(tempEvent1.getStartHour() + ":"
				+ tempEvent1.getStartMinute() + " to "
				+ tempEvent1.getEndHour() + ":" +  tempEvent1.getEndMinute());
		locationTV.setText(tempEvent1.getLocation());
		descriptionTV.setText(tempEvent1.getDescription());
		
		InviteButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	
				Intent intent = new Intent(EventInfo.this, InviteToEvent.class);
				startActivity(intent);
	
			}
		
		});
		
		
		
	}
	
	

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

}
