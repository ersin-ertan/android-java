package com.nullcognition.autowithgson.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by mms on 11/2/16.
 */
@AutoValue public abstract class Room {

  public static Room create(int length, int width, Person person) {
    return new AutoValue_Room(length, width, person);
  }

  public static TypeAdapter<Room> typeAdapter(Gson gson) {
    return new AutoValue_Room.GsonTypeAdapter(gson);
  }

  public abstract int length();

  public abstract int width();

  public abstract Person person();
}
