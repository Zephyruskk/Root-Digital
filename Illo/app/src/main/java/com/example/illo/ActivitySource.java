package com.example.illo;

public abstract class ActivitySource<Exercise> {

    private Exercise[] exerciseBank;
    public static String name = null;

    public abstract Exercise nextExercise();

    protected ActivitySource() {

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
    public static ActivitySource makeActivitySource(){
        if (name.equalsIgnoreCase("Workout")) {
            return new Workout(name, exercises);
        } else if (name.equalsIgnoreCase("ExerciseSet")) {
            return new ExerciseSet(name, exercises);
        } else {
            throw new IllegalArgumentException("Invalid activity source name");
        }
    }

}