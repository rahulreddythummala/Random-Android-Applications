package com.example.rahul.grayscalepicture;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.icu.lang.UCharacter;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rahul.grayscalepicture.BitmapGrayer;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final static int Code_c = 1;
    private ImageView imageView;
    private Bitmap bitmap;
    private BitmapGrayer grayer; //Version 1: Graying the color part.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageView);
        PackageManager manager = this.getPackageManager();
        if(manager.hasSystemFeature(PackageManager.FEATURE_CAMERA))
        {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i,Code_c);
        }
        else
        {
            Toast.makeText(this,"Sorry, device does not have a camera",Toast.LENGTH_LONG).show();
        }
    }// end oncreate

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==Code_c && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap)extras.get("data");
            grayer = new BitmapGrayer(bitmap,.34f,.33f,.33f);
            imageView.setImageBitmap(bitmap);
        }
    }
    public void savePicture(View v)
    {
        try
        {

        }
        catch (IOException e)
        {

        }
    }
}
