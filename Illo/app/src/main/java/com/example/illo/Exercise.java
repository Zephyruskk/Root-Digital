package com.example.illo;

public class Exercise {
    private String[] graphicPaths; //
    private String name;
    private String[] instructionSet;

    public Exercise(String nomen, String[] instructions, String[] graphics){
        this.name = nomen;
        this.instructionSet = instructions;
        this.graphicPaths = graphics;
    }


}
