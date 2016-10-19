package com.nullcognition.threeten;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.jakewharton.threetenabp.AndroidThreeTen;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.ChronoLocalDateTime;
import org.threeten.bp.temporal.TemporalAdjusters;

public class MainActivity extends AppCompatActivity {

  int i = 0;
  Test test = new Test();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AndroidThreeTen.init(getApplication());
    setContentView(R.layout.activity_main);

    TemporalAdjusters.firstDayOfMonth();
    ChronoLocalDate chronoLocalDate; // system neutral interface instead of local date
    ChronoLocalDateTime chronoLocalDateTime;

    Log.wtf("before", "i=" + i + " integer=" + test.i);

    Test.update(i, test);

    Log.wtf("after", "i=" + i + " integer=" + test.i);

    // class update

    Log.wtf("before", "i=" + i + " integer=" + test.i);

    classUpdate(i, test);

    Log.wtf("after", "i=" + i + " integer=" + test.i);

    int ii = 99;

  }

  void classUpdate(int i, Test test1) {
    i++;
    test1.i++;
  }
}

class Test {

  int i = 0;

  public static void update(int i, Test testIn) {
    i++;
    testIn.i = 10;
  }
}