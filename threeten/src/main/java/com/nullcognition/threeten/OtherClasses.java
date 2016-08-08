package com.nullcognition.threeten;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.Month;
import org.threeten.bp.MonthDay;
import org.threeten.bp.Period;
import org.threeten.bp.Year;
import org.threeten.bp.YearMonth;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.ValueRange;

import static org.threeten.bp.Month.MARCH;
import static org.threeten.bp.Month.MAY;

/**
 * Created by mms on 8/6/16.
 */

public class OtherClasses {

  // package provides a collection of interfaces, classes, and enums that support date and time code and, in particular, date and time calculations
  //

  public static void t() {

    Year year;
    YearMonth yearMonth;
    MonthDay monthDay = MonthDay.now();
    Month month;
    DayOfWeek dayOfWeek;

    year = Year.now();
    //year.adjustInto();
    year.atDay(LocalDate.now().getDayOfYear());
    year.format(DateTimeFormatter.BASIC_ISO_DATE);

    // TemporalField - field of date-time like month of year or hour of minute, partitioning the
    // timeline into something meaningful for humans
    // Most common units used are defined in ChronoField

    year.range(
        ChronoField.DAY_OF_WEEK); // returns a value time - range of valid values for date time field
    // ex iso day of month is 1 to 28 or 31, only max min values are provided, a weird field may have
    // valid of 1 2 3 5 6 7  with 4 excluded but a range of 1- 7
    ValueRange valueRange = ValueRange.of(1, 10);

    month = MARCH;
    month.getValue();
    month.compareTo(MAY);
    month.getLong(ChronoField.MINUTE_OF_HOUR); // not sure how this is working

    // temporal adjuster - externalize the process of adjustment, adjuster that sets the date
    // avoiding weekends, or one that sets the date to the last day of the month.
    // temporal = thisAdjuster.adjustInto(temporal);
    // temporal = temporal.with(thisAdjuster); // this is the preferred method

    //TemporalAdjusters class contains a standard set of adjusters, available as static methods. These include:

    //finding the first or last day of the month
    //finding the first day of next month
    //finding the first or last day of the year
    //finding the first day of next year
    //finding the first or last day-of-week within a month, such as "first Wednesday in June"
    //finding the next or previous day-of-week, such as "next Thursday"

    //monthDay.adjustInto(); NOT recomended
    monthDay.withDayOfMonth(1); // not sure how this work

    dayOfWeek = DayOfWeek.SUNDAY;
    // DayOfWeek.FROM.queryFrom(); // temporalQuery - retrieve information from a temporal-based object.
    //

    ChronoUnit.HOURS.between(Instant.now().minus(1, ChronoUnit.HOURS),
        Instant.now()); // returns long
    Period p = Period.between(LocalDate.of(1991, 3, 9), LocalDate.now());
  }

  public static void temporalQueries() {
    TemporalQuery query = TemporalQueries.precision();
    System.out.printf("LocalDate precision is %s%n",
        LocalDate.now().query(query)); // LocalDate precision is Days
  }

  // Returns true if the passed-in date occurs during one of the
  // family vacations. Because the query compares the month and day only,
  // the check succeeds even if the Temporal types are not the same.
  public Boolean queryFrom(TemporalAccessor date) {
    int month = date.get(ChronoField.MONTH_OF_YEAR);
    int day = date.get(ChronoField.DAY_OF_MONTH);

    // Disneyland over Spring Break
    if ((month == Month.APRIL.getValue()) && ((day >= 3) && (day <= 8))) return Boolean.TRUE;

    // Smith family reunion on Lake Saugatuck
    if ((month == Month.AUGUST.getValue()) && ((day >= 8) && (day <= 14))) return Boolean.TRUE;

    return Boolean.FALSE;
  }
}






































