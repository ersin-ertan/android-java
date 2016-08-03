package com.nullcognition.threeten.instant;

import org.threeten.bp.Clock;
import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;

/**
 * Created by mms on 8/3/16.
 */

public class TInstant {



  public static void t(){
    // instance in time, good for storing time stapms for log events, up to nano second precision
    Instant instant = Instant.now(); // gets current time from system clock sys.currtimemillis
    Instant.now(new Clock() {
      @Override public ZoneId getZone() { // for further control
        return null;
      }

      @Override public Clock withZone(ZoneId zoneId) {
        return null;
      }

      @Override public Instant instant() {
        return null;
      }
    });

    //instant.FROM.queryFrom()
    //instant.MAX;

    Instant two = instant.plusSeconds(1);
    // plus' with tempmoral amounts and units or nano, millis, sec,

    instant.minusMillis(3); // minuses

    instant.getNano(); // get methods to get various units from the instant

    //instant.adjustInto(Temporal); // Temporal has with, plus, minus, until

    instant.isBefore(Instant.now()); // is befores after, is supported?

    //instant.until() // given a temporal value exclusive, return how much time is left

    //instant.query() // not sure

    //instant.range(); // not sure
    //ValueRange
  }
}
















































































