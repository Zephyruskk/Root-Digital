package com.example.illo;

public class User {
    public String name;
    private int uid;
    public ActivitySourceBank sources;
    public ExerciseBank exercises;
    private ApplicationLog userLog;

    public User(String name){
        userLog = ApplicationLog.getInstance();
        this.name = name;
        // TODO: uid assignment
    }


}
