package com.example.illo;

import java.util.ArrayList;

public class ExerciseBank {
    private static ExerciseBank instance;
    private ArrayList<Exercise> bank;

    private ExerciseBank(){
        initialize();
    }

    private void initialize(){

    }

    public ExerciseBank getInstance(){
        if(instance == null){
            instance = new ExerciseBank();
        }
        return instance;
    }
}
