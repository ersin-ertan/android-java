package com.nullcognition.threeten.duration;

import org.threeten.bp.Duration;
import org.threeten.bp.Instant;
import org.threeten.bp.temporal.ChronoUnit;

/**
 * Created by mms on 8/4/16.
 *
 * Represents the duration between two instants on the time line.
 * Where physical duration could be infinite, adding a temporal unit like seconds
 * quantifies the time. Precision being nano secs, but not SI second based on atomic clock
 */

public class TDuration {

  public static void t() {
    Instant start = Instant.now();
    Instant end = start.plusSeconds(1).plusNanos(1);
    Duration between = Duration.between(start, end);  // PT5.000000002S
    end = start.minusSeconds(1).minusNanos(1);
    between = Duration.between(start, end); // PT-5.000000002S in the past hence negative

    Duration a = Duration.of(1, ChronoUnit.MINUTES); // PT60S

    // returning values in seconds
    Duration b = Duration.ofNanos(1);                // PT0.000000001S
    Duration c = Duration.ofMillis(1);               // PT0.001S
    Duration d = Duration.ofSeconds(1);              // PT1S
    Duration e = Duration.ofSeconds(3, 1);           // PT3.000000001S
    Duration f = Duration.ofMinutes(1);              // PT60S
    Duration g = Duration.ofHours(1);                // PT3600S

    Duration i = Duration.parse("PT1S");             // PT1S
    // will parse the string produced by toString() which is the ISO-8601 format PTnS where n is the
    // number of seconds with optional decimal part.

    // not so sure about ^ above

    //Duration dur = Duration.from(); // not sure
    a.getUnits();
    a.isNegative();
    a.toDays();
    a.abs(); // duration with positive value
    a.multipliedBy(3);
  }
}
