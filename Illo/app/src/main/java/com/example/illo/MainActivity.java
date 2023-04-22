package com.example.illo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView countdownText;
    private Button countdownButton;
    private ImageView exerciseView;
    private ImageButton nextExerciseButton;

    private CountDownTimer countDownTimer;
    private long timeLeftMS = 300000; // 15 min
    private boolean timerRunning;

    private String[][] exerciseBank;
    private String packageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        packageName = getPackageName();

        exerciseBank = new String[][]{
                new String[]{"exercise_ballsqueezehandstretch"},
                new String[]{"exercise_bodycrunches_blueshirtman"},
                new String[]{
                        "exercise_breakdancer_blueshirtwoman",
                        "exercise_breakdancer_whiteshirtwoman"
                },
                new String[]{
                        "exercise_pushups_blueshirtman",
                        "exercise_pushups_blueshirtwoman"
                }
        };

        // gives references to objects in the view
        countdownText = findViewById(R.id.countdown_text);
        countdownButton = findViewById(R.id.countdown_toggle);
        nextExerciseButton = findViewById(R.id.nextExerciseButton);
        exerciseView = findViewById(R.id.exerciseView);

//        int imageResource = getResources().getIdentifier(
//                "@drawable/exercise_bodycrunches_blueshirtman",
//                null,
//                this.getPackageName()
//        );
//        exerciseView.setImageResource(imageResource);


        // implements click behavior for button object
        countdownButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startStop(); // button click triggers class method
            }
        });

        nextExerciseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String nextExercise = pickRandomExercise(exerciseBank);
                Log.v("MainActivity", nextExercise);
                int imageResource = getResources().getIdentifier(
                        nextExercise,
                        null,
                        packageName
                );
                exerciseView.setImageResource(imageResource);
            }
        });

        updateTimer(); // initial call to form timer at app launch
    }

    // helper method to eliminate checking bools in onClick event
    public void startStop(){
        if(timerRunning){
            stopTimer();
        } else {
            startTimer();
        }
    }
    public void startTimer(){
        countDownTimer = new CountDownTimer(timeLeftMS, 10) { // ticks every 1000ms
            @Override
            public void onTick(long l) {
                timeLeftMS = l; // update time left
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start(); // starts on creation
        countdownButton.setText("Pause");
        timerRunning = true;
    }
    public void stopTimer(){
        countDownTimer.cancel();
        countdownButton.setText("Start");
        timerRunning = false;
    }

    // update text in the view
    public void updateTimer(){
        // building some useful values
        int minutes = (int) timeLeftMS / 60000; // 60000ms = 1 minute
        int seconds = (int) (timeLeftMS % 60000) / 1000; // % gives remaining seconds, 1000ms = 1 sec

        String timeLeft;
        // keeps leading zeros
        timeLeft = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        countdownText.setText(timeLeft);
    }

    public String pickRandomExercise(String[][] bank){
        Random r = new Random();
        int exercise = new Random().nextInt(bank.length);
        String[] graphics = bank[exercise];
        int graphic = new Random().nextInt(graphics.length);
        return "@drawable/" + graphics[graphic];
    }
}