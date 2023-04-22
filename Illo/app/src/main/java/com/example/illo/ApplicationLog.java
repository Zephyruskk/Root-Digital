package com.example.illo;

public class ApplicationLog {
    private static ApplicationLog instance;
    private ApplicationLog(){

    }

    public static ApplicationLog getInstance(){
        if (instance == null) {
            instance = new ApplicationLog();
        }
        return instance;
    }

    public void writeLog(){

    }
}
