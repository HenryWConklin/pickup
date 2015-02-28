package hackuva15.pickup;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{

	private String username;
	private String school;
	private String bio;
	private ArrayList<Event> eventsAttending;
	private ArrayList<Event> eventsHosting;
	private ArrayList<User> friends;
	private String interest;
	private ArrayList<User> friendRequests;
	private ArrayList<Event> invites;
	private int mData;

	public User() {
		username = "";
		school = "";
		bio = "";
		eventsAttending= new ArrayList<Event>();
		eventsHosting =  new ArrayList<Event>();
		friends = new ArrayList<User>();
		interest = "";
		friendRequests = new ArrayList<User>();
		invites = new ArrayList<Event>();
	}

	public User(String username, String school, String bio,
			ArrayList<Event> eventsAttending, ArrayList<Event> eventsHosting,
			ArrayList<User> friends, String interest,
			ArrayList<User> friendRequests, ArrayList<Event> invites) {
		this.username = username;
		this.school = school;
		this.bio = bio;
		this.eventsAttending = eventsAttending;
		this.eventsHosting = eventsHosting;
		this.friends = friends;
		this.interest = interest;
		this.friendRequests = friendRequests;
		this.invites = invites;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public ArrayList<Event> getEventsAttending() {
		return eventsAttending;
	}

	public void setEventsAttending(ArrayList<Event> eventsAttending) {
		this.eventsAttending = eventsAttending;
	}

	public ArrayList<Event> getEventsHosting() {
		return eventsHosting;
	}

	public void setEventsHosting(ArrayList<Event> eventsHosting) {
		this.eventsHosting = eventsHosting;
	}

	public ArrayList<User> getFriends() {
		return friends;
	}

	public void setFriends(ArrayList<User> friends) {
		this.friends = friends;
	}

	public String getInterests() {
		return interest;
	}

	public void setInterests(String interests) {
		this.interest = interests;
	}

	public ArrayList<User> getFriendRequests() {
		return friendRequests;
	}

	public void setFriendRequest(ArrayList<User> friendRequest) {
		this.friendRequests = friendRequests;
	}

	public ArrayList<Event> getInvites() {
		return invites;
	}

	public void setInvites(ArrayList<Event> invites) {
		this.invites = invites;
	}

	public boolean addEvent(Event e) {
		return eventsAttending.add(e);
	}

	public boolean removeEvent(Event e) {
		return eventsAttending.remove(e);
	}

	public boolean hostEvent(Event e) {
		return eventsAttending.add(e);
	}

	public boolean hostLeave(Event e) {
		if (!eventsHosting.contains(e)) {
			return false;
		} else {
			return (eventsHosting.remove(e) && eventsAttending.remove(e));
		}
	}

	public boolean invite(User u, Event e) {
		return u.getInvites().add(e);
	}

	public boolean acceptInvite(User u, Event e) {
		return (this.getEventsAttending().add(e) && this.getInvites().remove(e));
	}

	public boolean rejectInvite(User u, Event e) {
		return (this.getInvites().remove(e));
	}

	public boolean friendRequest(User u) {
		return this.getFriends().add(u);
	}

	public boolean addFriendRequest(User u) {
		return (this.getFriends().add(u) && this.getFriendRequests().remove(u));
	}

	public boolean rejectFriendRequest(User u) {
		return this.getFriendRequests().remove(u);
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		// TODO Auto-generated method stub
		out.writeInt(mData);
	}
//	
//    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
//        public User createFromParcel(Parcel in) {
//            return new User(in);
//        }
//
//        public User[] newArray(int size) {
//            return new User[size];
//        }
//    };


}
