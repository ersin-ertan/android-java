package com.nullcognition.autovalue;

import com.google.auto.value.AutoValue;

/**
 * Created by mms on 6/25/16.
 * Showcasing the construction with a required parameter, and setting default values
 */
@AutoValue
abstract class Robot {
     abstract int required();
     abstract String name();

    static Builder builder(int required){ //
        return new AutoValue_Robot.Builder().setRequired(required).setName("someDefault");
    }

    @AutoValue.Builder
    abstract static class Builder {
        public abstract Builder setRequired(int value);
        abstract Builder setName(String value);

        abstract Robot build();

    }
}
