package com.nullcognition.autowithgson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nullcognition.autowithgson.model.Building;
import com.nullcognition.autowithgson.model.BuildingAdapterFactory;
import com.nullcognition.autowithgson.model.Person;
import com.nullcognition.autowithgson.model.Room;

public class MainActivity extends AppCompatActivity {
  public static final String BUILD = "{\"address\":\"your address\"}";
  public static final String ROOM = "{\"length\":75,\"width\":76}";
  public static final String PER = "{\"name\":\"Will\",\"age\":40}";
  Gson gson;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    gson = new GsonBuilder().registerTypeAdapterFactory(BuildingAdapterFactory.create())
        //.setPrettyPrinting()
        .create();

    Building building = Building.create("my address");
    Room room = Room.create(12, 34);
    Person person = Person.builder().setName("Tom").build();

    System.out.println(gson.toJson(building)); // has type adapter predefined
    System.out.println(gson.toJson(room));
    System.out.println(gson.toJson(person));

    person = gson.fromJson(PER, Person.class);
    room = gson.fromJson(ROOM, Room.class);
    //building = gson.fromJson(BUILD, Building.class); // didn't work

    System.out.println(person.toString());
    System.out.println(room.toString());
    //System.out.println(building.toString()); // java.lang.RuntimeException: Failed to invoke public com.nullcognition.autowithgson.model.Building() with no args
  }
}
