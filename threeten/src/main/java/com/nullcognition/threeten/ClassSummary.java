package com.nullcognition.threeten;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.Month;
import org.threeten.bp.MonthDay;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.OffsetTime;
import org.threeten.bp.Period;
import org.threeten.bp.Year;
import org.threeten.bp.YearMonth;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.ZonedDateTime;

import static org.threeten.bp.temporal.ChronoUnit.DAYS;

/**
 * Created by mms on 8/6/16.
 */

public class ClassSummary {

  LocalDate localDate; // 2010-12-03
  LocalTime localTime; // 11:05:30
  LocalDateTime localDateTime; // 2010-12-03T11:05:30
  OffsetTime offsetTime; // 11:05:30+01:00
  OffsetDateTime offsetDateTime; // 2010-12-03T11:05:30+01:00
  ZonedDateTime zonedDateTime; // 2010-12-03T11:05:30+01:00 Europe/Paris
  Year year; // 2010
  YearMonth yearMonth; // 2010-12
  MonthDay monthDay; // -12-03
  Instant instant; // 2576458258.266 seconds after 1970-01-01

  // Existing JDK classes : Date, Calendar, SQL, TimeZone
  // methods to convert to and from JSR-310 type
  // not be deprecated
  // JSR-310 classes do not reference the old classes

  // Moving from one type to another

  LocalDate ifValuesAreComingFromDifferentObjets = Year.of(2013).atMonth(Month.MARCH).atDay(2);
  // but you should really create a LocalDateTime object if you can

  LocalDateTime dt = ifValuesAreComingFromDifferentObjets.atTime(12, 30);
  OffsetDateTime odt = dt.atOffset(ZoneOffset.ofHours(2));

  //Formatting and Parsing
  // Formatting supports patterns and parsing like SimpleDateFormat and more advanced formats

  // Main class is DateTimeFormatter
  // based on fields, extensible and customizable
  // Lots of common formatters provided

  // Low level interfaces for interoperability
  // Temporal, TemporalAccessor, TemporalField, TemporalUnit, TemporalAmount

  // Between
  {
    DAYS.between(LocalTime.now(), LocalTime.now().plus(1, DAYS));
    Period period = Period.between(LocalDate.now().minus(1, DAYS), LocalDate.now());
  }
}

