package com.example.team12.assignment_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }

    public void createNew(View view) {

//        Context context = getApplicationContext();
//        CharSequence text = "Testing 1..2..3.. :)";
//        int duration = Toast.LENGTH_SHORT;
//
//        Toast toast = Toast.makeText(context, text, duration);
//        toast.show();
        Intent intent = new Intent(MainActivity.this, Human.class);
        intent.putExtra("name", "John");
        intent.putExtra("age", "15");
        intent.putExtra("hobbies", "Eating, sleeping, drinking water");
        intent.putExtra("school", "Singapore School");
        intent.putExtra("course", "New Course");

        startActivity(intent);
    }
}
