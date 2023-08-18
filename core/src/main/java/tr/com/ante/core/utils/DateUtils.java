package tr.com.ante.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static Date getCurrentDate() {
        return new Date();
    }

    public static String formatDate(Date date, String format) {
        try {
            if (date == null) {
                return null;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date parseDate(String date, String format) {
        try {
            if (date == null) {
                return null;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date);
        } catch (Exception e) {
            return null;
        }
    }
}