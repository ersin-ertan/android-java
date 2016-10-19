package com.nullcognition.android.truetime;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.instacart.library.truetime.TrueTime;
import com.instacart.library.truetime.TrueTimeRx;
import java.io.IOException;
import java.util.Date;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

  String ntpHosts = "1.sg.pool.ntp.org";
  String ntpHosts1 = "0.north-america.pool.ntp.org";
  String TAG = MainActivity.class.getSimpleName();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    vanilla();
    //rx();

    setContentView(R.layout.activity_main);

    Button b = (Button) findViewById(R.id.button);
    b.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (TrueTime.isInitialized()) {
          Toast.makeText(MainActivity.this, TrueTime.now().toString(), Toast.LENGTH_SHORT).show();
        }
      }
    });
  }

  private void rx() {
    TrueTimeRx.build()
        .initializeRx(ntpHosts)
        .subscribeOn(Schedulers.io())
        .subscribe(new Action1<Date>() {
          @Override public void call(Date date) {

            Log.v(TAG, "TrueTime was initialized at: %s" + date);
          }
        }, new Action1<Throwable>() {
          @Override public void call(Throwable throwable) {
            Log.e(TAG, "TrueTime init failed: ", throwable);
          }
        });
  }

  // vanilla works when I choose a server close to my location, without the need for connection timeout
  private void vanilla() {
    new AsyncTask<Void, Void, Void>() {
      @Override protected Void doInBackground(Void... params) {
        try {
          TrueTime.build()
              .withLoggingEnabled(true)
              .withNtpHost(ntpHosts)
              //.withConnectionTimeout(8_428)
              .withSharedPreferences(MainActivity.this)
              .initialize();
        } catch (IOException e) {
          e.printStackTrace();
        }
        return null;
      }
    }.execute();
  }
}
