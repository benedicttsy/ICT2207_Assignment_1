package com.example.team12.assignment_1;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Blob;

public class createHuman extends AppCompatActivity {

    dbHelper db;
    HumanClass hc = new HumanClass();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_human);
        db = new dbHelper(this);
    }

    public void createProfile(View view) {
        hc.setId(((EditText)findViewById(R.id.tbId)).getText().toString());
        hc.setName(((EditText)findViewById(R.id.tbName)).getText().toString());
        hc.setAge(Integer.parseInt(((EditText)findViewById(R.id.tbAge)).getText().toString()));
        hc.setHobbies(((TextView)findViewById(R.id.tbHobbies)).getText().toString());
        hc.setSchool(((TextView)findViewById(R.id.tbSchool)).getText().toString());
        hc.setCourse(((TextView)findViewById(R.id.tbCourse)).getText().toString());
        hc.setContact(Integer.parseInt(((TextView)findViewById(R.id.tbContact)).getText().toString()));
        hc.setEmail(((TextView)findViewById(R.id.tbEmail)).getText().toString());



        boolean isInserted = db.insertAllField(hc.getId(),hc.getName(),hc.getAge(),hc.getHobbies(),hc.getSchool(),hc.getCourse(),hc.getContact(),hc.getEmail(),"none");


        if(isInserted == true){


//            Cursor rs = db.getAllResult();
//            if(rs.getCount() == 0){
//                showMessage("ERROR", "NOTHING FOUND");
//                return;
//            }
//            StringBuffer buff = new StringBuffer();
//            while(rs.moveToNext()){
//                buff.append("Name: " + rs.getString(1) + "\n");
//                buff.append("Age: " + rs.getString(2) + "\n");
//                buff.append("Hobbies: " + rs.getString(3) + "\n\n");
//            }
//
//            showMessage("Data", buff.toString());
            Intent intent = new Intent(createHuman.this, MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,"KABOOM",Toast.LENGTH_LONG).show();
        }
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

}
