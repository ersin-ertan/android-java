package com.nullcognition.rave;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.uber.rave.Rave;
import com.uber.rave.RaveException;
import com.uber.rave.UnsupportedObjectException;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    SimpleModel sm = new SimpleModel();
    validateMyModel(sm);
  }

  // good when deserializing to verify the response
  public void validateMyModel(SimpleModel model) {
    try {
      Rave.getInstance().validate(model);
    } catch (UnsupportedObjectException e) {
      e.printStackTrace();
    } catch (RaveException re) {
      re.printStackTrace();
    }
  } // more jack errors Error:Execution failed for task ':rave:transformJackWithJackForDebug'.
  //> com.android.build.api.transform.TransformException: com.android.jack.api.v01.CompilationException: Failed to compile
  /**
   ExclusionStrategy builder = new ExclusionStrategy.Builder()
   .addMethod(MyExcludedClass.class, "excludedMethodName")
   .addMethod("path.to.class.MyExcludedClass", "otherMethodToExclude");
   Rave.getInstance().validate(object, builder.build());
   **/
}
