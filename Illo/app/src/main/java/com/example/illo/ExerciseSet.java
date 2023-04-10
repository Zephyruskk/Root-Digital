package com.example.illo;

public class ExerciseSet extends ActivitySource{

    protected ExerciseSet(String name) {
        super(name);
        // TODO: Finish constructor
    }

    public Exercise nextExercise(){
        // TODO: implement random selection
        return (Exercise) this.exerciseBank[0];
    }
}
