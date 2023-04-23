package com.example.illo;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivitySourceBank {
    private static ActivitySourceBank instance;
    private Map<String, ExerciseSet> ES_bank;
    private Map<String, Workout> WO_bank;

    ExerciseSet stretchSet;
    private String[] stretches = new String[]{
            "Ball Squeeze Stretch",
            "Butterfly Pose",
            "Camel Pose",
            "Chair Spinal Twist",
            "Child Pose",
            "Cobra Pose",
            "Ear To Shoulder Stretch",
            "Finger Clench Stretch",
            "Finger Spread Stretch",
            "Kneeling Hip Flexor",
            "Neck Stretch",
            "Overhead Tricep Stretch",
            "Reverse Shoulder Stretch",
            "Seated Toe Touch",
            "Side Bend",
            "Spinal Twist",
            "Wall Calf Stretch",
            "Horizontal Wrist Flick",
            "Rolling Ball Wrist Stretch",
            "Vertical Wrist Flexor",
    };

    ExerciseSet freeweightSet;
    private String[] freeweights = new String[]{
            "Body Crunches",
            "Break Dancer",
            "Mountain Climbers",
            "Oblique Crunch",
            "Push Ups",
            "Seated Bench Leg Pull In",
            "Squat",
            "Triceps Dip",
    };

    private ActivitySourceBank(ExerciseBank ebank){
        ES_bank = new HashMap<>(); WO_bank = new HashMap<>();
        stretchSet = new ExerciseSet("Stretches");
        freeweightSet = new ExerciseSet("Free Weight Exercises");
        initialize(ebank);
    }

    private void initialize(ExerciseBank ebank){
        for(String str : stretches){
            try{
                stretchSet.addExercise(ebank.getExerciseByName(str));
            } catch (Exception e){
                Log.v("ACTIVITY_SOURCE_BANK", e.toString());
            }
        }
        for(String str : freeweights){
            try{
                freeweightSet.addExercise(ebank.getExerciseByName(str));
            } catch (Exception e){
                Log.v("ACTIVITY_SOURCE_BANK", e.toString());
            }
        }
        ES_bank.put("Stretches", stretchSet);
        ES_bank.put("Freeweight Exercises", freeweightSet);

    }

    public static ActivitySourceBank getInstance(ExerciseBank ebank){
        if(instance == null){
            instance = new ActivitySourceBank(ebank);
        }
        return instance;
    }

    public Map<String, ExerciseSet> getExerciseSets() {
        return ES_bank;
    }
}
