package com.example.illo;

import java.util.ArrayList;
import java.util.List;

public class Workout extends ActivitySource {
    private int completions = 0;
    private int atIndex; // state variable -- which exercise the user is currently at

    public Workout(String name) {
        super(name);
        atIndex = 0;
    }

    // get the next exercise in the workout
    public Exercise nextExercise() {
        List<Exercise> exerciseList = new ArrayList<Exercise>(exerciseBank.values());
        Exercise nextExercise = exerciseList.get(atIndex);
        incrementAtIndex();
        return nextExercise;
    }

    public void incrementAtIndex(int i){
        if(i < 1){
            return;
        }
        atIndex += i;
        if(atIndex >= exerciseBank.size()){
            atIndex = atIndex%exerciseBank.size();
            incrementCompletion();
        }
    }

    public void incrementAtIndex(){
        incrementAtIndex(1);
    }

    public void setAtIndex(int i){
        atIndex = i;
    }

    private void incrementCompletion(){
        completions++;
    }

    public int getCompletions(){
        return completions;
    }

    public int getAtIndex(){
        return atIndex;
    }
}