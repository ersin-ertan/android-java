package com.nullcognition.threeten;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.jakewharton.threetenabp.AndroidThreeTen;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.ChronoLocalDateTime;
import org.threeten.bp.temporal.TemporalAdjusters;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AndroidThreeTen.init(getApplication());
    setContentView(R.layout.activity_main);

    TemporalAdjusters.firstDayOfMonth();
    ChronoLocalDate chronoLocalDate; // system neutral interface instead of local date
    ChronoLocalDateTime chronoLocalDateTime;

  }

  void doo(int i) {
  }
}
