package com.nullcognition.jooq;
// ersin 31/08/15 Copyright (c) 2015+ All rights reserved.


import java.util.Objects;

import javax.xml.transform.Source;

// http://blog.jooq.org/2013/08/20/10-subtle-best-practices-when-coding-java/
public class SubtlePractices{

	public void beforeEvent(){
		// super.beforeEvent();
		int myLogic = 9;
	}

	// free memory in inverse order of allocation
	public void afterEvent(){
		int myLogic = 9;
		// super.afterEvent();
	}

	// because you might need to change functionality for the future, SPIs should try to use context/
	// parameter objects
	interface MessageContext{

		String message();
		Integer id();
		Source messageSource(); // Source is place holder
	}


	interface EventListenr{

		void message(MessageContext context);
	}
	// this way you are flexible to change return values, and always working with 1 type

	//  an anonymous, local or inner class, check if you can make it static or even a regular
	// top-level class. Avoid returning anonymous, local or inner class instances from methods
	// to the outside scope.
	public Runnable outerScopedRunnable(){
		return new Runnable(){
			@Override public void run(){
				// I have a ref to the outer scope, thus can cause leaks
			}
		};
	}

	// avoid returning nulls from methods... not sure about this one
	// to be used for uninitialized and absent semantics

	// arrays and collections should never null
//	Null does not help in finding the error
//	Null does not allow to distinguish I/O errors from the File instance not being a directory
//	Everyone will keep forgetting about null, here

	// avoid state, be functional.  Pass state through method arguments. Manipulate less object state.
	public void functionalMethods(int a, int b){
		int     result       = calculate(a, b);
		boolean isAcceptable = isAcceptable(result);
	}
	private boolean isAcceptable(final int result){return false;}
	private int calculate(final int a, final int b){
		int c = a + a + a + a + b + b; // doing work with state here
		return c;
	}

	// short circuit, exit early if logic does not hold
	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (other == null) return false;
		// Rest of equality logic...
		return false;
	}

	public boolean equalsWithObjects(Object other){
		return Objects.equals(this, other); //  return (a == null) ? (b == null) : a.equals(b);
		// Rest of equality logic...
	}
	// thus consider Objects .convenience methods: deepequals, toString, etc

	// if in full control of the api, make methods final to save yourself from accidental overrides
	// and architectural thinking

	// consider the methods, based on what the compiler infers, calling acceptAll("message", 1, var)
	// is ambiguous, use Object... objects because T can be inferred to Object type
	// not quite sure about this one, I understand the ambiguity, but not the problem with using generic types
	<T> void acceptAll(T... all){}
	<T> void acceptAll(String message, T... all){}




}
