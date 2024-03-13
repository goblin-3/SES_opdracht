package org.example;

import java.util.ArrayList;
import java.util.List;

public class CheckNeighboursInGrid {

    public static Iterable<Integer> getSameNeighboursIds(Iterable<Integer> grid,int width, int height, int indexToCheck){
        List<Integer> result = new ArrayList<>();

        int row = indexToCheck / width;
        int column = indexToCheck % width;


        // first we get the value from the grid so we can check for items with the same values
        int valueToCheck = -1;
        int i =0;
        for(int value : grid){
            if(i == indexToCheck){
                valueToCheck = value;
                break;
            }
            i++;
        }


        for (int r = Math.max(0, row - 1); r <= Math.min(height - 1, row + 1); r++) {
            for (int c = Math.max(0, column - 1); c <= Math.min(width - 1, column + 1); c++) {
                int currentIndex = r * width + c;
                if (currentIndex != indexToCheck) {
                    // Get the value at the current index
                    i = 0;
                    for (int value : grid) {
                        if (i == currentIndex && value == valueToCheck) {
                            result.add(currentIndex);
                            break;
                        }
                        i++;
                    }
                }
            }
        }

        

        return result;
    }
}
