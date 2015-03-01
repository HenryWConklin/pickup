package com.josephtobin.pickup;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;
import java.time.LocalDateTime;
import java.time.Month;

private String name;
private LocalDateTime beginningTime;
private LocalDateTime endTime;
private String location;
private double latitude;
private double longitude;
private String sportType;
private description;

public class Event implements Parcelable {
    public Event() {
        this.name = "UVA Wahoo Hackerball";
        this.beginningTime = LocalDateTime.of(2015,Month.FEBRUARY, 2, 6, 5, 30);
        this.endTime = LocalDateTime.of(2015,Month.FEBRUARY, 2, 10, 6, 32);
        this.location = "lambeth";
        this.latitude = 3.3;
        this.longitude = 2.1;
        this.sportType = "Hackerball";

    }

    public Event(String name, LocalDateTime beginningTime, LocalDateTime endTime, String location, double latitude, double longitude, String sportType, String description) {
        this.name = name;
        this.beginningTime = beginningTime;
        this.endTime=endTime;
        this.location=location;
        this.latitude=latitude;
        this.longitude=longitude;
        this.sportType= sportType;
    }




    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mData);
        dest.writeStringArray(this.name,
                this.beginningTime.toString(), this.endTime.toString(),
                this.location, Double.toDouble(latitude),
                Double.toDouble(longitude), sportType,
                description });


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
