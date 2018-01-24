package com.msimonic.worldofworkout;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Forsworn on 19. 09. 2017.
 */

public class GetReady extends AppCompatActivity{
    TextView timer;
    CountDownTimer cTimer;
    int total_rest,total_rounds;
    public static ArrayList<String> Exercises;


    public GetReady() {
        Exercises=new ArrayList<String>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.getready);

        //celozaslonsko
        fullscreen();


        /*Intent myIntent = getIntent();
        Exercises = (ArrayList<String>) myIntent.getSerializableExtra("all_exercises");
        System.out.println("GRVelikost tabele je: "+Exercises.size());
        total_rest=(int)myIntent.getSerializableExtra("total_rest");
        total_rounds=(int)myIntent.getSerializableExtra("total_rounds");*/


        getReady();



    }


    public void getReady(){
        timer = (TextView) findViewById(R.id.timer);
        cTimer= new CountDownTimer(6000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("" + millisUntilFinished / 1000);

            }

            public void onFinish() {
                Bundle b = GetReady.this.getIntent().getBundleExtra("myData");
                Intent myIntent = new Intent(GetReady.this, Exercises.class);
                myIntent.putExtra("myData", b);
                startActivity(myIntent);
            }
        }.start();
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

}
