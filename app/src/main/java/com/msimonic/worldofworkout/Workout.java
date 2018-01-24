package com.msimonic.worldofworkout;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.x;

/**
 * Created by Forsworn on 26. 09. 2017.
 */

public class Workout extends AppCompatActivity{
    private WelcomeScreen welcomeScreen;
    private CustomWorkout customWorkout;
    TextView exercises,timer;
    Button done;
    public ArrayList<String> Exercises;
    int rest,rounds;
    int counter;
    CountDownTimer cTimer,countdowntimer;

    public Workout(){
        welcomeScreen = new WelcomeScreen();
        customWorkout = new CustomWorkout();
        Exercises=new ArrayList<>();
        counter=0;
        cTimer=null;
        countdowntimer=null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.workout);

        Intent i = getIntent();
        //dobimo vse vaje
        Exercises = (ArrayList<String>) i.getSerializableExtra("all_exercises");
        //dobimo počitek
        rest=(int)i.getSerializableExtra("total_rest");
        //dobimo število ciklov
        rounds=(int)i.getSerializableExtra("total_rounds");
        //Exercises = customWorkout.getExercises();
        exercises = (TextView) findViewById(R.id.exercises);
        //Celozaslonsko
        fullscreen();

        final Button doneAction = (Button)findViewById(R.id.done);
        doneAction.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("TEST_KLIK_GUMBA_DONE");
                cTimer.cancel();
                countdowntimer.cancel();
                cdTimer(rest);
            }
        });

        getReady();

        exercises.setText(Exercises.get(counter));

        //cdTimer(rest);

    }

    public void fullscreen(){
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        getWindow().getDecorView().setSystemUiVisibility(flags);
        final View decorView = getWindow().getDecorView();
        decorView
                .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
                {

                    @Override
                    public void onSystemUiVisibilityChange(int visibility)
                    {
                        if((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                        {
                            decorView.setSystemUiVisibility(flags);
                        }
                    }
                });
    }


    public void cdTimer(int rest){
        timer = (TextView) findViewById(R.id.timer);
        done = (Button) findViewById(R.id.done);
        done.setVisibility(View.INVISIBLE);
        exercises.setVisibility(View.INVISIBLE);
        System.out.println("TEST_METODE_CDTIMER");

        countdowntimer = new CountDownTimer(rest*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                System.out.println("TEST_METODE_ONTICK_CDTIMER");
                timer.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timer.setText("");
                done.setVisibility(View.VISIBLE);
                exercises.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void getReady(){
        timer = (TextView) findViewById(R.id.timer);
        cTimer= new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("" + millisUntilFinished / 1000);
                done.setVisibility(View.INVISIBLE);
                //done.invalidate();
                exercises.setVisibility(View.INVISIBLE);
                //exercises.invalidate();

            }

            public void onFinish() {
                timer.setText("");
                done.setVisibility(View.VISIBLE);
                //done.requestLayout();
                exercises.setVisibility(View.VISIBLE);
                //exercises.requestLayout();
            }
        }.start();
    }
}
