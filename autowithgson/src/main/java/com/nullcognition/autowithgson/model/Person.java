package com.nullcognition.autowithgson.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by mms on 11/2/16.
 */
@AutoValue public abstract class Person {


  public static TypeAdapter<Person> typeAdapter(Gson gson) {
    return new AutoValue_Person.GsonTypeAdapter(gson);
  }

  public static Builder builder() {
    return new AutoValue_Person.Builder().setName("Default Name").setAge(4);
  }

  /**
   * @see <a href="https://code.google.com/p/android/issues/detail?id=226871">Reported Jack bug</a>
   */
  //@SerializedName("n") // uncomment me to fail
  abstract String name();

  abstract int age();

  @AutoValue.Builder public abstract static class Builder {
    public abstract Builder setName(String name);
    public abstract Builder setAge(int age);
    public abstract Person build();
  }
}
