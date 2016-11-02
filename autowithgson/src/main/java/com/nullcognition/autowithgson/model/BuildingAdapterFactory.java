package com.nullcognition.autowithgson.model;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

/**
 * Created by mms on 11/2/16.
 */

@GsonTypeAdapterFactory public abstract class BuildingAdapterFactory implements TypeAdapterFactory {

  public static BuildingAdapterFactory create() {
    return new AutoValueGson_BuildingAdapterFactory();
  }
}
