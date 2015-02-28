package hackuva15.pickup;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {
	private int id;
	private String name;
	private ArrayList<User> participants;
	private User host;
	private int startHour;
	private int startMinute;
	private int endHour;
	private int endMinute;
	private String location;
	private boolean beginner;
	private boolean intermediate;
	private boolean experienced;
	private String description;
	private int mData;
	private String startMeridian;
	private String endMeridian;
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Event(int id, String name, ArrayList<User> participants,
			User host, String date, int startMinute, int startHour,
			int endMinute, int endHour, String location, boolean beginner,
			boolean intermediate, boolean experienced, String description) {
		this.id = id;
		this.name = name;
		this.participants = participants;
		this.startMinute = startMinute;
		this.startMinute = startMinute;
		this.endHour = endHour;
		this.endMinute = endMinute;
		this.location = location;
		this.beginner = beginner;
		this.intermediate = intermediate;
		this.experienced = experienced;
		this.host = host;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<User> getParticipants() {
		return participants;
	}

	public void setParticipants(ArrayList<User> participants) {
		this.participants = participants;
	}

	
	

	public int getStartHour() {
		return startHour;
	}

	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}

	public int getStartMinute() {
		return startMinute;
	}

	public void setStartMinute(int startMinute) {
		this.startMinute = startMinute;
	}

	public int getEndHour() {
		return endHour;
	}

	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}

	public int getEndMinute() {
		return endMinute;
	}

	public void setEndMinute(int endMinute) {
		this.endMinute = endMinute;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isBeginner() {
		return beginner;
	}

	public void setBeginner(boolean beginner) {
		this.beginner = beginner;
	}

	public boolean isIntermediate() {
		return intermediate;
	}

	public void setIntermediate(boolean intermediate) {
		this.intermediate = intermediate;
	}

	public boolean isExperienced() {
		return experienced;
	}

	public void setExperienced(boolean experienced) {
		this.experienced = experienced;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public boolean removeParticipant(User u) {
		return participants.remove(u);
	}


	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		 dest.writeInt(mData);
			dest.writeStringArray(new String[] { Integer.toString(id), this.name,
				this.participants.toString(), this.host.toString(),
				Integer.toString(startHour), Integer.toString(startMinute),
				Integer.toString(endHour), Integer.toString(endMinute),
				location, Boolean.toString(beginner),
				Boolean.toString(intermediate), Boolean.toString(experienced),
				description });
	}

	public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
		public Event createFromParcel(Parcel in) {
			return new Event(in);
		}

		public Event[] newArray(int size) {
			return new Event[size];
		}
	};

	// example constructor that takes a Parcel and gives you an object populated
	// with it's values
	private Event(Parcel in) {
		mData = in.readInt();
	}

}
