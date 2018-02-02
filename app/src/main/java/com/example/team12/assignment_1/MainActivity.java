package com.example.team12.assignment_1;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    dbHelper db;
    ArrayList<HumanClass> hcList = new ArrayList<HumanClass>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new dbHelper(this);

        Cursor rs = db.getAllResult();
        while(rs.moveToNext()){

            HumanClass hc = new HumanClass();
            hc.setId(rs.getString(0));
            hc.setName(rs.getString(1));
            hc.setAge(rs.getInt(2));
            hc.setHobbies(rs.getString(3));
            hc.setSchool(rs.getString(4));
            hc.setCourse(rs.getString(5));
            hc.setContact(rs.getInt(6));
            hc.setEmail(rs.getString(7));
            hc.setProfile(rs.getString(8));

            hcList.add(hc);
        }

        LinearLayout buttonsLayout = findViewById(R.id.buttonLayout);

        for(int i=0;i<hcList.size();i++){
            final HumanClass hc = hcList.get(i);
            RelativeLayout r = new RelativeLayout(this);
            r.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            //r.setLayoutParams(new LinearLayout.LayoutParams(356,40));
            r.setPadding(0,10,0,0);
            r.setGravity(Gravity.CENTER_HORIZONTAL);

            Button b = new Button(this);
            b.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            b.getLayoutParams().width=Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 350, getResources().getDisplayMetrics()));
            b.getLayoutParams().height=Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics()));
            b.setBackgroundResource(R.drawable.button);
            b.setTextColor(Color.WHITE);
            b.setText("HUMAN " + (i+1) + " - " + hc.getName());
            b.setGravity(Gravity.CENTER_HORIZONTAL);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Context context = getApplicationContext();
//                    CharSequence text = hc.getName();
//                    int duration = Toast.LENGTH_SHORT;
//
//                    Toast toast = Toast.makeText(context, text, duration);
//                    toast.show();

                    Intent intent = new Intent(MainActivity.this, Human.class);
                    intent.putExtra("id", hc.getId());
                    intent.putExtra("name", hc.getName());
                    intent.putExtra("age", ""+hc.getAge());
                    intent.putExtra("hobbies", hc.getHobbies());
                    intent.putExtra("school", hc.getSchool());
                    intent.putExtra("course", hc.getCourse());
                    intent.putExtra("email", hc.getEmail());
                    intent.putExtra("contact", ""+hc.getContact());
                    intent.putExtra("profile", hc.getProfile());

                    startActivity(intent);
                }
            });

            r.addView(b);
            buttonsLayout.addView(r);
        }
    }

    public void createNew(View view) {

//        Context context = getApplicationContext();
//        CharSequence text = "Testing 1..2..3.. :)";
//        int duration = Toast.LENGTH_SHORT;
//
//        Toast toast = Toast.makeText(context, text, duration);
//        toast.show();


//        Intent intent = new Intent(MainActivity.this, Human.class);
//        intent.putExtra("name", "John");
//        intent.putExtra("age", "15");
//        intent.putExtra("hobbies", "Eating, sleeping, drinking water");
//        intent.putExtra("school", "Singapore School");
//        intent.putExtra("course", "New Course");
//
//        startActivity(intent);

        Intent intent = new Intent(MainActivity.this, createHuman.class);
        startActivity(intent);
    }

    public void showAll(View view) {
        Cursor rs = db.getAllResult();
        if(rs.getCount() == 0){
            showMessage("ERROR", "NOTHING FOUND");
            return;
        }
        StringBuffer buff = new StringBuffer();
        while(rs.moveToNext()){
            buff.append("Name: " + rs.getString(1) + "\n");
            buff.append("Age: " + rs.getString(2) + "\n");
            buff.append("Hobbies: " + rs.getString(3) + "\n");
            buff.append("Picture: " + rs.getString(8) + "\n\n");
        }

        showMessage("Data", buff.toString());
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }



}
