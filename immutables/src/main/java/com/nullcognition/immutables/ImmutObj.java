package com.nullcognition.immutables;

import org.immutables.value.Value;

import java.util.List;

/**
 * Created by mms on 6/25/16.
 */

@Value.Immutable
public abstract class ImmutObj {

    public abstract int val();
    abstract String name();
    abstract List<Integer> integerList();
}
