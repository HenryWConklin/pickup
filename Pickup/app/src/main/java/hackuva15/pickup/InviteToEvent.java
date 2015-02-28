package hackuva15.pickup;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


public class InviteToEvent extends Activity{
	private Button InviteSelectedFriendsButton;
	private ListView lview;
	private List<User> friendList;
	private ArrayList<User> invitees;
	private User u;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invite_to_event);

			friendList = u.getFriends();
			ArrayList<User> me = new ArrayList<User>();
			me.add(u);
			User f1 = new User("michael", "TJHSST", "I like to code.", new ArrayList<Event>(), new ArrayList<Event>(), me, "swimming, science, and oceans", new ArrayList<User>(), new ArrayList<Event>());
			User f2 = new User("joe", "UVA", "Wahoowa", new ArrayList<Event>(), new ArrayList<Event>(), me, "business, coding, rushing jewish fraternies without being jewish", new ArrayList<User>(), new ArrayList<Event>());
			friendList = new ArrayList<User>();
			friendList.add(f1);
			friendList.add(f2);
		
			
			
			invitees = new ArrayList<User>();
			
			InviteSelectedFriendsButton = (Button) findViewById(R.id.inviteSelectedFriendsButton);
	
			// create a list view of stuff
			lview = (ListView) findViewById(R.id.friendListView);
			ArrayAdapter<User> arrayAdapter = new ArrayAdapter<User>(this,
					android.R.layout.simple_list_item_1, friendList);
			

			// add stuff to list view
//			arrayAdapter.add(new Event(2173, "Ballin @ UVA", null, null, "01.12.15", 5, 30,
//					 7, 30, "newcomb", true, true, true,
//					"UVA IS BETTER THAN TECH WOOOOO"));
			for(User f: friendList)
			{
				arrayAdapter.add(f);
			}
			
			//update liist view
			lview.setAdapter(arrayAdapter);
			
			
			// open event info on click
			lview.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> arg0, View view, int pos,
						long arg3) {
					// get user
					Log.v("position--", "" + pos);
					User tempUser = (User) arg0.getItemAtPosition(pos);
					
					//bold users
					invitees.add(tempUser);
					
					

//					// start new intent to display info
//					Log.v("user--", tempUser.toString());
//					Intent eventInfoIntent = new Intent(MainActivity.this,
//							EventInfo.class);
//					
//					eventInfoIntent.putExtra("event", tempEvent);
//					startActivity(eventInfoIntent);
				}
			});
			
	
		InviteSelectedFriendsButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	
				//Intent i = new Intent();
				
				for(User invitee: invitees)
				{
					//send notification to invitee
				}
	
				//setResult(Activity.RESULT_OK, i);
				finish();
	
			}
		
		});
	}
}

