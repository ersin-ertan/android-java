package com.nullcognition.jooq;
// ersin 31/08/15 Copyright (c) 2015+ All rights reserved.


import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// http://blog.jooq.org/2015/08/11/top-10-useful-yet-paranoid-java-programming-techniques/
public class ParanoidJava{

	public void stringLiteral(){
		String s = null;
		// comparing a string could yield a NullPointerException
		if(s.equals("not null")){} // s is null so .equals will not work
		if("not null".equals(s)){} // which allows s to be null
	}

	public void returns(){
		File f = null;
		if(f.isDirectory()){
			String[] files = f.list(); // returns a string array or null, thus check the api
			if(null != files){
				// do work
			}
		}
	}

	public void longTermStability(){
		// subject to change
		String s = "the";
		if(-1 != s.indexOf('l')){} // returns the first index of the given code point or -1
		// the binary result can not be trusted thus be explicit
		if(s.indexOf('l') >= 0){} // in case returns are extended lower than -1
	}

	public void accidentalAssingment(){
		int var;
		// if(var = 5) could be missed
		// if(5 = var) this doesn't look right
	}

	public void nullAndLength(){
		int[] array = null;
		if(null != array && array.length > 0){}
	}

	public final void unextensibleMethods(){ } // don't touch

	final void immutables(final String input){
		final String compares = "test";
	}

	<T> void bad(T value){ bad(Collections.singletonList(value));}
	<T> void bad(List<T> values){}

	{ // which could be abused like
		@SuppressWarnings("all")
		Object t = (Object) (List) Arrays.asList("abc");
		bad(t); // raw cast to object
	}

	// ensure that your user is providing the right types
	final <T> void good(final T value){
		if(value instanceof List){ good((List<?>) value); }
		else{ good(Collections.singletonList(value)); }
	}
	final <T> void good(final List<T> values){ }

	public void switches(){
		switch("val"){
			case "a":
				break;
			default:
				throw new IllegalArgumentException(this.getClass().getSimpleName() + ".switches default");
		}
	}

	public void switches2(){
		switch("val"){
			case "a":{ break;}
			case "b":{
				int i = 3;
				break;
			}
			default:{
				throw new IllegalArgumentException(this.getClass().getSimpleName() + ".switches default");
			}
		}
	}

}
