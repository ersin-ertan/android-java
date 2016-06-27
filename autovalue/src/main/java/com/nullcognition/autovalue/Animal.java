package com.nullcognition.autovalue;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

/**
 * Created by mms on 6/25/16.
 * Showcasing the standard usage for immutable values
 */
 @AutoValue
abstract class Animal implements Parcelable {
    static Animal create(String name, int numLegs){
        return new AutoValue_Animal(name, numLegs);
    }

    abstract String name();
    abstract int numLegs();
}
