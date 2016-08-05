package com.nullcognition.threeten.localdate;

import org.threeten.bp.LocalDate;
import org.threeten.bp.Month;

/**
 * Created by mms on 8/4/16.
 *
 * date only, no time
 * local time-line, not time zone
 */

public class TLocalDate {

  public static void t() {

    LocalDate a = LocalDate.now(); // zone id, and clock
    LocalDate aa = LocalDate.of(2013, 1, 18);              // 18th January 2013
    LocalDate b = LocalDate.of(2013, Month.JANUARY, 18);  // 18th January 2013
    LocalDate c = LocalDate.ofYearDay(2013, 32);          // 1st February 2013 (day-of-year 32)

    c.getDayOfWeek();
    c.atTime(1, 3, 5, 9);
    c.getEra();
    c.getChronology(); // isoChronology
    //c.isAfter();
    //c.isBefore();

    // and other standard

  }
}
