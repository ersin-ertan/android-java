package com.nullcognition.autovalue;

import com.google.auto.value.AutoValue;

/**
 * Created by mms on 6/25/16.
 */
 @AutoValue
abstract class Animal {
    static Animal create(String name, int numLegs){
        return new AutoValue_Animal(name, numLegs);
    }

    abstract String name();
    abstract int numLegs();
}
