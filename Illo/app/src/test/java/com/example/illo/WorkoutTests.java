package com.example.illo;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;


public class WorkoutTests {
    static Workout testWO;
    static int TEST_SIZE = 20; // make at least 20

    @BeforeClass
    public static void instantiateWorkout(){
        testWO = new Workout("Test");
        assertEquals("Test", testWO.name);
        assertEquals(0, testWO.size());

        for(int i = 0; i < TEST_SIZE; i++){
            testWO.addExercise(
                    new Exercise("Exercise" + i, null, null)
            );
            // no tests for instructions or graphics as of 24 April
        }

        assertEquals(TEST_SIZE, testWO.size());

        // reset
        testWO.setAtIndex(0);
    }

    @Test
    public void testSetAtIndex(){
        testWO.setAtIndex(0);
        assertEquals(0, testWO.getAtIndex());
        for(int i = 0; i < 10; i++){
            testWO.incrementAtIndex();
        }
        assertEquals(10, testWO.getAtIndex()); // should be true

        testWO.setAtIndex(15);
        assertEquals(15, testWO.getAtIndex());

        // reset
        testWO.setAtIndex(0);
    }

    @Test
    public void testIncrementAtIndex(){
        int s = testWO.size();

        int current = testWO.getAtIndex();
        testWO.incrementAtIndex(-1);
        assertEquals(current, testWO.getAtIndex());

        for(int i = 0; i<10; i++){
            testWO.incrementAtIndex();
        }
        assertEquals((current+10)%s, testWO.getAtIndex());

        int big_change = (int) Math.floor(TEST_SIZE*111.25);
        testWO.incrementAtIndex(big_change);
        assertEquals((current+10+big_change)%s, testWO.getAtIndex());

        testWO.setAtIndex(0);
        testWO.incrementAtIndex(s);
        assertEquals(0, testWO.getAtIndex());
    }

    // we want exercises in order, as opposed to ExerciseSets
    @Test
    public void testNextExercise(){
        int s = testWO.size();

        // loop throgh entire Workout
        for(int i = 0; i < s; i++){
            assertEquals("Exercise"+i, testWO.nextExercise().getName());
        }

        // loop many times (check for off by ones etc)
        for(int i = 0; i < s*20; i++){
            assertEquals("Exercise"+i%20, testWO.nextExercise().getName());
        }

        // check on single case
        assertEquals("Exercise0", testWO.nextExercise().getName());

        // reset
        testWO.setAtIndex(0);
    }

    @Test
    public void testIncrementCompletion(){
        int s = testWO.size();
        testWO.setAtIndex(0);
        int current = testWO.getCompletions();

        for(int i = 0; i < s/2; i++){
            testWO.incrementAtIndex();
        }
        assertEquals(current, testWO.getCompletions()); // no change

        for(int i = 0; i < s/2; i++){
            testWO.incrementAtIndex();
        }
        assertEquals(current + 1, testWO.getCompletions());

        for(int i = 0; i < 2*s; i++){
            testWO.incrementAtIndex();
        }
        assertEquals(current + 3, testWO.getCompletions());
    }
}