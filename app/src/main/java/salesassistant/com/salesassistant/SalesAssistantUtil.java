package salesassistant.com.salesassistant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Utilities class
 */
public class SalesAssistantUtil {

    /**
     * Pareses a SQLite string date to a {@link Date}
     * @param strDate the date
     * @return the {@link Date}
     */
    public static Date parseDate(String strDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_ISO8601);
        dateFormat.setTimeZone(TimeZone.getTimeZone(Constants.TIME_ZONE_ISO8601));
        Date date;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
        return date;
    }

    /**
     * Formats a Date according to SQLite pattern.
     * @param date the date
     * @return the formatted date
     */
    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_ISO8601);
        dateFormat.setTimeZone(TimeZone.getTimeZone(Constants.TIME_ZONE_ISO8601));
        return dateFormat.format(date);
    }

}
