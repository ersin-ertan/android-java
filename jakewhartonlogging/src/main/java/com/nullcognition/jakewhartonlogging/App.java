package com.nullcognition.jakewhartonlogging;
// ersin 21/08/15 Copyright (c) 2015+ All rights reserved.


import android.app.Application;
import android.util.Log;

import timber.log.Timber;

public class App extends Application{

	@Override public void onCreate(){
		super.onCreate();

		if(BuildConfig.DEBUG){
			Timber.plant(new Timber.DebugTree());
		}
		else{ Timber.plant(new CrashReportingTree());}
	}

	private static class CrashReportingTree extends Timber.Tree{


		@Override protected void log(final int priority, final String tag, final String message, final Throwable t){
			if(priority == Log.VERBOSE || priority == Log.DEBUG){ return; }

			FakeCrashLibrary.log(priority, tag, message);

			if(t != null){
				if(priority == Log.ERROR){ FakeCrashLibrary.logError(t);}
				else if(priority == Log.WARN){FakeCrashLibrary.logWarning(t);}
			}
		}
	}
}


final class FakeCrashLibrary{

	public static void log(int priority, String tag, String message){
		// TODO add log entry to circular buffer.
	}

	public static void logWarning(Throwable t){
		// TODO report non-fatal warning.
	}

	public static void logError(Throwable t){
		// TODO report non-fatal error.
	}

	private FakeCrashLibrary(){
		throw new AssertionError("No instances.");
	}
}
