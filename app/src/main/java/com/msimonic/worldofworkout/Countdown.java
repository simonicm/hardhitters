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

public class Countdown extends AppCompatActivity{
    TextView timer;
    CountDownTimer cTimer;
    int total_rest,total_rounds;


    public Countdown() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.getready);

        //celozaslonsko
        fullscreen();






        countdowntimer();



    }


    public void countdowntimer(){
        timer = (TextView) findViewById(R.id.timer);

        Intent i = getIntent();
        Bundle bu = i.getBundleExtra("myData");
        total_rest=bu.getInt("total_rest",10);
        System.out.println("TOTAL REST je : "+total_rest);

        cTimer= new CountDownTimer(total_rest*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("" + millisUntilFinished / 1000);

            }

            public void onFinish() {
                Bundle b = Countdown.this.getIntent().getBundleExtra("myData");
                Intent in = new Intent(Countdown.this, Exercises.class);
                in.putExtra("myData", b);
                startActivity(in);
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
