package com.nullcognition.columbus;

import com.simonevertsson.columbus.Mapping;

/**
 * Created by mms on 11/5/16.
 */

public class MovieViewModel {

  @Mapping(clazz = Movie.class, field = "title") private String title;
  @Mapping(clazz = Movie.class, field = "cost") private float cost;
  @Mapping(clazz = Movie.class, field = "rating") private int rating;
  @Mapping(clazz = Person.class, field = "boughtBy") private String boughtBy;

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public float getCost() {
    return cost;
  }

  public void setCost(float cost) {
    this.cost = cost;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String boughtBy() {
    return boughtBy;
  }

  public void setBoughtBy(String bb) {
    this.boughtBy = bb;
  }

  @Override public String toString() {
    return "MVM - Title: "
        + title
        + ", Cost: "
        + String.valueOf(cost)
        + " Rating: "
        + String.valueOf(rating)
        + " BoughtBy: "
        + String.valueOf(boughtBy);
  }
}
