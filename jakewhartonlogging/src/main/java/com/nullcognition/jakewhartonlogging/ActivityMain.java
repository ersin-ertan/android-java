package com.nullcognition.jakewhartonlogging;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;

import hugo.weaving.DebugLog;
import timber.log.Timber;

public class ActivityMain extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Timber.tag("LIFECYCLES");
		Timber.d("ACTIVITY MAIN CREATED");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		int id = item.getItemId();
		switch(id){
			case R.id.action_debugLogged:{
				debugLog("input");
				return true;
			}
			case R.id.action_timber:{
				Timber.i("INFOLOG", id, "INFO TEXT");
				return true;
			}
		}

		return super.onOptionsItemSelected(item);
	}

	// run debug build, and filter by V/ActivityMain or what the activity name is
	@DebugLog
	private String debugLog(String input){
		SystemClock.sleep(15);
		return "output";
	}
}
