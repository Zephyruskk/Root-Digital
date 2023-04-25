package com.example.illo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class ActivitySource {

    protected Map<String, Exercise> exerciseBank;
    public String name = null;

    public abstract Exercise nextExercise();

    protected ActivitySource(String name) {
        this.exerciseBank = new LinkedHashMap<>();
        this.name = name;
    }

    public void addExercise(Exercise exr){
        String name = exr.getName();
        int i = 1;
        while(exerciseBank.containsKey(name)){
            name = exr.getName() + "_" + i;
            i++;
        }
        exerciseBank.put(name, exr);
    }

    public void removeExercise(Exercise exr){
        exerciseBank.remove(exr.getName());
    }

    public void removeExercise(String k){
        exerciseBank.remove(k);
    }


    public void reorderExercise(String k, int pos){
        if(!exerciseBank.containsKey(k)){
            return;
        }
        Exercise temp = exerciseBank.get(k);
        exerciseBank.remove(k);
        Map<String, Exercise> newBank = new LinkedHashMap<>();
        if(pos >= exerciseBank.size()){
            exerciseBank.put(k, temp);
        }else {
            if(pos < 0) pos = 0;
            int i = 0;
            for (String key : exerciseBank.keySet()) {
                if(pos == i++) newBank.put(k,temp);
                newBank.put(key, exerciseBank.get(key));
            }
            exerciseBank = newBank;
        }
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

    public int size(){
        return exerciseBank.size();
    }

    public String toString(){
        String outString = "-------------"+name+"-------------\n";
        for(String s : exerciseBank.keySet()){
            outString += s + "\n";
        }
        return outString;
    }

}