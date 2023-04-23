package com.example.illo;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;

public class ExerciseBank {
    private static ExerciseBank instance;
    private Map<String, Exercise> bank;
    private Exercise blankExercise;

    private ExerciseBank(Context context){
        bank = new Hashtable<>();
        blankExercise = new Exercise("EMPTY", "EXERCISE", null);
        try {
            initialize(context);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void initialize(Context context) throws IOException {
        AssetManager am = context.getAssets();
        InputStream assets = am.open("initializeExercises.txt");
        byte[] array = new byte[20000];
        assets.read(array);
        String data = new String(array);
        data = data.replace("\0", "");


        String[] exerciseTemps = data.split("\n", 0);
        for (String s : exerciseTemps){
            String[] exerciseAttributes = s.split(";");
            String nomen = exerciseAttributes[0];
            String instructions = exerciseAttributes[1];
            String[] graphicPaths = exerciseAttributes[2].split("/");
            bank.put(nomen, new Exercise(nomen, instructions, graphicPaths));
        }

    }


    public static ExerciseBank getInstance(Context context){
        if(instance == null){
            instance = new ExerciseBank(context);
        }
        return instance;
    }

    public Exercise getExerciseByName(String name){
        Exercise returnValue = blankExercise;
        try{
            returnValue = bank.get(name);
        } catch (Exception e){
            Log.v("EXERCISE_BANK", e.toString());
        }
        return returnValue;
    }

    @Override
    public String toString(){
        String outString = "";

        for (Exercise s : bank.values()){
            outString += s.toString() + "\n";
        }

        return outString;
    }
}
