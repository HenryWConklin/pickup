package hackuva15.pickup;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Joseph A. Tobin on 3/1/2015.
 */
public class LVArrayAdapterPartTwo extends ArrayAdapter<Event> {

    Context context;
    int layoutResourceId;
    Event data[] = null;

    public LVArrayAdapterPartTwo(Context context, int layoutResourceId, Event[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        EventHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new EventHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);

            row.setTag(holder);
        }
        else
        {
            holder = (EventHolder)row.getTag();
        }

        Event event = data[position];
        holder.txtTitle.setText(event.getName());
        if(event.getSportType().equalsIgnoreCase("Football")) {
            holder.imgIcon.setImageResource(R.mipmap.football);
        }
        else {
            holder.imgIcon.setImageResource(R.mipmap.unknown);
        }

        return row;
    }

    static class EventHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
    }
}