package com.example.illo;

public class User {
    public String name;
    private int uid;
    public ActivitySource[] sources;
    private ApplicationLog userLog;

    public User(String name){
        userLog = ApplicationLog.getInstance();
        this.name = name;
        // TODO: uid assignment
    }


}
