package com.nullcognition.autovalue;

import com.google.auto.value.AutoValue;

/**
 * Created by mms on 6/25/16.
 */

@AutoValue
abstract class Vehicle {
    abstract int required();
    abstract String name();

    static Builder builder(int required){
        return new AutoValue_Vehicle.Builder().setRequired(required).setName("default");
    }

    @AutoValue.Builder
    abstract static class Builder {
        abstract Builder setRequired(int value);
        abstract Builder setName(String value);

        abstract String name();
        abstract int required();


        abstract Vehicle autoBuild(); // not public

        public Vehicle build(){

            // or Vehicle v = autoBuild(); // then do some verification on the values
            setName(name()+ Integer.toString(required()));
            return autoBuild();
        }

    }
}