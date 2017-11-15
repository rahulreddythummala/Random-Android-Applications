package com.example.rahul.grayscalepicture;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by rahul on 10/16/17.
 */

public class BitmapGrayer
{
    private Bitmap originalBitmap;
    private float redCoeff, blueCoeff,greenCoeff;


    public BitmapGrayer(Bitmap bitmap,float newRedCoeff, float newBlueCoeff,float newgreenCoeff)
    {
        originalBitmap = bitmap;
        setBlueCoeff(newBlueCoeff);
        setGreenCoeff(newgreenCoeff);
        setRedCoeff(newRedCoeff);
    }//end constructor

    public float getRedCoeff() {
        return redCoeff;
    }

    public void setRedCoeff(float redCoeff1) {
        if(redCoeff1 >= 0 && redCoeff1 <= 1)
        {
            if((greenCoeff + blueCoeff + redCoeff1) <= 1)
            {
                redCoeff = redCoeff1;
            }
            else
            {
                redCoeff = 1 - greenCoeff - blueCoeff;
            }
        }
    }

    public float getBlueCoeff() {
        return blueCoeff;
    }

    public void setBlueCoeff(float blueCoeff1) {

        if (blueCoeff1 >= 0 && blueCoeff1 <=1)
        {
            if ((greenCoeff + redCoeff + blueCoeff1) <= 1)
            {
                blueCoeff = blueCoeff1;
            }
            else
            {
                blueCoeff = 1 - redCoeff - greenCoeff;
            }
        }

    }

    public float getGreenCoeff() {
        return greenCoeff;
    }

    public void setGreenCoeff(float greenCoeff1) {
        if(greenCoeff1 >= 0 && greenCoeff1 <= 1)
        {
            if((redCoeff + blueCoeff + greenCoeff1) <= 1)
            {
                greenCoeff = greenCoeff1;
            }
            else
            {
                greenCoeff = 1 - redCoeff - blueCoeff;
            }
        }
    }//end set green coeff

    public Bitmap grayScale()
    {
        int width = originalBitmap.getWidth();
        int height = originalBitmap.getHeight();
        Bitmap.Config config = originalBitmap.getConfig();
        Bitmap bitmap = Bitmap.createBitmap(width,height,config);
        for (int i =0; i<width; i++) {
            for (int j = 0; j < height; j++) {
                int color = originalBitmap.getPixel(i, j);
                int shade = (int) (redCoeff * Color.red(color) + blueCoeff * Color.blue(color) + greenCoeff * Color.green(color));
                color = Color.argb(Color.alpha(color), shade, shade, shade);
                bitmap.setPixel(i, j, color);
            }
        }
        // for i
        return bitmap;
    }
}
