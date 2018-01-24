package com.msimonic.worldofworkout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Forsworn on 19. 09. 2017.
 */

public class CustomWorkout extends AppCompatActivity{
    private WelcomeScreen welcomeScreen;
    public static ArrayList<String> Exercises;
    int indeks, rounds, rest;
    int total_rest,total_rounds;
    String temp;
    TextView rounds_minus,rounds_plus,rounds_count;
    TextView rest_minus,rest_plus,rest_count;

    public CustomWorkout() {
        welcomeScreen=new WelcomeScreen();
        Exercises=new ArrayList<String>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.custom);

        //določitev spremenljivk na začetne vrednosti da niso NULL
        rounds=4;
        rest=30;
        temp="";

        //inicializacija polj za runde
        rounds_count = (TextView) findViewById(R.id.rounds_count);
        rounds_plus = (TextView) findViewById(R.id.rounds_plus);
        rounds_minus = (TextView) findViewById(R.id.rounds_minus);

        //inicializacija polj za počitek
        rest_count = (TextView) findViewById(R.id.rest_count);
        rest_plus = (TextView) findViewById(R.id.rest_plus);
        rest_minus = (TextView) findViewById(R.id.rest_minus);

        //celozaslonsko
        fullscreen();

        //deklaracija in inicializacija gumba NEXT
        final Button next=(Button) findViewById(R.id.next_custom);

        //akcija na gumbu NEXT
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle b = new Bundle();

                Intent myIntent = new Intent(CustomWorkout.this, GetReady.class);

                //dodamo spremenljivke v BUNDLE in jim določimo unikatni ključ
                b.putSerializable("all_exercises", Exercises);
                b.putInt("total_rounds", total_rounds);
                b.putInt("stevec", 0);
                b.putInt("total_rest", total_rest);

                myIntent.putExtra("myData", b);

                startActivity(myIntent);
            }
        });

        rounds_count.setText(rounds+"");

        rounds_minus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                temp = rounds_count.getText()+"";
                if(Integer.parseInt(temp)>1) {
                    total_rounds=Integer.parseInt(temp);
                    total_rounds-=1;
                    rounds_count.setText((Integer.parseInt(temp) - 1) + "");
                }
            }
        });
        rounds_plus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                temp = rounds_count.getText()+"";
                total_rounds=Integer.parseInt(temp);
                total_rounds+=1;
                rounds_count.setText((Integer.parseInt(temp)+1)+"");
            }
        });

        rest_count.setText(rest+"");

        rest_minus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                temp = rest_count.getText()+"";
                if(Integer.parseInt(temp)>10) {
                    total_rest=Integer.parseInt(temp);
                    total_rest-=10;
                    rest_count.setText((Integer.parseInt(temp) - 10) + "");
                }
            }
        });
        rest_plus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                temp = rest_count.getText()+"";
                total_rest=Integer.parseInt(temp);
                total_rest+=10;
                rest_count.setText((Integer.parseInt(temp)+10)+"");
            }
        });
    }

    public void onCheckboxClicked(View view) {
        //če je prikazano na zaslonu
        boolean checked = ((CheckBox) view).isChecked();

        //Preverimo kateri Checkbox je bil kliknjen
        switch (view.getId()) {

            case R.id.pushups:
                if (checked) {
                    Exercises.add("Push Ups");
                } else {
                    indeks = Exercises.indexOf("Push Ups");
                    Exercises.remove(indeks);
                }
                break;

            case R.id.pullups:
                if (checked) {
                    Exercises.add("Pull Ups");
                } else {
                    indeks = Exercises.indexOf("Pull Ups");
                    Exercises.remove(indeks);
                }
                break;

            case R.id.squats:
                if (checked) {
                    Exercises.add("Squats");
                } else {
                    indeks = Exercises.indexOf("Squats");
                    Exercises.remove(indeks);
                }
                break;

        }
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
