package com.nullcognition.autowithgson.model;

import android.support.annotation.Nullable;
import com.google.auto.value.AutoValue;

/**
 * Created by mms on 11/2/16.
 */
@AutoValue public abstract class Building {

  public static Building create(String address) {
    return new AutoValue_Building(address);
  }

  @Nullable abstract String address();

  // type adapter not needed, defined in class
}

