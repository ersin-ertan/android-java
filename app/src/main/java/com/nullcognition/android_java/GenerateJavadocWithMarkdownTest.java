package com.nullcognition.android_java;
// ersin 17/08/15 Copyright (c) 2015+ All rights reserved.


public class GenerateJavadocWithMarkdownTest{
	/**
	 * ## Large headline
	 * ### Smaller headline
	 *
	 * This is a comment that contains `code` parts.
	 *
	 * Code blocks:
	 *
	 * ```java
	 * int foo = 42;
	 * System.out.println(foo);
	 * ```
	 *
	 * Quote blocks:
	 *
	 * > This is a block quote
	 *
	 * lists:
	 *
	 * - first item
	 * - second item
	 * - third item
	 *
	 * This is a text that contains an [external link][link].
	 *

	 * [link]: http://external-link.com/
	 *
	 * @param id the user id
	 * @return the user object with the passed `id` or `null` if no user with this `id` is found
	 */
	public int meth(int id){return id;}
}
