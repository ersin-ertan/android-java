package com.nullcognition.threeten.clock;

import org.threeten.bp.Clock;
import org.threeten.bp.Duration;
import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;

/**
 * Created by mms on 8/3/16.
 *
 * attess to current instant, date, time usin ga time soze, colkc is optional
 *
 * allows for hotswapping clocks when needed
 *
 * apps use an object to obtoin the current time rather than a static method, simplifying testing
 *
 * dependency injection into a class which requires the clock
 */

public class TClock {

  public static void t(){
    Clock a = Clock.system(ZoneId.of("Europe/Pari"));
    a = Clock.systemUTC(); // gets time and converts it to Universal Time Zone
    a = Clock.systemDefaultZone(); // dependency to the default time zone of app
    a = Clock.fixed(Instant.now(), ZoneId.of("Europe/Paris")); // will return a clock with a fixed time (for testing)
    a = Clock.offset(Clock.system(ZoneId.of("Europe/Paris")), Duration.ofSeconds(2));
    // used to simulate future or past

    //Clock.tickMinutes() not sure

  }
}
