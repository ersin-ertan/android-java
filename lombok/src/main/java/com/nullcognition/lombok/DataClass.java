package com.nullcognition.lombok;
// ersin 23/08/15 Copyright (c) 2015+ All rights reserved.


import lombok.AccessLevel;
import lombok.Getter;

public class DataClass{

	@Getter(AccessLevel.PUBLIC)
	public int age = 10;
}
