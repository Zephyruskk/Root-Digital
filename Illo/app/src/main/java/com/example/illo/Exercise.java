package com.example.illo;

import java.util.Random;

public class Exercise {
    private String[] graphicPaths; //
    private String name;
    private String instructionSet;

    public Exercise(String nomen, String instructions, String[] graphics){
        this.name = nomen;
        this.instructionSet = instructions;
        this.graphicPaths = graphics;
    }

    @Override
    public String toString(){
        String outString = "----------------------------------------------------------\n";
        outString += (name + "\n");
        if(instructionSet != null) outString += (instructionSet + "\n");
        if(graphicPaths!=null) {
            for (String s : graphicPaths) {
                outString += (s + "\n");
            }
        }
        outString += "----------------------------------------------------------\n";

        return outString;
    }

    public String randomGraphic(){
        int index = new Random().nextInt(graphicPaths.length);
        return graphicPaths[index];
    }

    public String[] getGraphicPaths() {
        return graphicPaths;
    }

    public String getName() {
        return name;
    }

    public String getInstructionSet() {
        return instructionSet;
    }
}
