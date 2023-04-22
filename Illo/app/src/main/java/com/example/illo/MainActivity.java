package com.example.illo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
<<<<<<< HEAD
import android.widget.Spinner;
=======
>>>>>>> 59fae08 (Change to single-screen app. Major rehaul.)
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity
                            implements AdapterView.OnItemSelectedListener{

    private TextView countdownText;
    private Button countdownButton;
<<<<<<< HEAD


    private ImageView exerciseGraphicView;
    private TextView exerciseNameView;
    private TextView instructionView;

=======
    private ImageView exerciseView;
>>>>>>> 59fae08 (Change to single-screen app. Major rehaul.)
    private ImageButton nextExerciseButton;

    private CountDownTimer countDownTimer;
    private long timeLeftMS = 300000; // 15 min
    private boolean timerRunning;

    private String[][] exerciseBank;
    private String packageName;
<<<<<<< HEAD

    private ExerciseBank exercise_bank;
    private ActivitySourceBank source_bank;

    private ActivitySource selectedSource;
=======
>>>>>>> 59fae08 (Change to single-screen app. Major rehaul.)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
        try{
            exercise_bank = ExerciseBank.getInstance(this.getBaseContext());
        } catch (Exception e){
            Log.v("EXERCISEBANK", e.toString());
        }
        source_bank = ActivitySourceBank.getInstance(exercise_bank);
        packageName = getPackageName();

=======
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
>>>>>>> 59fae08 (Change to single-screen app. Major rehaul.)

        // gives references to objects in the view
        countdownText = findViewById(R.id.countdown_text);
        countdownButton = findViewById(R.id.countdown_toggle);
        nextExerciseButton = findViewById(R.id.nextExerciseButton);
<<<<<<< HEAD

        exerciseGraphicView = findViewById(R.id.exerciseGraphicView);
        instructionView = findViewById(R.id.instructionView);
        exerciseNameView = findViewById(R.id.exerciseTitleView);

        Spinner spinno = findViewById(R.id.activitySourceSpinner);
        ArrayAdapter ad = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                source_bank.getExerciseSets().keySet().toArray()
        );
        ad.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        spinno.setAdapter(ad);
        spinno.setOnItemSelectedListener(this);

        // default value, for now
        selectedSource = source_bank.getExerciseSets().get("Freeweight Exercises");
        Log.v("SELECTED_SOURCE", selectedSource.toString());
=======
        exerciseView = findViewById(R.id.exerciseView);

//        int imageResource = getResources().getIdentifier(
//                "@drawable/exercise_bodycrunches_blueshirtman",
//                null,
//                this.getPackageName()
//        );
//        exerciseView.setImageResource(imageResource);

>>>>>>> 59fae08 (Change to single-screen app. Major rehaul.)

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
<<<<<<< HEAD
                Exercise nextExercise = selectedSource.nextExercise();
                Log.v("MainActivity", nextExercise.toString());

                exerciseNameView.setText(nextExercise.getName());

                int imageResource = getResources().getIdentifier(
                        "@drawable/"+nextExercise.randomGraphic(),
                        null,
                        packageName
                );
                exerciseGraphicView.setImageResource(imageResource);

                instructionView.setText(nextExercise.getInstructionSet());
=======
                String nextExercise = pickRandomExercise(exerciseBank);
                Log.v("MainActivity", nextExercise);
                int imageResource = getResources().getIdentifier(
                        nextExercise,
                        null,
                        packageName
                );
                exerciseView.setImageResource(imageResource);
>>>>>>> 59fae08 (Change to single-screen app. Major rehaul.)
            }
        });

        updateTimer(); // initial call to form timer at app launch
<<<<<<< HEAD
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selected_item = adapterView.getSelectedItem().toString();

        System.out.println("SELECTED" + selected_item);

        selectedSource = source_bank.getExerciseSets().get(
                adapterView.getSelectedItem().toString()
        );
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

//    public String pickRandomExercise(String[][] bank){
//        Random r = new Random();
//        int exercise = new Random().nextInt(bank.length);
//        String[] graphics = bank[exercise];
//        int graphic = new Random().nextInt(graphics.length);
//        return "@drawable/" + graphics[graphic];
//    }
=======
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
>>>>>>> 59fae08 (Change to single-screen app. Major rehaul.)
}