package com.example.illo;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;


public class ActivitySourceTests {
    static ActivitySource testAS;
    static int TEST_SIZE = 20; // AT LEAST 20

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

        // repeat names
        testAS.addExercise(new Exercise("Exercise"+TEST_SIZE, null, null));
        assertTrue("Add Exercise "+TEST_SIZE+"_1", testAS.exerciseBank.containsKey("Exercise"+TEST_SIZE+"_1"));

        testAS.addExercise(new Exercise("Exercise"+TEST_SIZE, null, null));
        assertTrue("Add Exercise "+TEST_SIZE+"_2", testAS.exerciseBank.containsKey("Exercise"+TEST_SIZE+"_2"));
    }

    @Test
    public void testReorder(){
        ArrayList<Exercise> al = new ArrayList<>();
        for(String k : testAS.exerciseBank.keySet()){
            al.add(testAS.exerciseBank.get(k));
        }

        Exercise popped = al.remove(10);
        al.add(0, popped);
        testAS.reorderExercise("Exercise10", 0);
        // check order
        int al_index = 0;
        for(String k : testAS.exerciseBank.keySet()){
            assertEquals(al.get(al_index), testAS.exerciseBank.get(k));
            al_index++;
        }

        // check move from beginning of AS to middle
        popped = al.remove(0);
        al.add(10, popped);
        testAS.reorderExercise("Exercise10", 10);
        al_index = 0;
        for(String k : testAS.exerciseBank.keySet()){
            assertEquals(al.get(al_index), testAS.exerciseBank.get(k));
            al_index++;
        }

        // check move to index outside of list (append to end)
        popped = al.remove(15);
        al.add(popped);
        testAS.reorderExercise("Exercise15", TEST_SIZE+20);
        al_index = 0;
        for(String k : testAS.exerciseBank.keySet()){
            assertEquals(al.get(al_index), testAS.exerciseBank.get(k));
            al_index++;
        }

        // check move from end of list to middle
        popped = al.remove(al.size()-1);
        al.add(15, popped);
        testAS.reorderExercise("Exercise15", 15);
        al_index = 0;
        for(String k : testAS.exerciseBank.keySet()){
            assertEquals(al.get(al_index), testAS.exerciseBank.get(k));
            al_index++;
        }

        // check move to same index
        testAS.reorderExercise("Exercise15", 15);
        al_index = 0;
        for(String k : testAS.exerciseBank.keySet()){
            assertEquals(al.get(al_index), testAS.exerciseBank.get(k));
            al_index++;
        }
    }
    @Test
    public void testRemoveExercise(){
        testAS.removeExercise("Exercise0");
        assertFalse("Remove Exercise0", testAS.exerciseBank.containsKey("Exercise0"));

        int currentSize = testAS.size();
        testAS.removeExercise("Exercise0");
        assertEquals(currentSize, testAS.size()); // size shouldnt change.

        // reset
        testAS.addExercise(new Exercise("Exercise0", null, null));
        testAS.reorderExercise("Exercise0", 0);
    }


}