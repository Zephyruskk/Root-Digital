package com.example.illo;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.illo.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLISECONDS = 600000;
    private TextView countdownText;
    private Button startPauseButton;
    private Button resetButton;
    private CountDownTimer countdownTimer;
    private boolean isTimerRunning;
    private long millisecondsLeftOnTimer = START_TIME_IN_MILLISECONDS;

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        // COUNTDOWN TIMER CODE BEGINS HERE;
        // Need-To: Debug Timer; Currently throws 'null object reference' error without try-catch in place
        try {
            countdownText = findViewById(R.id.countdown_text);
            startPauseButton = findViewById(R.id.start_pause_button);
            resetButton = findViewById(R.id.reset_button);

            startPauseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isTimerRunning) {
                        pauseTimer();
                    } else {
                        startTimer();
                    }
                }
            });
            resetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    resetTimer();
                }
            });
            updateCountdownText();

        } catch (Exception e){
            System.out.println("DEBUGGING MainActivity -- Timer Exception Error");
        }

    }

    private void startTimer(){
        // When called a new countdown timer is created & begins ticking down
        countdownTimer = new CountDownTimer(millisecondsLeftOnTimer, 1000) {
            // Creates a new timer that ticks down every 1000 milliseconds (1 second)
            @Override
            public void onTick(long millisecondsUntilFinished) {
                millisecondsLeftOnTimer = millisecondsUntilFinished;
                updateCountdownText();
            }
            @Override
            public void onFinish() {
                isTimerRunning = false;
                startPauseButton.setText("Start");
                startPauseButton.setVisibility(View.INVISIBLE);
                resetButton.setVisibility(View.VISIBLE);

            }
        };

        countdownTimer.start();
        isTimerRunning = true;
        startPauseButton.setText("Pause");
        resetButton.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer(){
        countdownTimer.cancel();
        isTimerRunning = false;
        startPauseButton.setText("Start");
        resetButton.setVisibility(View.VISIBLE);
    }

    private void resetTimer(){
        // Resets countdown to original state
        millisecondsLeftOnTimer = START_TIME_IN_MILLISECONDS;
        updateCountdownText();
        resetButton.setVisibility(View.INVISIBLE);
        startPauseButton.setVisibility(View.VISIBLE);
    }

    private void updateCountdownText(){
        // Converts milliseconds & updates countdownText with new string
        int minutes = (int) (millisecondsLeftOnTimer / 1000) / 60;
        int seconds = (int) (millisecondsLeftOnTimer / 1000) % 60;

        String updatedTimeRemaining = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        countdownText.setText(updatedTimeRemaining);
    }

}