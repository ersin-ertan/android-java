package com.nullcognition.columbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.simonevertsson.columbus.Columbus;

public class MainActivity extends AppCompatActivity {

  public static void p(String s) {
    System.out.println("zz ".concat(s));
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //stdMapping();
    autoMapping();
  }

  private void autoMapping() {

    Disk disk = Disk.builder().setName("tom").build();
    DiskVm diskVm = new DiskVm();

    try {
      Columbus.mapFrom(diskVm, disk);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }

    p(disk.toString());
    p(diskVm.toString());
    p("Standard Example Done");
    p("");

    // intermediate class to test using Disk instead of Disk.Builder
    Disk.Builder d2 = Disk.builder();
    // java.lang.IllegalAccessException: Cannot set private final  field java.lang.String com.nullcognition.columbus.AutoValue_Disk.name of class java.lang.Class<com.nullcognition.columbus.AutoValue_Disk>

    Disk d3 = d2.build();

    AutoValue_Disk.Builder d4 = new AutoValue_Disk.Builder();
    // java.lang.RuntimeException: Unable to start activity ComponentInfo{com.nullcognition.columbus/com.nullcognition.columbus.MainActivity}: java.lang.IllegalStateException: Missing required properties: name

    try {
      Columbus.mapTo(diskVm, d2);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }

    p(d2.build().toString());

    // will not populate in the case of d2, d3 will error, d4 will yield autovalue builder error
  }

  private void stdMapping() {

    Movie movie = new Movie();
    movie.setTitle("MovieTitle");
    movie.setRating(3);
    movie.setCost(3.5F);

    MovieViewModel movieViewModel = new MovieViewModel();
    movieViewModel.setBoughtBy("Rick");

    try {
      Columbus.mapFrom(movieViewModel, movie);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }

    p(movieViewModel.toString());

    Movie m2 = new Movie();
    Person person = new Person();

    try {
      Columbus.mapTo(movieViewModel, m2, person);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }

    p(m2.toString());
    p(person.toString());

    // working as intended, mapping is correct
  }
}
