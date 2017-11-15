package com.example.rahul.grayscalepicture;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class StorageUtility
{
    public static File writeToExternalStorage(Activity activity, Bitmap bitmap) throws IOException
    {
        String storageState = Environment.getExternalStorageState();
        File file = null;
        if (storageState.equals(Environment.MEDIA_MOUNTED))
        {
            File dir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            Date dateToday = new Date();
            long ms = SystemClock.elapsedRealtime();
            String filename = "/" + dateToday + "_" + ms + ".png";
            file = new File(dir +filename);
            long freespace = dir.getFreeSpace();
            int bytesneeded = bitmap.getByteCount();
            if(bytesneeded * 1.5 < freespace)
            {
                try
                {
                    FileOutputStream fos = new FileOutputStream(file);
                    boolean result = bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
                    fos.close();
                    if(result)
                    {
                        return file;
                    }
                    else
                    {
                        throw new IOException("Cannot compress");
                    }
                }
                catch (Exception e)
                {
                    throw new IOException("Cannot open file to write");
                }
            }
            else
            {
                throw new IOException("not enough space");
            }
        }
        else
        {
            throw new IOException("No external storage found");
        }
    }
}


