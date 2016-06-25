package com.nullcognition.autovalue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Animal dog = Animal.create("Dog", 4);
        Toast.makeText(this, dog.name() + Integer.toString(dog.numLegs()), Toast.LENGTH_SHORT).show();

        Robot rob = Robot.builder(3).setName("Rob").build();
        Toast.makeText(this, rob.name(), Toast.LENGTH_SHORT).show();

        Vehicle v = Vehicle.builder(1).build();
        Toast.makeText(this, v.name(), Toast.LENGTH_SHORT).show();
    }
}
