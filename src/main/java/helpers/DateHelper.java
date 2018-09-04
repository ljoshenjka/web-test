package helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateHelper {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
    private static final SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss");

    public static String getTodaysDate() {
        return simpleDateFormat.format(new Date());
    }

    public static String getTodaysDateTime() {
        return simpleDateTimeFormat.format(new Date());
    }

    public static String getDateWithOffsetTemplate(String value){
        String offset = value.substring(5, value.length()-1);
        switch (value.charAt(value.length()-1)){
            case 'D': return getDateWithDaysOffset(Integer.parseInt(offset));
            case 'M': return getDateWithMonthOffset(Integer.parseInt(offset));
            case 'Y': return getDateWithYearsOffset(Integer.parseInt(offset));
        }
        return null;
    }

    public static String getDateWithDaysOffset(int offset){
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, offset);
        return simpleDateFormat.format(cal.getTime());
    }

    public static String getDateWithMonthOffset(int offset){
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, offset);
        return simpleDateFormat.format(cal.getTime());
    }

    public static String getDateWithYearsOffset(int offset){
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, offset);
        return simpleDateFormat.format(cal.getTime());
    }
}
