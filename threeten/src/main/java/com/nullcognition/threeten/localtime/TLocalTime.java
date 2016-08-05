package com.nullcognition.threeten.localtime;

import com.nullcognition.threeten.Optional;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.temporal.ChronoUnit;

import static org.threeten.bp.temporal.ChronoUnit.DAYS;

/**
 * Created by mms on 8/4/16.
 *
 * time with no representation of date
 */

public class TLocalTime {

  public static void t() {

    LocalDate ld = LocalDate.now();
    DAYS.between(Instant.now(), Instant.now().plus(1, ChronoUnit.DAYS));
    Optional<LocalDate> localDate = Optional.ofNullable(LocalDate.MAX);
  }
}