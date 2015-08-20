package com.nullcognition.improveappperformance;
// ersin 19/08/15 Copyright (c) 2015+ All rights reserved.

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.leakcanary.RefWatcher;

import java.lang.ref.WeakReference;

// handlers and static variables

/* http://www.androiddesignpatterns.com/2013/01/inner-class-handler-memory-leak.html

on applications first creation, its main thread is assigned a looper which implements a message queue

lifecycle method calls, button clicks, are contained in message objects added to the message queue
which are processed serially

handlers created on the main are associated with the loopers message queue, thus messages posted to
the looper hol a reference to the handler to let the framework call Handler#handdleMessage(Message)

javas non static inner and anonymous classes hold implicit reference to their outer class

static inner do not

-----

unregister listeners

-----

use an event bus

-----

close long running threads

 */


public class ActivityLeaks extends Activity{

	// for any threads that may be long running but not needed onDestroy consider .close();
	private CThread thread;


	private static class CThread extends Thread{

		boolean isRunning = false;

		@Override public void run(){
			super.run();
			isRunning = true;
			while(isRunning){
				// do work
			}
		}

		public void close(){
			isRunning = false;
		}
	}


	private final Handler leak = new Handler(){
		@Override public void handleMessage(final Message msg){
			super.handleMessage(msg);
		}
	};

	@Override protected void onCreate(final Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		leak.postDelayed(new Runnable(){
			byte[] c = new byte[204800];

			// will hold a ref to outer class for the duration of the sleep, thus if you do lots of
			// rotations, then a forced garbage collection, nothing will be reclaimed
			@Override public void run(){
			}
		}, 1000 * 60 * 10);

		// Unregister listeners to prevent leaking your context(listening object)

		// if a Manager.getInstance(this).addListener(this); is init
		// then be sure to Manager.getInstance(this).removeListener(this);
		// before the context is destroyed, else a leak
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.activity_leaks, menu);
		return true;
	}

	Leakable leakable;

	// after clicking, will do the dump ~5 seconds later
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		int id = item.getItemId();

		if(id == R.id.action_leak_object){
			leakable = new Leakable(); // will leak leakable instance
		}
		else if(id == R.id.action_leak_activity){
			leakable = new Leakable(this); // will leak the entire activity
		}

		RefWatcher refWatcher = App.getRefWatcher(this);
		refWatcher.watch(leakable);
		if(leakable != null){ return true; }
		// instead duplicating the refWatcher.watch(leakable) code, thus the return true is push out
		// of the if to here

		return super.onOptionsItemSelected(item);
	}

	class Leakable{

		Leakable(){}

		Leakable(ActivityLeaks al){
			activityLeaks = al;
		}

		ActivityLeaks activityLeaks;
		byte[] bytes = new byte[100_000];
	}

//	@Override protected void onDestroy(){
//		super.onDestroy();
//		RefWatcher refWatcher = App.getRefWatcher(this);
//		refWatcher.watch(this);
//	}

	// solving the leak


	private static class NoLeakHandler extends Handler{

		private final WeakReference<ActivityLeaks> activityLeaksWeakReference;

		public NoLeakHandler(ActivityLeaks activityLeaks){
			activityLeaksWeakReference = new WeakReference<ActivityLeaks>(activityLeaks);
		}

		@Override public void handleMessage(final Message msg){
			super.handleMessage(msg);
			ActivityLeaks activity = activityLeaksWeakReference.get();
		}
	}


	private final        NoLeakHandler handler  = new NoLeakHandler(this);
	private static final Runnable      runnable = new Runnable(){
		@Override public void run(){
			// run
		}
	};

	@Override protected void onStart(){
		super.onStart();
		handler.postDelayed(runnable, 10_000);
	}
}
