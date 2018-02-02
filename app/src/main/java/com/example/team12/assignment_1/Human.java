package com.example.team12.assignment_1;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Human extends AppCompatActivity {

    private Uri file;
    String mPath = "";
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_human);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }
        Intent intent = getIntent();

        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String age = intent.getStringExtra("age");
        String hobbies = intent.getStringExtra("hobbies");
        String school = intent.getStringExtra("school");
        String course = intent.getStringExtra("course");
        String contact = intent.getStringExtra("contact");
        String email = intent.getStringExtra("email");
        String profile = intent.getStringExtra("profile");

        ((TextView)findViewById(R.id.tbId)).setText(id);
        ((TextView)findViewById(R.id.tbName)).setText(name);
        ((TextView)findViewById(R.id.tbAge)).setText(age);
        ((TextView)findViewById(R.id.tbHobbies)).setText(hobbies);
        ((TextView)findViewById(R.id.tbSchool)).setText(school);
        ((TextView)findViewById(R.id.tbCourse)).setText(course);
        ((TextView)findViewById(R.id.tbContact)).setText(contact);
        ((TextView)findViewById(R.id.tbEmail)).setText(email);

//        final String encodedString = "data:image/jpg;base64, ....";
//        final String pureBase64Encoded = encodedString.substring(encodedString.indexOf(",")  + 1);
//        final byte[] decodedBytes = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
//        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
//
//        ((ImageView)findViewById(R.id.imgProfile)).setImageBitmap(decodedBitmap);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

            }
        }
    }

    // 3.
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        bitmap = BitmapFactory.decodeFile(mPath, options);

        ((ImageView)findViewById(R.id.imgProfile)).setImageBitmap(bitmap);
    }

    public void changePicture(View view){

        dbHelper db = new dbHelper(this);
        try
        {

            // 1.
            File tempFile = File.createTempFile("camera", ".png", getExternalCacheDir());
            mPath = tempFile.getAbsolutePath();
            Uri uri = Uri.fromFile(tempFile);

            Toast.makeText(this,"The URI of Image: " + uri, Toast.LENGTH_LONG).show();

            // 2.
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, 100);
        }
        catch(IOException ex)
        {

        }


        HumanClass h = new HumanClass();

        h.setId(((TextView)findViewById(R.id.tbId)).getText().toString());
        h.setName(((TextView)findViewById(R.id.tbName)).getText().toString());
        h.setAge(Integer.parseInt(((TextView)findViewById(R.id.tbAge)).getText().toString()));
        h.setEmail(((TextView)findViewById(R.id.tbEmail)).getText().toString());
        h.setContact(Integer.parseInt(((TextView)findViewById(R.id.tbContact)).getText().toString()));
        h.setHobbies(((TextView)findViewById(R.id.tbHobbies)).getText().toString());
        h.setCourse(((TextView)findViewById(R.id.tbCourse)).getText().toString());
        h.setSchool(((TextView)findViewById(R.id.tbSchool)).getText().toString());
        h.setProfile(encodeImage(((BitmapDrawable)((ImageView)findViewById(R.id.imgProfile)).getDrawable()).getBitmap()));

        db.updatePic(h);













//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        dbHelper db = new dbHelper(this);
//        String encodedImage = "";
//
//        if (intent.resolveActivity(getPackageManager()) != null) {
//
//            ContentValues values = new ContentValues(1);
//            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");
//            file = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
//            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);



//                InputStream imageStream = getContentResolver().openInputStream(file);
//
//            InputStream imageStream = getContentResolver().openInputStream(file);
//
//                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
//                encodedImage = encodeImage(selectedImage);
//                Toast.makeText(this,"ENCODED: " + encodedImage,Toast.LENGTH_LONG).show();
//
//
//
//            startActivityForResult(intent, 100);

//            String[] projection = new String[]{
//                    MediaStore.Images.ImageColumns._ID,
//                    MediaStore.Images.ImageColumns.DATA,
//                    MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
//                    MediaStore.Images.ImageColumns.DATE_TAKEN,
//                    MediaStore.Images.ImageColumns.MIME_TYPE
//            };
//
//            Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null,
//                    null, MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC");;

//            while (cursor.moveToNext()) {
//                String imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA));
//                File imageFile = new File(imagePath);
//                if (imageFile.canRead() && imageFile.exists()) {
//                    try
//                    {
                        ///OLD///
//                        Uri tempUri = Uri.fromFile(imageFile);
//                        Bitmap profile = MediaStore.Images.Media.getBitmap(getContentResolver(), tempUri);
//                        Toast.makeText(this,"URI LAHHH "+tempUri,Toast.LENGTH_LONG).show();
//                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                        profile.compress(Bitmap.CompressFormat.PNG, 0, stream);
//                        Log.d("This is the byte : ",stream.toByteArray().toString());
                        ///OLD////

//                        HumanClass h = new HumanClass();
//
//                        h.setId(((TextView)findViewById(R.id.tbId)).getText().toString());
//                        h.setName(((TextView)findViewById(R.id.tbName)).getText().toString());
//                        h.setAge(Integer.parseInt(((TextView)findViewById(R.id.tbAge)).getText().toString()));
//                        h.setEmail(((TextView)findViewById(R.id.tbEmail)).getText().toString());
//                        h.setContact(Integer.parseInt(((TextView)findViewById(R.id.tbContact)).getText().toString()));
//                        h.setHobbies(((TextView)findViewById(R.id.tbHobbies)).getText().toString());
//                        h.setCourse(((TextView)findViewById(R.id.tbCourse)).getText().toString());
//                        h.setSchool(((TextView)findViewById(R.id.tbSchool)).getText().toString());
//                        //h.setProfile(encodedImage);

                        //add the HumanClass as input
//                        boolean result = db.updatePic(h);
//                        if(result == false){
//                            Toast.makeText(this,"Update FAILED", Toast.LENGTH_LONG).show();
//                        }
//                        else
//                            Toast.makeText(this,"Updated",Toast.LENGTH_LONG).show();
//                    }
//                    catch (IOException ioEx){
//                        ioEx.printStackTrace();
//                    }
//                    break;
//                }
//            }
//        }
//        else {
//            Toast.makeText(this, "Camera not found", Toast.LENGTH_LONG).show();
//        }

    }


    private String encodeImage(Bitmap bm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == 100) {
//            if (resultCode == RESULT_OK) {
//                Bitmap picture = data.getParcelableExtra("data");
//                ((ImageView)findViewById(R.id.imgProfile)).setImageBitmap(picture);
//                //((ImageView)findViewById(R.id.imgProfile)).setImageURI(file);
//                Toast.makeText(this, "File Location: " + file, Toast.LENGTH_LONG).show();
//            }
//        }
//    }

}
