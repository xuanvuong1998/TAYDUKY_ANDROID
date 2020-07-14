package vuong.hx.tayduky.helpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeHelper {
    public static int CompareToNow(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date parsedDate = dateFormat.parse(date);

            Date now = new Date();

            return parsedDate.compareTo(now);

        } catch (ParseException e) {

            e.printStackTrace();
            return -2;
        }
    }

    public static String formatDate(Date date){

        return DateFormat.getDateTimeInstance().format(date);

    }
    public static String GetDateString(int year, int month, int day){
        Calendar calendar = Calendar.getInstance();

        calendar.set(year, month, day);

        return formatDate(calendar.getTime());
    }
}
