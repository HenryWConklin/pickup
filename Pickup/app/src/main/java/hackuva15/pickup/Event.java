package hackuva15.pickup;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Date;




public class Event implements Parcelable {

    private String name;
    private Date beginningTime;

    private double latitude;
    private double longitude;
    private String sportType;

    private int mData;

    public Event() {
        this.name = "UVA Wahoo Hackerball";
        this.beginningTime = new Date(2015,2,2,10,0,0);
        this.latitude = 3.3;
        this.longitude = 2.1;
        this.sportType = "Hackerball";

    }

    public Event(String name, Date beginningTime,double latitude, double longitude, String sportType) {
        this.name = name;
        this.beginningTime = beginningTime;
        this.latitude=latitude;
        this.longitude=longitude;
        this.sportType= sportType;
    }

    public String toString() {
        return "\n" + name + "\n" + Utils.getDateString(beginningTime) + "\n" + sportType + "\n";
    }




    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mData);
        dest.writeString(name);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeLong(beginningTime.getTime());
        dest.writeString(sportType);
//        dest.writeStringArray(new String[] {this.name,
//                this.beginningTime.toString(), Double.toString(latitude),
//                Double.toString(longitude), sportType,
//                });

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
        name = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        beginningTime = new Date(in.readLong());
        sportType = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBeginningTime() {
        return beginningTime;
    }

    public void setBeginningTime(Date beginningTime) {
        this.beginningTime = beginningTime;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sportType) {
        this.sportType = sportType;
    }


    public int getmData() {
        return mData;
    }

    public void setmData(int mData) {
        this.mData = mData;
    }
}
