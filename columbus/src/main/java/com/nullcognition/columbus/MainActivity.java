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
