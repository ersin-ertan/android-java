package com.nullcognition.autovalue;

import com.google.auto.value.AutoValue;

import solid.collections.SolidList;

/**
 * Created by mms on 6/25/16.
 */
@AutoValue
abstract class Element {

    public abstract SolidList<String> names();
    // should be an abstract ImmutableContainer but there isn't any

    public static Builder builder(){
        return new AutoValue_Element.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder{
        public abstract Builder names(SolidList<String> value); // works
//        public abstract Builder names(ArrayList<String> value); // doesn't
        public abstract Element build();
    }
}

// compile error with using a list in the builder, switched to solidlist

// error docs advocating set, varargs, collection to receive from the builder