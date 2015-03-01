package hackuva15.pickup;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Henry on 3/1/2015.
 */
public class Utils {

    public static String getDateString(Date date){
        Calendar curr = Calendar.getInstance();
        curr.setTimeInMillis(System.currentTimeMillis());

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR);
        if (hour == 0)
            hour = 12;
        String res=String.format("%d:%02d %s",
                hour,
                cal.get(Calendar.MINUTE),
                (cal.get(Calendar.AM_PM)==Calendar.AM?"AM":"PM"));

        if (curr.get(Calendar.DAY_OF_YEAR) == cal.get(Calendar.DAY_OF_YEAR)) {}
        else if (curr.get(Calendar.WEEK_OF_YEAR)==cal.get(Calendar.WEEK_OF_YEAR)
                && curr.get(Calendar.YEAR)==cal.get(Calendar.YEAR)) {
            res += String.format(" %s", cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()));
        }
        else if (curr.get(Calendar.YEAR) == cal.get(Calendar.YEAR)) {
            res += String.format(" %s %d",
                    cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()),
                    cal.get(Calendar.DATE));
        }
        else {
            res += String.format(" %s %d, %d",
                    cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()),
                    cal.get(Calendar.DATE),
                    cal.get(Calendar.YEAR)
                    );
        }
        return res;
    }
}
