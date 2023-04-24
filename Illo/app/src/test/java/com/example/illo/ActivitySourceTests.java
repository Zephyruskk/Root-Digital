package com.example.illo;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class ActivitySourceTests {
    static ActivitySource testAS;
    static int TEST_SIZE = 20; // make at least 20

    // setup TestAS and ensure constructor is correct
    @BeforeClass
    public static void instantiateActivitySource(){
        testAS = new ActivitySource("Test") {
            @Override
            public Exercise nextExercise() {
                return null; // see Workout and ExerciseSet tests for this method
            }
        };

        assertEquals("Test", testAS.name);
        assertEquals(0, testAS.size());

        for(int i = 0; i < TEST_SIZE; i++){
            testAS.addExercise(
                    new Exercise("Exercise" + i, null, null)
            );
            // no tests for instructions or graphics as of 24 April
        }

        assertEquals(TEST_SIZE, testAS.size());
    }

    @Test
    public void testAddExercise(){
        testAS.addExercise(new Exercise("Exercise"+TEST_SIZE, null, null));
        assertTrue("Add Exercise "+TEST_SIZE, testAS.exerciseBank.containsKey("Exercise"+TEST_SIZE));
    }

    @Test
    public void testRemoveExercise(){
        testAS.removeExercise("Exercise0");
        assertFalse("Remove Exercise0", testAS.exerciseBank.containsKey("Exercise0"));
    }
    @Test
    public void testReorder(){
        testAS.reorderExercise("Exercise10", 0);
        // necessary procedure for getting first element of LinkedHashMap
        for(String s : testAS.exerciseBank.keySet()){
            assertEquals("Exercise10", s);
            break;
        }
    }

}