package com.example.illo;

public class Workout extends ActivitySource {
    private int completions = 0;

    // get the next exercise in the workout
    public Exercise nextExercise() {
        return ActivitySource.exerciseBank;
    }

    public void incrementCompletion(){
        completions++;
    }
}