package be.kuleuven.candycrush.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


    public class CandycrushModelTests {


        @Test
    public void IfNoSurroundingNumbersAreDifferentOnlyChangeOneNumber(){
            ArrayList<Integer> preMadeSpeelbord = new ArrayList<>(Arrays.asList(
                    1, 2, 3, 4,
                    2, 2, 3, 4,
                    1, 2, 3, 4,
                    2, 2, 2, 2
            ));

            CandycrushModel model = new CandycrushModel("Test");
            model.setWidth(4);
            model.setHeight(4);
            model.setSpeelbord(preMadeSpeelbord);


        model.candyWithIndexSelected(0);
        assertEquals(2, (int) model.getSpeelbord().get(1));
        assertEquals(2, (int) model.getSpeelbord().get(4));
        assertEquals(2, (int) model.getSpeelbord().get(5));
    }



        @Test
        public void testSameSurroundingNumberGetsChangedInMiddle() {
            ArrayList<Integer> preMadeSpeelbord = new ArrayList<>(Arrays.asList(
                    1, 2, 3, 4,
                    2, 2, 3, 4,
                    1, 2, 3, 4,
                    2, 2, 2, 2
            ));

            CandycrushModel model = new CandycrushModel("Test");
            model.setWidth(4);
            model.setHeight(4);
            model.setSpeelbord(preMadeSpeelbord);

            int originalValueAt5 = model.getSpeelbord().get(5);
            int originalValueAt1 = model.getSpeelbord().get(1);
            int originalValueAt2 = model.getSpeelbord().get(2);
            int originalValueAt4 = model.getSpeelbord().get(4);
            int originalValueAt6 = model.getSpeelbord().get(6);
            int originalValueAt8 = model.getSpeelbord().get(8);
            int originalValueAt9 = model.getSpeelbord().get(9);
            int originalValueAt10 = model.getSpeelbord().get(10);

            model.candyWithIndexSelected(5);


            int newValueAt5 = model.getSpeelbord().get(5);
            int newValueAt1 = model.getSpeelbord().get(1);
            int newValueAt2 = model.getSpeelbord().get(2);
            int newValueAt4 = model.getSpeelbord().get(4);
            int newValueAt6 = model.getSpeelbord().get(6);
            int newValueAt8 = model.getSpeelbord().get(8);
            int newValueAt9 = model.getSpeelbord().get(9);
            int newValueAt10 = model.getSpeelbord().get(10);

            assertNotEquals("The value at index 5 should be changed", originalValueAt5, newValueAt5);
            assertNotEquals("The value at index 1 should be changed", originalValueAt1, newValueAt1);
            assertEquals("The value at index 2 should not be changed", originalValueAt2, newValueAt2);
            assertNotEquals("The value at index 4 should be changed", originalValueAt4, newValueAt4);
            assertEquals("The value at index 6 should not be changed", originalValueAt6, newValueAt6);
            assertEquals("The value at index 8 should not be changed", originalValueAt8, newValueAt8);
            assertNotEquals("The value at index 9 should be changed", originalValueAt9, newValueAt9);
            assertEquals("The value at index 10 should not be changed", originalValueAt10, newValueAt10);
        }

        @Test
        public void testSameSurroundingNumberGetsChangedAtEdge() {
            ArrayList<Integer> preMadeSpeelbord = new ArrayList<>(Arrays.asList(
                    1, 2, 3, 4,
                    2, 2, 3, 4,
                    1, 2, 3, 4,
                    2, 2, 2, 2
            ));

            CandycrushModel model = new CandycrushModel("Test");
            model.setWidth(4);
            model.setHeight(4);
            model.setSpeelbord(preMadeSpeelbord);


            int originalValueAt6 = model.getSpeelbord().get(6);
            int originalValueAt2 = model.getSpeelbord().get(2);
            model.candyWithIndexSelected(2);
            int newValueAt6 = model.getSpeelbord().get(6);
            int newValueAt2 = model.getSpeelbord().get(2);
            assertNotEquals(originalValueAt6,newValueAt6);
            assertNotEquals(originalValueAt2,newValueAt2);
        }

        @Test
        public void scoreGetsSetCorectly(){
            CandycrushModel model = new CandycrushModel("Test");
            model.setScore(5);
            assertEquals(5,model.getScore());
             }

        @Test
        public void whenOneCandyIsSelectedScoreUpdatesCorrectly(){
            ArrayList<Integer> preMadeSpeelbord = new ArrayList<>(Arrays.asList(
                    1, 2, 3, 4,
                    2, 2, 3, 4,
                    1, 2, 3, 4,
                    2, 2, 2, 2
            ));

            CandycrushModel model = new CandycrushModel("Test");
            model.setWidth(4);
            model.setHeight(4);
            model.setSpeelbord(preMadeSpeelbord);
            model.setScore(2);


            model.candyWithIndexSelected(0);
            assertEquals(3,model.getScore());
        }


        @Test
        public void testMultipleCandyAreChangedScoreGetsKeptCorrectly() {
            ArrayList<Integer> preMadeSpeelbord = new ArrayList<>(Arrays.asList(
                    1, 2, 3, 4,
                    2, 2, 3, 4,
                    1, 2, 3, 4,
                    2, 2, 2, 2
            ));

            CandycrushModel model = new CandycrushModel("Test");
            model.setWidth(4);
            model.setHeight(4);
            model.setSpeelbord(preMadeSpeelbord);
            model.setScore(0);

            model.candyWithIndexSelected(2);
            assertEquals(2,model.getScore());

        }

        @Test
        public void testNegativeIndexIsCheckedNothingHappens() {
            ArrayList<Integer> preMadeSpeelbord = new ArrayList<>(Arrays.asList(
                    1, 2, 3, 4,
                    2, 2, 3, 4,
                    1, 2, 3, 4,
                    2, 2, 2, 2
            ));

            CandycrushModel model = new CandycrushModel("Test");
            model.setWidth(4);
            model.setHeight(4);
            model.setSpeelbord(preMadeSpeelbord);


            model.candyWithIndexSelected(-1);
            assertEquals(preMadeSpeelbord,model.getSpeelbord());

        }
        @Test
        public void testOutOfBoundsIndexIsCheckedNothingHappens() {
            ArrayList<Integer> preMadeSpeelbord = new ArrayList<>(Arrays.asList(
                    1, 2, 3, 4,
                    2, 2, 3, 4,
                    1, 2, 3, 4,
                    2, 2, 2, 2
            ));

            CandycrushModel model = new CandycrushModel("Test");
            model.setWidth(4);
            model.setHeight(4);
            model.setSpeelbord(preMadeSpeelbord);


            model.candyWithIndexSelected(16);
            assertEquals(preMadeSpeelbord,model.getSpeelbord());

        }
        @Test
        public void testScoreIsZeroAtStart(){
            CandycrushModel model = new CandycrushModel("Test");
            assertEquals(0,model.getScore());
        }
        @Test
        public void testNullGetsIgnoredInName(){
            CandycrushModel model = new CandycrushModel(null);
            assertNotEquals(true,model.isInitialized());
        }

        @Test
        public void testNameIsSavedCorrectly(){
            CandycrushModel model = new CandycrushModel("Test");
            assertEquals("Test",model.getSpeler());
        }

}
