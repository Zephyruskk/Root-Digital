package com.example.illo;

import java.util.ArrayList;
import java.util.List;

public class Workout extends ActivitySource {
    private int completions = 0;
    private int atIndex;

    public Workout(String name) {
        super(name);
        atIndex = 0;
    }

    // get the next exercise in the workout
    public Exercise nextExercise() {
        List<Exercise> exerciseList = new ArrayList<Exercise>(exerciseBank.values());
        Exercise returnExercise = exerciseList.get(atIndex);
        incrementAtIndex();
        return returnExercise;
    }

    public void incrementAtIndex(){
        atIndex++;
        if(atIndex >= exerciseBank.size()){
            atIndex = 0;
            incrementCompletion();
        }
    }

    private void incrementCompletion(){
        completions++;
    }
}