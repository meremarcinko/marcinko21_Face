package com.example.cs301fa2019hw2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.Random;

/**
 * This is the Face Class of the program. This is where the face, eyes and hair
 * is drawn. This is also where the randomization of the color is set. The getter
 * and setter methods for the colors and hairStyle are in this class
 *
 * @author Meredith Marcinko
 * @version Fall 2019
 */

public class Face extends SurfaceView
{
    protected int skinColor;
    protected int eyeColor;
    protected int hairColor;
    protected int hairStyle;

    public Face(Context context, AttributeSet attrs)
    {
        super(context,attrs);
        setWillNotDraw(false);
        skinColor = randomize();
        eyeColor = randomize();
        hairColor = randomize();
        hairStyle = randomHair();
    }

    public int randomize()
    {
        Random randomNum = new Random();
        int red = randomNum.nextInt(255);
        int green = randomNum.nextInt(255);
        int blue = randomNum.nextInt(255);
        int randomColor = Color.argb(255,red,green,blue);
        return randomColor;
    }

    //three random hairstyles
    public int randomHair()
    {
        Random randomHair = new Random();
        int hairNum = randomHair.nextInt(3);
        return hairNum;
    }

    public void onDraw(Canvas canvas)
    {
//Hair
        Paint hair = new Paint();
        hair.setColor(hairColor);

        if(hairStyle == 0)
        {
            canvas.drawCircle(640,300,100,hair);
            canvas.drawCircle(690,200,100,hair);
            canvas.drawCircle(790,150,100,hair);
            canvas.drawCircle(890,200,100,hair);
            canvas.drawCircle(940,275,100,hair);
        }


        //face
        Paint skin = new Paint();
        skin.setColor(skinColor);
        canvas.drawCircle(800,350,200,skin);

        //right eye
        Paint eye = new Paint();
        eye.setColor(eyeColor);
        canvas.drawCircle(890,325,37,eye);

        //left eye
        canvas.drawCircle(700,325,37,eye);


        if(hairStyle == 1)
        {
            canvas.drawRect(930,150,650,250,hair);
            canvas.drawCircle(600,200,100,hair);
            canvas.drawCircle(1015,200,100,hair);
        }
        if(hairStyle == 2)
        {
            canvas.drawRect(900, 20, 700, 250, hair);
            canvas.drawRect(650, 350, 525, 400, hair);
            canvas.drawRect(1050,350,925,400,hair);

        }
        invalidate();
    }

    public void setEyeColor(int eyeColor)
    {
        this.eyeColor = eyeColor;
    }

    public int getEyeColor()
    {
        return eyeColor;
    }

    public void setHairColor(int hairColor)
    {
        this.hairColor = hairColor;
    }

    public int getHairColor()
    {
        return hairColor;
    }

    public void setHairStyle(int hairStyle)
    {
        this.hairStyle = hairStyle;
    }

    public int getHairStyle()
    {
        return hairStyle;
    }

    public void setSkinColor(int skinColor)
    {
        this.skinColor = skinColor;
    }

    public int getSkinColor()
    {
        return skinColor;
    }

}
