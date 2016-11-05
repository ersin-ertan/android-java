package com.nullcognition.rave;

import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.annotation.StringDef;
import com.uber.rave.annotation.MustBeFalse;
import com.uber.rave.annotation.Validated;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mms on 11/4/16.
 */

@Validated(factory = SampleFactory.class) public class SimpleModel {

  public static final String MATCHED = "matched";
  public static final String MATCHING = "matching";
  public static final String NOT_MATCHED = "notmached";
  private String notNullField;
  private String canBeNullField;
  private List<String> names = new ArrayList<>(5);

  public SimpleModel() {
  }

  @NonNull @StringVals public String getNotNullField() {
    return notNullField;
  }

  @Size(5) public List<String> getNames() {
    return names;
  }

  @MustBeFalse public boolean getIsFalse() {
    return names.size() > 2 && canBeNullField != null;
  }

  @StringVals public String getSomeString() {
    return MATCHED;
  }

  @StringDef({ MATCHED, MATCHING, NOT_MATCHED }) @Retention(RetentionPolicy.SOURCE)
  @interface StringVals {
  }
}