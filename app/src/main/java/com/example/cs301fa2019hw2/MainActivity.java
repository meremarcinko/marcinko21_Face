package com.example.cs301fa2019hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the MainActivity of the program. It sets the listeners for the
 * touch events and the functions of each of the touch events
 *
 * @author Meredith Marcinko
 * @version Fall 2019
 */

public class MainActivity extends AppCompatActivity {

    int radioButtonState = 0;
    Face myFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myFace = (Face)findViewById(R.id.faceSurfaceView);

        //find the objects
        TextView redTextView = (TextView)findViewById(R.id.redText);
        TextView greenTextView = (TextView)findViewById(R.id.greenText);
        TextView blueTextView = (TextView)findViewById(R.id.blueText);

        SeekBar redSB = (SeekBar)findViewById(R.id.redSeekBar);
        SeekBarListener redSeekBarListener = new SeekBarListener(redTextView, greenTextView, blueTextView);
        redSB.setOnSeekBarChangeListener(redSeekBarListener);

        SeekBar greenSB = (SeekBar)findViewById(R.id.greenSeekBar);
        SeekBarListener greenSeekBarListener = new SeekBarListener(redTextView, greenTextView, blueTextView);
        greenSB.setOnSeekBarChangeListener(greenSeekBarListener);

        SeekBar blueSB = (SeekBar)findViewById(R.id.blueSeekBar);
        SeekBarListener blueSeekBarListener = new SeekBarListener(redTextView, greenTextView, blueTextView);
        blueSB.setOnSeekBarChangeListener(blueSeekBarListener);

        //radiobuttons
        RadioButtonListener radioListener = new RadioButtonListener();
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.RadioGroup);
        radioGroup.setOnCheckedChangeListener(radioListener);

        //For the spinner
        //https://stackoverflow.com/questions/5241660/how-can-i-add-items-to-a-spinner-in-android
        //I used this link to help me with adding stuff to the spinner
        List<String>hairs = new ArrayList<>();
        hairs.add("Hair 1");
        hairs.add("Hair 2");
        hairs.add("Hair 3");
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hairs);

        //setting the spinner Listener
        SpinnerListener spinnerListen = new SpinnerListener();
        Spinner spinner = (Spinner)findViewById(R.id.hairSpinner);
        spinner.setOnItemSelectedListener(spinnerListen);
        spinner.setAdapter(adp);

        //setting the listener for the randomize button
        OnClickListener buttonListner = new OnClickListener();
        Button button = (Button)findViewById(R.id.randomFaceButton);
        button.setOnClickListener(buttonListner);

    }

    //https://developer.android.com/reference/android/widget/AdapterView.OnItemSelectedListener.html
    //I used this to help with with the Spinner Listener and implementing it

    public class SpinnerListener extends Activity implements AdapterView.OnItemSelectedListener
    {
        //for selecting with hairStyle is being selected
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
        {
            if(parent.getItemAtPosition(position).toString().equals("Hair 1"))
            {
                myFace.setHairStyle(0);
            }
            if(parent.getItemAtPosition(position).toString().equals("Hair 2"))
            {
                myFace.setHairStyle(1);
            }
            if(parent.getItemAtPosition(position).toString().equals("Hair 3"))
            {
                myFace.setHairStyle(2);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent)
        {

        }


    }

    //https://developer.android.com/reference/android/view/View.OnClickListener
    //used this link to help make the onClickListener method
    public class OnClickListener implements View.OnClickListener
    {
        public void onClick(View v)
        {
            myFace.setHairStyle(myFace.randomHair());
            myFace.setEyeColor(myFace.randomize());
            myFace.setHairColor(myFace.randomize());
            myFace.setSkinColor(myFace.randomize());
        }
    }

    public class RadioButtonListener implements RadioGroup.OnCheckedChangeListener
    {
        @Override
        public void onCheckedChanged(RadioGroup group, int checked)
        {
            if(checked == R.id.radioEyes)
            {
                radioButtonState = 1;
            }
            if(checked == R.id.radioSkin)
            {
                radioButtonState = 2;
            }
            if(checked == R.id.radioHair)
            {
                radioButtonState = 3;
            }
        }
    }

    public class SeekBarListener implements SeekBar.OnSeekBarChangeListener
    {
        TextView redText;
        TextView blueText;
        TextView greenText;
        public SeekBarListener(TextView redTextBox, TextView greenTextBox, TextView blueTextBox)
        {
            redText = redTextBox;
            greenText = greenTextBox;
            blueText = blueTextBox;
        }
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean user)
        {
            int redSeek;
            int redSeekBar;
            int redSB;
            int greenSeek;
            int greenSeekBar;
            int greenSB;
            int blueSeek;
            int blueSeekBar;
            int blueSB;
            int red;
            int green;
            int blue;

            if (radioButtonState == 1)
            {
                redSeek = Color.red(myFace.getEyeColor());
                greenSeek = Color.green(myFace.getEyeColor());
                blueSeek = Color.blue(myFace.getEyeColor());
                switch(seekBar.getId())
                {
                    case R.id.redSeekBar:
                        myFace.setEyeColor(Color.argb(255,redSeek, greenSeek, blueSeek));
                        redText.setText("Red:"+progress);
                        break;
                    case R.id.greenSeekBar:
                        myFace.setEyeColor(Color.argb(255, redSeek, greenSeek + progress, blueSeek));
                        greenText.setText("Green:"+progress);
                        break;
                    case R.id.blueSeekBar:
                        myFace.setEyeColor(Color.argb(255, redSeek, greenSeek, blueSeek + progress));
                        blueText.setText("Blue:"+progress);
                        break;
                }

            }
            if (radioButtonState == 2)
            {
                redSeekBar = Color.red(myFace.getSkinColor());
                greenSeekBar = Color.green(myFace.getSkinColor());
                blueSeekBar = Color.blue(myFace.getSkinColor());
                switch(seekBar.getId())
                {
                    case R.id.redSeekBar:
                        myFace.setSkinColor(Color.argb(255, redSeekBar+progress, greenSeekBar, blueSeekBar));
                        redText.setText("Red:"+progress);
                        break;
                    case R.id.greenSeekBar:
                        myFace.setSkinColor(Color.argb(255, redSeekBar, greenSeekBar + progress, blueSeekBar));
                        greenText.setText("Green:"+progress);
                        break;
                    case R.id.blueSeekBar:
                        myFace.setSkinColor(Color.argb(255, redSeekBar, greenSeekBar, blueSeekBar + progress));
                        blueText.setText("Blue:"+progress);
                        break;
                }
            }
            if (radioButtonState == 3)
            {
                redSB = Color.red(myFace.getHairColor());
                greenSB = Color.green(myFace.getHairColor());
                blueSB = Color.blue(myFace.getHairColor());
                switch(seekBar.getId())
                {
                    case R.id.redSeekBar:
                        myFace.setHairColor(Color.argb(255, redSB+progress, greenSB, blueSB));
                        redText.setText("Red:"+progress);
                        break;
                    case R.id.greenSeekBar:
                        myFace.setHairColor(Color.argb(255, redSB, greenSB + progress, blueSB));
                        greenText.setText("Green:"+progress);
                        break;
                    case R.id.blueSeekBar:
                        myFace.setHairColor(Color.argb(255,redSB, greenSB, blueSB + progress));
                        blueText.setText("Blue:"+progress);
                        break;
                }

            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

}
