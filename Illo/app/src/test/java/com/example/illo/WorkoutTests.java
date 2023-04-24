package com.example.illo;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;


public class WorkoutTests {
    static Workout testWO;

    @BeforeClass
    public static void instantiateWorkout(){
        testWO = new Workout("Test");
        assertEquals("Test", testWO.name);
        assertEquals(0, testWO.size());

        for(int i = 0; i < 20; i++){
            testWO.addExercise(
                    new Exercise("Exercise" + i, null, null)
            );
            // no tests for instructions or graphics as of 24 April
        }

        assertEquals(20, testWO.size());

        // reset
        testWO.setAtIndex(0);
    }

    @Test
    public void testSetAtIndex(){
        for(int i = 0; i < 10; i++){
            testWO.nextExercise();
        }
        testWO.setAtIndex(15);
        assertEquals("Exercise15", testWO.nextExercise().getName());

        // reset
        testWO.setAtIndex(0);
    }

    @Test
    public void testIncrementAtIndex(){
        for(int i = 0; i < 20; i++){
            testWO.incrementAtIndex();
        }
        // wrap?
        assertEquals("Exercise0", testWO.nextExercise().getName());

        testWO.setAtIndex(0);
    }

    // we want exercises in order, as opposed to ExerciseSets
    @Test
    public void testNextExercise(){
        for(int i = 0; i < 20; i++){
            assertEquals("Exercise"+i, testWO.nextExercise().getName());
        }
        // test wrap
        assertEquals("Exercise0", testWO.nextExercise().getName());

        testWO.setAtIndex(0);
    }

    @Test
    public void testIncrementCompletion(){
        int current = testWO.getCompletions();
        for(int i = 0; i < 40; i++){
            testWO.incrementAtIndex();
        }
        assertEquals(current + 2, testWO.getCompletions());
    }
}