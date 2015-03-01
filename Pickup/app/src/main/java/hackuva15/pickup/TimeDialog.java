package hackuva15.pickup;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Henry on 2/28/2015.
 */
public class TimeDialog extends Dialog implements View.OnClickListener {

    private long startTime;
    public TimeDialog(Context context, long time) {
        super(context);
        startTime = time;
    }

    public TimeDialog(Context context, int theme) {
        super(context, theme);
    }

    protected TimeDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Select Event Time");
        setContentView(R.layout.dialog_time_date);

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(startTime);

        ((Button)findViewById(R.id.event_time_submit)).setOnClickListener(this);
        DatePicker date = ((DatePicker)findViewById(R.id.event_date_picker));
        TimePicker time = ((TimePicker)findViewById(R.id.event_time_picker));
        date.setCalendarViewShown(false);
        date.init(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH),null);
        time.setCurrentHour(cal.get(Calendar.HOUR_OF_DAY));
        time.setCurrentMinute(cal.get(Calendar.MINUTE));


    }


    @Override
    public void onClick(View v) {
        dismiss();
    }
}
