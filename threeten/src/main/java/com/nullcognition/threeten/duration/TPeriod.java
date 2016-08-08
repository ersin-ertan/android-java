package com.nullcognition.threeten.duration;

import org.threeten.bp.LocalDate;
import org.threeten.bp.Period;
import org.threeten.bp.chrono.IsoChronology;

/**
 * Created by mms on 8/6/16.
 */

public class TPeriod {

  public static void t() {
    Period p; // date based amount years months and days
    Period period = Period.ofMonths(4);
    period.plusDays(4);
    period.normalized(); // 1 Year and 15 months" will be normalized to "2 years and 3 months".
    IsoChronology chronology = (IsoChronology) period.getChronology(); // don't know why the cast
    // is needed, IsoChronology extends Chronology

    LocalDate.now().plus(period);

    // duration is equal to a quantifiable fixed numeric
    // while the concept of a day is equal to a numeric based upon the context of the request
    // ex 23, or 25 hours if...

  }
}
