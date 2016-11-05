package com.nullcognition.columbus;

/**
 * Created by mms on 11/5/16.
 */

public class Person {

  private String boughtBy;

  public String boughtBy() {
    return boughtBy;
  }

  public void setBoughtBy(String bb) {
    this.boughtBy = bb;
  }

  @Override public String toString() {
    return "Person - BoughtBy: " + boughtBy;
  }
}
