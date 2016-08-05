package com.nullcognition.threeten;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.jakewharton.threetenabp.AndroidThreeTen;
import org.threeten.bp.temporal.TemporalAdjusters;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AndroidThreeTen.init(getApplication());
    setContentView(R.layout.activity_main);

    TemporalAdjusters.firstDayOfMonth();

  }

  void doo(int i) {
  }
}
