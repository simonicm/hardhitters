package com.msimonic.worldofworkout;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Forsworn on 4. 12. 2017.
 */

public class Exercises extends AppCompatActivity {
    TextView exercise_name;
    public ArrayList<String> all_exercises;
    int rounds;
    int stevec;
    public CustomWorkout cw;

    public Exercises(){
        all_exercises=new ArrayList<>();
        rounds=0;
        stevec=0;
        cw=new CustomWorkout();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.exercises);

        Intent i = getIntent();
        Bundle b = i.getBundleExtra("myData");

        all_exercises = (ArrayList<String>) b.getSerializable("all_exercises");
        rounds= b.getInt("total_rounds", -1);
        stevec = b.getInt("stevec",-1);

        exercise_name = (TextView) findViewById(R.id.exercise_name);

        exercise_name.setText(all_exercises.get(stevec));

        if(stevec<=all_exercises.size()){
            stevec++;
        }else{
            stevec=0;
        }

        //celozaslonsko
        fullscreen();

        final Button done=(Button) findViewById(R.id.done);

        //akcija na gumbu DONE
        done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle bu = Exercises.this.getIntent().getBundleExtra("myData");
                Intent in = new Intent(Exercises.this, Countdown.class);
                bu.putInt("stevec",stevec);
                in.putExtra("myData", bu);
                startActivity(in);
            }
        });

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
