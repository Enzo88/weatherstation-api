package eu.vinmasterpiece.weatherstation.util;

import java.time.Instant;
import java.util.Calendar;

public class DateUtility {

    private DateUtility() {throw new IllegalStateException("Utility class");}

    public static Instant getTodayDateWithoutTime() {
        Calendar calendar = new Calendar.Builder().setInstant(Instant.now().toEpochMilli()).build();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.toInstant();
    }

    public static java.sql.Date getTodaySQLDateWithoutTime() {
        return new java.sql.Date(getTodayDateWithoutTime().toEpochMilli());
    }
}
