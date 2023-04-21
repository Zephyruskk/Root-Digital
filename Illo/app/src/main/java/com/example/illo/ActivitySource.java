package com.example.illo;

public abstract class ActivitySource {

    protected Exercise[] exerciseBank;
    public String name = null;

    public abstract Exercise nextExercise();

    protected ActivitySource(String name) {
        this.name = name;
    }

    public void addExercise(){

    }

    public Exercise[] removeExercise(){

        return null;
    }

    public void reorderExercise(){

    }

    public void rename(String name){
        this.name = name;
    }

    // Creates new activity source if name is valid; else throws exception
    public static ActivitySource makeActivitySource(String name){
        if (name.equalsIgnoreCase("Workout")) {
            return new Workout(name);
        } else if (name.equalsIgnoreCase("ExerciseSet")) {
            return new ExerciseSet(name);
        } else {
            throw new IllegalArgumentException("Invalid activity source name");
        }
    }

}