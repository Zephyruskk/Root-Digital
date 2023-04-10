package com.example.illo;

public class Workout extends ActivitySource {
    private int completions = 0;

    public Workout(String name) {
        super(name);
        // TODO: finish constructor
    }

    // get the next exercise in the workout
    public Exercise nextExercise() {
        // TODO: Implement next exercise logic
        return this.exerciseBank[0];
    }

    public void incrementCompletion(){
        completions++;
    }
}