package com.nullcognition.columbus;

import com.google.auto.value.AutoValue;

/**
 * Created by mms on 11/5/16.
 */

@AutoValue public abstract class Disk {

  public static Builder builder() {
    return new AutoValue_Disk.Builder().setName("Default Name");
  }

  public abstract String name();

  @AutoValue.Builder public abstract static class Builder {
    public abstract Builder setName(String name);

    public abstract Disk build();
  }
}