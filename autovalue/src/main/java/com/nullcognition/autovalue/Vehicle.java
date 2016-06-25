package com.nullcognition.autovalue;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

/**
 * Created by mms on 6/25/16.
 * Showcasing the modification of builder input based on other input upon buil
 */


@AutoValue
abstract class Vehicle {

    abstract int required();

    abstract String name();

    static Builder builder(int isThisRequired, @Nullable String somestring) {
        return new AutoValue_Vehicle.Builder()
                .setRequired(isThisRequired)
                .setName("default");  // constructor will reject null values, lest they be
        // @Nullable
    }

    @AutoValue.Builder
    abstract static class Builder {
        abstract Builder setRequired(int value);

        abstract Builder setName(String value);

        abstract String name();

        abstract int required();

        abstract Vehicle autoBuild(); // not public

        public Vehicle build() {

            // or Vehicle v = autoBuild(); // then do some verification on the values
            setName(name() + Integer.toString(required()));
            return autoBuild();
        }

    }
}