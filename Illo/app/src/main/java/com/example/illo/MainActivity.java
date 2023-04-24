package com.example.illo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
                            implements AdapterView.OnItemSelectedListener{
    // potentially useful constants
    private String packageName;
    private String productivityPeriod = "Productivity Period"; // for translators
    private int logoResource;
    private long productivityInterval = 1200000; // 20 min
    private long activityInterval = 300000; // 5 min

    // countdown timer text + buttons
    private TextView countdownText;
    private Button startStopButton;
    private ImageButton nextExerciseButton;

    // exercise view
    private TextView exerciseNameView;
    private ImageView exerciseGraphicView;
    private TextView instructionView;

    // state variables
    private boolean nextScreenIsExercise = true; // starts in productivity period
    private long timeLeftMS = productivityInterval;
    private boolean timerRunning = false;
    private ActivitySource selectedSource;


    // major components with scope outside of onCreate
    private CountDownTimer countDownTimer;
    private ExerciseBank exercise_bank;
    private ActivitySourceBank source_bank;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        packageName = getPackageName();
        logoResource = getResources().getIdentifier(
                "@drawable/logo",
                null,
                packageName
        );

        // initializing Exercise objects -- described line by line in "initializeExercises.txt"
        try{
            exercise_bank = ExerciseBank.getInstance(this.getBaseContext());
        } catch (Exception e){
            Log.v("EXERCISEBANK", e.toString());
        }

        // initialize default Activity Sources (Freeweight exercises, muscular stretches, hand stretches)
        source_bank = ActivitySourceBank.getInstance(exercise_bank);

        // gives references to objects in the view
        countdownText = findViewById(R.id.countdown_text);
        startStopButton = findViewById(R.id.countdown_toggle);
        nextExerciseButton = findViewById(R.id.nextExerciseButton);
        exerciseGraphicView = findViewById(R.id.exerciseGraphicView);
        instructionView = findViewById(R.id.instructionView);
        exerciseNameView = findViewById(R.id.exerciseTitleView);
        exerciseNameView.setText(productivityPeriod);
        // spinner setup
        Spinner sourceSpinner = findViewById(R.id.activitySourceSpinner);
        ArrayAdapter ad = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                source_bank.getExerciseSets().keySet().toArray()
                // Freeweight, Muscular Stretch, Hand Strecth by default
        );
        ad.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        sourceSpinner.setAdapter(ad);
        sourceSpinner.setOnItemSelectedListener(this);

        // default starting source, for now
        selectedSource = source_bank.getExerciseSets().get("Freeweight Exercises");

        ///////////////////////////////////////////////////////////////////////////////////////////

        // implements click behavior for start/stop button
        startStopButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startStop(); // see below,
            }
        });

        nextExerciseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                nextScreenIsExercise = nextScreen(nextScreenIsExercise);
            }
        });

        updateTimer(); // initial call to form timer at app launch
        startTimer();
        stopTimer();
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
                nextScreenIsExercise = nextScreen(nextScreenIsExercise);

                // setup sount alert for timer end
                Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                Ringtone ringtoneSound = RingtoneManager.getRingtone(getApplicationContext(), ringtoneUri);
                // making sure the alarm is not already running
                if (ringtoneSound != null) {
                    ringtoneSound.play();
                    android.os.SystemClock.sleep(1000); // alarm time in ms
                    ringtoneSound.stop();
                }
            }
        }.start(); // starts on creation
        startStopButton.setText("Pause");
        timerRunning = true;
    }
    public void stopTimer(){
        countDownTimer.cancel();
        startStopButton.setText("Start");
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

    // when an item is selected in the sources spinner -- ie Exercise Sets
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selected_item = adapterView.getSelectedItem().toString();

        selectedSource = source_bank.getExerciseSets().get(
                adapterView.getSelectedItem().toString()
        );
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    // get the next screen based on current state
    public boolean nextScreen(boolean exerciseOnNextScreen){
        if(exerciseOnNextScreen) {
            // find the next exercise through source's method
            Exercise nextExercise = selectedSource.nextExercise();

            // setup components
            exerciseNameView.setText(nextExercise.getName());
            int imageResource = getResources().getIdentifier(
                    "@drawable/" + nextExercise.randomGraphic(), // random multiple images for exercise
                    null,
                    packageName
            );
            exerciseGraphicView.setImageResource(imageResource);
            instructionView.setText(nextExercise.getInstructionSet());
            timeLeftMS = activityInterval;

        }else{
            exerciseNameView.setText(productivityPeriod);
            exerciseGraphicView.setImageResource(logoResource);
            instructionView.setText("");
            timeLeftMS = productivityInterval;
        }
        // reset the timer
        stopTimer();
        updateTimer();
        startTimer();
        return !exerciseOnNextScreen;
    }
}