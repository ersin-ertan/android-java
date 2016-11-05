package com.nullcognition.rave;

import android.support.annotation.NonNull;
import com.uber.rave.BaseValidator;
import com.uber.rave.ValidatorFactory;

/**
 * Created by mms on 11/4/16.
 */

public final class SampleFactory implements ValidatorFactory {

  @NonNull @Override public BaseValidator generateValidator() {
    return new SampleFactory_Generated_Validator();
  }
}
