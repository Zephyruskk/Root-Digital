package com.example.illo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ExerciseSet extends ActivitySource{

    private Random r;
    protected ExerciseSet(String name) {
        super(name);
        r = new Random();
    }

    public Exercise nextExercise(){
        List<Exercise> exerciseList = new ArrayList<>(exerciseBank.values());
        int randIndex = r.nextInt(exerciseList.size());
        return exerciseList.get(randIndex);
    }




    @Override
    public String toString(){
        String outString = "";
        for(Exercise exr : exerciseBank.values()){
            outString += exr.toString() + "\n";
        }

        return outString;
    }
}
