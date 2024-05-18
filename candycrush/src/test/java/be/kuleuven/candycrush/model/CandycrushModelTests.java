package be.kuleuven.candycrush.model;


import javafx.geometry.Pos;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;


public class CandycrushModelTests {


//private ArrayList<Candy> preMadeSpeelbord;



 /*   @Before  // Before runs before every test beforeAll once
    public void setUp(){
    boardSize = new CandycrushModel.BoardSize(4, 4);
    board = new Board<>(boardSize);

    ArrayList<Candy> preMadeSpeelbord = new ArrayList<>(Arrays.asList(
            new Candy.NormalCandy(1), new Candy.NormalCandy(2), new Candy.NormalCandy(3), new Candy.NormalCandy(0),
            new Candy.NormalCandy(2), new Candy.NormalCandy(2), new Candy.NormalCandy(3), new Candy.NormalCandy(0),
            new Candy.NormalCandy(1), new Candy.NormalCandy(2), new Candy.NormalCandy(3), new Candy.NormalCandy(0),
            new Candy.NormalCandy(2), new Candy.NormalCandy(2), new Candy.NormalCandy(2), new Candy.NormalCandy(2)
    ));//prmadespeelbord generated by chatgpt

    int index = 0;
        for (int r = 0; r <= boardSize.rows(); r++) {
            for (int c = 0; c <= boardSize.columns(); c++) {
            board.setCellAt(new CandycrushModel.Position(r, c,boardSize), preMadeSpeelbord.get(index));
            index++;
        }
    }
}*/


        @Test
    public void IfNoSurroundingCandyAreDifferentOnlyChangeOneCandy(){

            CandycrushModel model = new CandycrushModel("Test");

            CandycrushModel.BoardSize boardSize = new CandycrushModel.BoardSize(4, 4);
            Board<Candy> board = new Board<>(boardSize);


            model.setBoardSize(boardSize);

            ArrayList<Candy> preMadeSpeelbord = new ArrayList<>(Arrays.asList(
                    new Candy.NormalCandy(1), new Candy.NormalCandy(2), new Candy.NormalCandy(3), new Candy.NormalCandy(0),
                    new Candy.NormalCandy(2), new Candy.NormalCandy(2), new Candy.NormalCandy(3), new Candy.NormalCandy(0),
                    new Candy.NormalCandy(1), new Candy.NormalCandy(2), new Candy.NormalCandy(3), new Candy.NormalCandy(0),
                    new Candy.NormalCandy(2), new Candy.NormalCandy(2), new Candy.NormalCandy(2), new Candy.NormalCandy(2)
            ));//prmadespeelbord generated by chatgpt

            int index = 0;
            for (int r = 0; r <= boardSize.rows()-1; r++) {
                for (int c = 0; c <= boardSize.columns()-1; c++) {
                    board.setCellAt(new CandycrushModel.Position(r, c,boardSize), preMadeSpeelbord.get(index));
                    index++;
                }
            }

            model.setSpeelbord(board);
            CandycrushModel.Position position = new CandycrushModel.Position(0,0,boardSize);
            CandycrushModel.Position position1 = CandycrushModel.Position.fromIndex(1,boardSize);
            CandycrushModel.Position position4 = CandycrushModel.Position.fromIndex(4,boardSize);
            CandycrushModel.Position position5 = CandycrushModel.Position.fromIndex(5,boardSize);

        model.candyWithIndexSelected(position);
            assertNotEquals(new Candy.NormalCandy(1),model.getSpeelbord().getCellAt(position));
            assertEquals(new Candy.NormalCandy(2), model.getSpeelbord().getCellAt(position1));
            assertEquals(new Candy.NormalCandy(2), model.getSpeelbord().getCellAt(position4));
            assertEquals(new Candy.NormalCandy(2), model.getSpeelbord().getCellAt(position5));
    }



        @Test
        public void testSameSurroundingNumberGetsChangedInMiddle() {

            CandycrushModel model = new CandycrushModel("Test");

            CandycrushModel.BoardSize boardSize= new CandycrushModel.BoardSize(4,4);

            model.setBoardSize(boardSize);

            Board<Candy> board = new Board<>(boardSize);

            ArrayList<Candy> preMadeSpeelbord = new ArrayList<>(Arrays.asList(
                    new Candy.NormalCandy(1), new Candy.NormalCandy(2), new Candy.NormalCandy(3), new Candy.NormalCandy(0),
                    new Candy.NormalCandy(2), new Candy.NormalCandy(2), new Candy.NormalCandy(3), new Candy.NormalCandy(0),
                    new Candy.NormalCandy(1), new Candy.NormalCandy(2), new Candy.NormalCandy(3), new Candy.NormalCandy(0),
                    new Candy.NormalCandy(2), new Candy.NormalCandy(2), new Candy.NormalCandy(2), new Candy.NormalCandy(2)
            ));


            int index = 0;
            for (int r = 0; r <= boardSize.rows()-1; r++) {
                for (int c = 0; c <= boardSize.columns()-1; c++) {
                    board.setCellAt(new CandycrushModel.Position(r, c,boardSize), preMadeSpeelbord.get(index));
                    index++;
                }
            }



            model.setSpeelbord(board);

            CandycrushModel.Position position = CandycrushModel.Position.fromIndex(5,boardSize);




            CandycrushModel.Position position5 = CandycrushModel.Position.fromIndex(5, boardSize);
            CandycrushModel.Position position0 = CandycrushModel.Position.fromIndex(0,boardSize);
            CandycrushModel.Position position1 = CandycrushModel.Position.fromIndex(1, boardSize);
            CandycrushModel.Position position2 = CandycrushModel.Position.fromIndex(2, boardSize);
            CandycrushModel.Position position4 = CandycrushModel.Position.fromIndex(4, boardSize);
            CandycrushModel.Position position6 = CandycrushModel.Position.fromIndex(6, boardSize);
            CandycrushModel.Position position8 = CandycrushModel.Position.fromIndex(8, boardSize);
            CandycrushModel.Position position9 = CandycrushModel.Position.fromIndex(9, boardSize);
            CandycrushModel.Position position10 = CandycrushModel.Position.fromIndex(10, boardSize);


            Candy originalValueAt5 =  model.getSpeelbord().getCellAt(position5);
            Candy originalValueAt0 = model.getSpeelbord().getCellAt(position0);
            Candy originalValueAt1 =  model.getSpeelbord().getCellAt(position1);
            Candy originalValueAt2 =  model.getSpeelbord().getCellAt(position2);
            Candy originalValueAt4 =  model.getSpeelbord().getCellAt(position4);
            Candy originalValueAt6 =  model.getSpeelbord().getCellAt(position6);
            Candy originalValueAt8 =  model.getSpeelbord().getCellAt(position8);
            Candy originalValueAt9 =  model.getSpeelbord().getCellAt(position9);
            Candy originalValueAt10 =  model.getSpeelbord().getCellAt(position10);

            model.candyWithIndexSelected(position);


            Candy newValueAt5 = model.getSpeelbord().getCellAt(position5);
            Candy newValueAt0 = model.getSpeelbord().getCellAt(position0);
            Candy newValueAt1 = model.getSpeelbord().getCellAt(position1);
            Candy newValueAt2 = model.getSpeelbord().getCellAt(position2);
            Candy newValueAt4 = model.getSpeelbord().getCellAt(position4);
            Candy newValueAt6 = model.getSpeelbord().getCellAt(position6);
            Candy newValueAt8 = model.getSpeelbord().getCellAt(position8);
            Candy newValueAt9 = model.getSpeelbord().getCellAt(position9);
            Candy newValueAt10 = model.getSpeelbord().getCellAt(position10);

            assertNotEquals("The value at index 5 should be changed", originalValueAt5, newValueAt5);
            assertEquals("The value at 0 should not change",originalValueAt0,newValueAt0);
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
            CandycrushModel model = new CandycrushModel("Test");

            CandycrushModel.BoardSize boardSize= new CandycrushModel.BoardSize(4,4);
            model.setBoardSize(boardSize);
            Board<Candy> board = new Board<>(boardSize);


            ArrayList<Candy> preMadeSpeelbord = new ArrayList<>(Arrays.asList(
                    new Candy.NormalCandy(1), new Candy.NormalCandy(2), new Candy.NormalCandy(3), new Candy.NormalCandy(0),
                    new Candy.NormalCandy(2), new Candy.NormalCandy(2), new Candy.NormalCandy(3), new Candy.NormalCandy(0),
                    new Candy.NormalCandy(1), new Candy.NormalCandy(2), new Candy.NormalCandy(3), new Candy.NormalCandy(0),
                    new Candy.NormalCandy(2), new Candy.NormalCandy(2), new Candy.NormalCandy(2), new Candy.NormalCandy(2)
            ));

            int index = 0;
            for (int r = 0; r <= boardSize.rows()-1; r++) {
                for (int c = 0; c <= boardSize.columns()-1; c++) {
                    board.setCellAt(new CandycrushModel.Position(r, c,boardSize), preMadeSpeelbord.get(index));
                    index++;
                }
            }




            model.setSpeelbord(board);


            CandycrushModel.Position position = new CandycrushModel.Position(0,2,boardSize);

            CandycrushModel.Position position6 = CandycrushModel.Position.fromIndex(6, boardSize);
            CandycrushModel.Position position2 = CandycrushModel.Position.fromIndex(2, boardSize);


            Candy originalValueAt6 = model.getSpeelbord().getCellAt(position6);
            Candy originalValueAt2 = model.getSpeelbord().getCellAt(position2);
            model.candyWithIndexSelected(position);
            Candy newValueAt6 = model.getSpeelbord().getCellAt(position6);
            Candy newValueAt2 = model.getSpeelbord().getCellAt(position2);
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
            CandycrushModel model = new CandycrushModel("Test");
            CandycrushModel.BoardSize boardSize= new CandycrushModel.BoardSize(4,4);
            model.setBoardSize(boardSize);


            Board<Candy> board = new Board<>(boardSize);
            ArrayList<Candy> preMadeSpeelbord = new ArrayList<>(Arrays.asList(
                    new Candy.NormalCandy(1), new Candy.NormalCandy(2), new Candy.NormalCandy(3), new Candy.NormalCandy(0),
                    new Candy.NormalCandy(2), new Candy.NormalCandy(2), new Candy.NormalCandy(3), new Candy.NormalCandy(0),
                    new Candy.NormalCandy(1), new Candy.NormalCandy(2), new Candy.NormalCandy(3), new Candy.NormalCandy(0),
                    new Candy.NormalCandy(2), new Candy.NormalCandy(2), new Candy.NormalCandy(2), new Candy.NormalCandy(2)
            ));

            int index = 0;
            for (int r = 0; r <= boardSize.rows()-1; r++) {
                for (int c = 0; c <= boardSize.columns()-1; c++) {
                    board.setCellAt(new CandycrushModel.Position(r, c,boardSize), preMadeSpeelbord.get(index));
                    index++;
                }
            }








            model.setSpeelbord(board);
            model.setScore(2);


            CandycrushModel.Position position = new CandycrushModel.Position(0,0,boardSize);

            model.candyWithIndexSelected(position);
            assertEquals(3,model.getScore());
        }


        @Test
        public void testMultipleCandyAreChangedScoreGetsKeptCorrectly() {
            CandycrushModel model = new CandycrushModel("Test");

            CandycrushModel.BoardSize boardSize = new CandycrushModel.BoardSize(4, 4);
            Board<Candy> board = new Board<>(boardSize);


            model.setBoardSize(boardSize);




            ArrayList<Candy> preMadeSpeelbord = new ArrayList<>(Arrays.asList(
                    new Candy.NormalCandy(1), new Candy.NormalCandy(2), new Candy.NormalCandy(3), new Candy.NormalCandy(0),
                    new Candy.NormalCandy(2), new Candy.NormalCandy(2), new Candy.NormalCandy(3), new Candy.NormalCandy(0),
                    new Candy.NormalCandy(1), new Candy.NormalCandy(2), new Candy.NormalCandy(3), new Candy.NormalCandy(0),
                    new Candy.NormalCandy(2), new Candy.NormalCandy(2), new Candy.NormalCandy(2), new Candy.NormalCandy(2)
            ));

            int index = 0;
            for (int r = 0; r <= boardSize.rows()-1; r++) {
                for (int c = 0; c <= boardSize.columns()-1; c++) {
                    board.setCellAt(new CandycrushModel.Position(r, c,boardSize), preMadeSpeelbord.get(index));
                    index++;
                }
            }



            model.setSpeelbord(board);
            model.setScore(0);


            CandycrushModel.Position position = new CandycrushModel.Position(0,2,boardSize);

            model.candyWithIndexSelected(position);
            assertEquals(2,model.getScore());

        }

        @Test
        public void testNegativeIndexIsCheckedNothingHappens() {

            CandycrushModel.BoardSize boardSize= new CandycrushModel.BoardSize(4,4);

            assertThrows(IllegalArgumentException.class, () -> {new CandycrushModel.Position(-1, -1, boardSize);});

        }
        @Test
        public void testOutOfBoundsIndexIsCheckedNothingHappens() {


            CandycrushModel.BoardSize boardSize = new CandycrushModel.BoardSize(4,4);

            assertThrows(IllegalArgumentException.class, () -> {new CandycrushModel.Position(9, 99, boardSize);});



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

        @Test
        public  void testPositionToIndexCorrectlyAssigned(){
            CandycrushModel.BoardSize boardSize = new CandycrushModel.BoardSize(2,4);

            CandycrushModel.Position position1 = new CandycrushModel.Position(0,0,boardSize);


            assertEquals(position1.toIndex(),0);


            CandycrushModel.Position position2 = new CandycrushModel.Position(0,3,boardSize);

            assertEquals(position2.toIndex(),3);

            CandycrushModel.Position position3 = new CandycrushModel.Position(1,2,boardSize);

            assertEquals(position3.toIndex(),6);
        }

        @Test
        public  void  testPositionFromIndex(){
            CandycrushModel.BoardSize boardSize = new CandycrushModel.BoardSize(2,4);

            CandycrushModel.Position position1 = CandycrushModel.Position.fromIndex(0, boardSize);
            assertEquals(0, position1.row());
            assertEquals(0, position1.column());

            CandycrushModel.Position position3 = CandycrushModel.Position.fromIndex(6, boardSize);
            assertEquals(1, position3.row());
            assertEquals(2, position3.column());
        }
        @Test
        public void testNeighboursPositions(){
            CandycrushModel.BoardSize boardSize = new CandycrushModel.BoardSize(3,3);

            CandycrushModel.Position position = new CandycrushModel.Position(1,1,boardSize);
            Iterable<CandycrushModel.Position> neighbours = position.neighbourPositions();

            // lists of expected position generated by chatgpt
            int[] expectedRows = {0, 0, 0, 1, 1, 2, 2, 2};
            int[] expectedColumns = {0, 1, 2, 0, 2, 0, 1, 2};

            Iterator<CandycrushModel.Position> iterator = neighbours.iterator();
            int count = 0;
            while (iterator.hasNext()) {
                CandycrushModel.Position neighbour = iterator.next();
                assertEquals(expectedRows[count], neighbour.row());
                assertEquals(expectedColumns[count], neighbour.column());
                count++;
            }
            assertEquals(count ,8);
        }
        @Test
        public void testNeighboursPositionsNearEdge(){
            CandycrushModel.BoardSize boardSize = new CandycrushModel.BoardSize(3, 3);

            CandycrushModel.Position position = new CandycrushModel.Position(0,0,boardSize);

            Iterable<CandycrushModel.Position> neighbours = position.neighbourPositions();

            // lists of expected position generated by chatgpt
            int[] expectedRows = {0, 1, 1};
            int[] expectedColumns = {1, 0, 1};

            Iterator<CandycrushModel.Position> iterator = neighbours.iterator();
            int count = 0;

            while(iterator.hasNext()){
                CandycrushModel.Position neighbour = iterator.next();
                assertEquals(expectedRows[count],neighbour.row());
                assertEquals(expectedColumns[count],neighbour.column());
                count++;
            }
            assertEquals(count,3);
        }

        @Test
        public void testIsLastColumn(){
            CandycrushModel.BoardSize boardSize = new CandycrushModel.BoardSize(2,4);
            CandycrushModel.Position position = new CandycrushModel.Position(0,3,boardSize);
            CandycrushModel.Position position1 = new CandycrushModel.Position(0,2,boardSize);

            boolean isColumn = position.isLastColumn();
            boolean isColumn1 = position1.isLastColumn();
            assertEquals(isColumn,true);
            assertEquals(isColumn1,false);
        }

        @Test
        public void testReturnsAllPositionsInBoard(){
            CandycrushModel.BoardSize boardSize = new CandycrushModel.BoardSize(2,3);

            Iterable<CandycrushModel.Position> positions = boardSize.positions();

            int expectedPositionsAmount = boardSize.columns()*boardSize.rows();
            int actualPositionsAmount = 0;

            for (CandycrushModel.Position ignored : positions){
                actualPositionsAmount++;
            }
            assertEquals(expectedPositionsAmount,actualPositionsAmount);

            int expectedRow =0;
            int expectedColumn=0;

            for (CandycrushModel.Position position : positions){
                assertEquals(expectedRow, position.row());
                assertEquals(expectedColumn,position.column());


                expectedColumn++;
                if (expectedColumn >= boardSize.columns()){
                    if(expectedRow>= boardSize.rows()) break;else {


                        expectedColumn = 0;
                        expectedRow++;
                    }
                }

            }

        }

    @Test
    void testGetPositionsOfElement_BasicFunctionality() {
        CandycrushModel.BoardSize boardSize = new CandycrushModel.BoardSize(4, 4);
        Board<Candy> board = new Board<>(boardSize);

        Candy normalCandy = new Candy.NormalCandy(1);
        Candy specialCandy = new Candy.ChocolatCandy(1);

        board.setCellAt(new CandycrushModel.Position(0, 0, boardSize), normalCandy);
        board.setCellAt(new CandycrushModel.Position(1, 1, boardSize), normalCandy);
        board.setCellAt(new CandycrushModel.Position(2, 2, boardSize), specialCandy);


        List<CandycrushModel.Position> normalCandyPositions = board.getPositionsOfElement(normalCandy);
        assertEquals(2, normalCandyPositions.size());
        assertTrue(normalCandyPositions.contains(new CandycrushModel.Position(0, 0, boardSize)));
        assertTrue(normalCandyPositions.contains(new CandycrushModel.Position(1, 1, boardSize)));


        List<CandycrushModel.Position> specialCandyPositions = board.getPositionsOfElement(specialCandy);
        assertEquals(1, specialCandyPositions.size());
        assertTrue(specialCandyPositions.contains(new CandycrushModel.Position(2, 2, boardSize)));
    }
}
