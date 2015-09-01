package com.nullcognition.jooq;
// ersin 01/09/15 Copyright (c) 2015+ All rights reserved.


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

// reddit thread rebuttal
// https://www.reddit.com/r/java/comments/2uuzv1/top_10_easy_performance_optimisations_in_java/cobzlng
// thus one should be aware of the advances in the language and compiler that yields advice obsolete due
// to improvements and updates


public class PerfOptimizations{

	// Big O Notation
//	J7s ForkJoinPool for parallelising work great for eliminating latency but:
	// good for batch processing, bad for async servers, helps when scaling up
	// does not reduce complexity, the runtime does not decrease

	// use StringBuilder, avoid + and reuse the instance
	public void sBuilder(){
		StringBuilder s = new StringBuilder(6); // take a guess to the average size
		s.append("a").append(9).append("b");
		if(true){ s.append(true); }
	}

	// avoid regular expressions
	// cached so you are not computing it each instance
	static final Pattern Heavy_Use = Pattern.compile("((X)*Y)");
	// be aware of jdk methods that use it String.replaceAll(), String.split()
	// apache commons lang lib will help

	// do not use iterator(), with ArrayList it allocates 3 ints on heap
	// better use: and waste 1 int on the stack
	public void betterLoops(){
		ArrayList<String> strings = new ArrayList<>(Arrays.asList(new String[]{"hi", "there", "mate"}));

		for(String s : strings){}// bad

		int size = strings.size();
		for(int i = 0; i < size; ++i){
			String value = strings.get(i);
		}
	}

	// dont call the method if you can terminate early
	// be sure to check your nulls and do cheaper return or commonly used return call methods early
	// delay heavy computation till it is really needed

	// use primitives with stack, rather than heap which creates objects and garbage
	// calling the wrapper with a constructor param vs valueOf will create a new instance of certain
	// types that are cached by the JDK: Boolean, Byte, char, short, int, long

	// prefer iteration vs recursion in java for now

	// use entrySet for both key and values for a Map
	public void map(){
		Map<String, Integer> map = new HashMap<>();
		for(Map.Entry<String, Integer> entry : map.entrySet()){
			String key = entry.getKey();
			int value = entry.getValue();
		}
	}

	// consider EnumSet or EnumMap when number of possible keys are known in advanced and is small

	// think in sets not individual elements
}
