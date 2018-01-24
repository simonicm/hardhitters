package com.msimonic.worldofworkout;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Chronometer;

/**
 * Created by Forsworn on 19. 09. 2017.
 */

public class Choose  extends AppCompatActivity{
    private WelcomeScreen welcomeScreen;

    public Choose(){
        welcomeScreen=new WelcomeScreen();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //določimo pripadajoči XML
        setContentView(R.layout.choose);

        //celozaslonsko
        fullscreen();

        final Button button_saved = (Button)findViewById(R.id.saved);
        final Button button_custom = (Button)findViewById(R.id.custom);


        //akcija na gumbu SAVED
        button_saved.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), SavedWorkout.class);
                startActivityForResult(myIntent, 0);
            }
        });


        //akcija na gumbu CUSTOM
        button_custom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), CustomWorkout.class);
                startActivityForResult(myIntent, 0);
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
