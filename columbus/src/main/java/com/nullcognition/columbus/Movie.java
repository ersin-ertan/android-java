package com.nullcognition.columbus;

/**
 * Created by mms on 11/5/16.
 */

public class Movie {

  private String title;
  private float cost;
  private int rating;

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

  @Override public String toString() {
    return "Movie - Title: "
        + title
        + ", Cost: "
        + String.valueOf(cost)
        + " Rating: "
        + String.valueOf(rating);
  }
}
