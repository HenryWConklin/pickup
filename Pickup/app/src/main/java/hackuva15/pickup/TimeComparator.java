package hackuva15.pickup;

import java.util.Comparator;

/**
 * Created by Joseph A. Tobin on 3/1/2015.
 */
public class TimeComparator implements Comparator<Event>{
    @Override
    public int compare(Event e1, Event e2) {
        if(e1.getBeginningTime().before(e2.getBeginningTime())) {
            return -1;
        }
        else if(e1.getBeginningTime().after(e2.getBeginningTime())){
            return 1;
        }
        return 0;

    }
}
