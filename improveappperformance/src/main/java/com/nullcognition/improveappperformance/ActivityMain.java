package com.nullcognition.improveappperformance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

// Best practices and common pitfalls
// activity leaks, scrolling, concurrency 1+2, depreciation, jni, architecture, misc
// http://www.api-solutions.com/2015/07/10-ways-to-improve-your-android-app.html


public class ActivityMain extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		int id = item.getItemId();

		if(id == R.id.action_activityLeaks){
			startActivity(new Intent(this, ActivityLeaks.class));

			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
