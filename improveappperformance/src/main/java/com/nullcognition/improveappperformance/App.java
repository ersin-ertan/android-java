package com.nullcognition.improveappperformance;
// ersin 19/08/15 Copyright (c) 2015+ All rights reserved.


import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

// to see LeakCanary in action, run app as non debug

public class App extends Application{

	public static RefWatcher getRefWatcher(Context context) {
		App application = (App) context.getApplicationContext();
		return application.refWatcher;
	}

	private RefWatcher refWatcher;

	@Override public void onCreate(){
		super.onCreate();

		// returns precofigured reference watcher to detect activities leaking on onDestroy
		refWatcher = LeakCanary.install(this);
	}
}
