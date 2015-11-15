package com.nullcognition.eliminatingcodeovehead;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/* be mindful of CPU, IO, Memory, to save battery life

CPU:
- don't nest multi-pass layouts
- lazily compute complex data when needed
- cache heavy computational results for re-use
- consider renderscript for performance
- keep work off the main thread

Memory:
-use object pools and caches to reduce churn
- be mindful of the overhead of enums
- do not allocate inside the draw path
- use specialized collections for android

i/o:
- batch operations with reasonable back off policies
- use gzip or binary serialization format
- cache data offline with TTLs for reloading
- use JobScheduler api to batch across os

*/
public class MainActivity extends AppCompatActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	// whats next after macro is micro, which can be a macro optimization

	public List<Integer> badFor(List<Date> dates){

		List<Integer> integerList = new ArrayList<>();
		for(Date d : dates){ integerList.add(d.getDate()); }
		return Collections.unmodifiableList(integerList);
	}

	public List<Integer> betterFor(List<Date> dates){
		// will save you from the double array allocation on resize, and the size() calls
		int           inputListSize = dates.size();
		List<Integer> integerList   = new ArrayList<>(inputListSize);
		for(int i = 0; i < inputListSize; ++i){ integerList.add(dates.get(i).getDate()); }
		return Collections.unmodifiableList(integerList);
	}

	public String withStringBuilders(List<String> names){
		// will save you from the double array allocation on resize, and the size() calls
		int           inputListSize   = names.size();
		int           averageUserName = 6;
		StringBuilder stringBuilder   = new StringBuilder(names.size() * averageUserName);
		for(int i = 0; i < inputListSize; ++i){
			if(stringBuilder.length() > 0){ stringBuilder.append(", "); }
			stringBuilder.append(names.get(i));
		}
		return stringBuilder.toString();
		// will give Tom, john, jack, and either have to do 1-2 re allocations, or having a silghtly big array
		// size based on the inputs
	}

	public void badForWithString(){
		Integer  key        = new Integer(1);
		int      valueCount = 10;
		String[] files      = new String[valueCount];
		String[] temp       = new String[valueCount];
		for(int i = 0; i < valueCount; i++){
			files[i] = new String(key.toString() + "." + i);
			temp[i] = new String(key.toString() + "." + i + ".tmp");
		}
		// 3 string appends, via the string builder internals
	}

	public void betterForWithString(){
		Integer  key        = new Integer(1);
		int      valueCount = 10;
		String[] files      = new String[valueCount];
		String[] temp       = new String[valueCount];

		StringBuilder sb = new StringBuilder();
		sb.append(key.toString()).append(".");
		// now the beginning, which was the same for both instances is save and build upon per iteration
		for(int i = 0; i < valueCount; i++){
			sb.append(i);
			files[i] = new String(sb.toString());
			temp[i] = new String(sb.append(".tmp").toString());
			// but since the appended  stringbuild will be use per each string, we must reset the string
			// to a point prior to when changes were made  key. (|x| or |.|t|m|p|) via setLength to move the pointer
			// back to a location
		}
	}

	public void bestForWithString(){
		Integer  key        = new Integer(1);
		int      valueCount = 10;
		String[] files      = new String[valueCount];
		String[] temp       = new String[valueCount];

//		StringBuilder sb = new StringBuilder();
//		sb.append(key.toString()).append(".");
		StringBuilder sb = new StringBuilder(key.toString()).append(".");
		// takes the length of the initial string arg +16 for its capacity
		int endOfCommonText = sb.length();

		for(int i = 0; i < valueCount; i++){
			sb.append(i);
			files[i] = new String(sb.toString());
			temp[i] = new String(sb.append(".tmp").toString());
			sb.setLength(endOfCommonText);
			// so after appends, the length could be 8, but setting the length upon completion back to 4
			// filling the extra values with empty chars, and allowing new appends to fill that space back up
		}
	}

	public void getRes(){
		Resources res = getResources(); // cache the value, use the same reference

		res.toString();
		res.getAnimation(0);
		res.getAssets();
		res.getBoolean(0);
	}

	// 4 ways to call methods on android java 6/7ish
	// invokestatic - static methods are linked at compile time
	// invokespecial - constructors, private methods, supermethods, statically linked to the compiler
	// invokevirtual - other class methods
	// invokeinterface - interface methods

	// invokevirtual creates a method table with the method and address of the call in memory. The hierarchy is traversed to the top
	// Object -> Context -> ContextThemeWrapper -> Activity -> MyActivity,  and starting with Object each method is mapped into the table.
	// when a overriden method is inserted, it also overrides the method call entry and address, example .toString would be overriden 5 times
	// if implemented per class.
	//
	// Since the method lookup is a hash(with a small cost), then the jump to the method, done within a for loop can lead to extra time/cpu costs

	// invokevirtual with the interface will require the class lookup as an additional step prior to the method table lookup

	// extra study - ploymorphic virtual method calls -> monomorphic vs bimorphic vs megamorphic
	// Concurrency implications and locks -> read/write when lock, act out of lock, synchronized vs concurrent lock types
}
