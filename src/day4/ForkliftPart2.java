
package day4;

import util.*;

import java.io.IOException;
import java.util.List;

// Much better solution than what I did in part1, imo

public class ForkliftPart2 {
    private final String DAY4INPUT = "src/day4/data.txt";
    private final String DAY4TESTINPUT = "src/day4/datatest.txt";
    private char[][] grid;
    private int xMaxIndex;
    private int yMaxIndex;

    public ForkliftPart2() {
        List<String> lines = null;
        try {
            lines = FileUtil.readAllLines(DAY4INPUT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int nrOfLines = lines.size();
        int nrOfChars = lines.get(0).length();
        grid = new char[nrOfLines][nrOfChars];

        yMaxIndex = nrOfLines - 1;
        xMaxIndex = nrOfChars - 1;

        // Init char grid
        for (int i = 0; i < nrOfLines; i++) {
            for (int j = 0; j < nrOfChars; j++) {
                grid[i][j] = lines.get(i).charAt(j);
            }
        }
    }

    public int solve() {
        int total = 0;
        int temp = 0;

        printGrid();

        do {
            temp = findOneSolution();
            total += temp;
        } while (temp != 0);

        printGrid();
        return total;
    }

    private int findOneSolution() {
        int count = 0;

        // Check corner positions. A corner can't have more than 3 adjacent positions.
        // => If roll, then available.
        count += checkCornersPositions();

        // Paper rolls at grid edges need to be handled differently
        count += checkGridEdgesAdjacent();

        // Count every character with 8 adjacent positions
        count += checkInnerGridAdjacent();

        // Print and clear the grid for next iteration
        //printGrid();
        clearProcessed();

        return count;
    }

    private int checkInnerGridAdjacent() {
        int innerCount = 0;
        for (int i = 1; i < yMaxIndex; i++) {
            for(int j = 1; j < xMaxIndex; j++){
                if(grid[i][j] == '@' && isPaperRollAvailable(i, j)){
                    grid[i][j] = 'X';
                    innerCount++;
                }
            }
        }
        return innerCount;
    }

    private int checkGridEdgesAdjacent() {
        int availableRollCount = 0;

        // Check top and bot edges
        for(int i = 1; i < xMaxIndex; i++){
            if(grid[0][i] == '@' && isPaperRollAvailable(0, i)){
                availableRollCount++;
                grid[0][i] = 'X';
            }
            if(grid[yMaxIndex][i] == '@' && isPaperRollAvailable(yMaxIndex, i)){
                availableRollCount++;
                grid[yMaxIndex][i] = 'X';
            }
        }
        // Check left and right edges
        for(int i = 1; i < yMaxIndex; i++){
            if(grid[i][0] == '@' && isPaperRollAvailable(i, 0)){
                availableRollCount++;
                grid[i][0] = 'X';
            }
            if(grid[i][xMaxIndex] == '@' && isPaperRollAvailable(i, xMaxIndex)){
                availableRollCount++;
                grid[i][xMaxIndex] = 'X';
            }
        }
        return availableRollCount;
    }

    private int checkCornersPositions() {
        int count = 0;
        if (grid[0][0] == '@') {
            count++;
            grid[0][0] = 'X';
        }
        if (grid[0][xMaxIndex] == '@') {
            count++;
            grid[0][xMaxIndex] = 'X';
        }
        if (grid[yMaxIndex][0] == '@') {
            count++;
            grid[yMaxIndex][0] = 'X';
        }
        if (grid[yMaxIndex][xMaxIndex] == '@') {
            count++;
            grid[yMaxIndex][xMaxIndex] = 'X';
        }
        return count;
    }

    private boolean isPaperRollAvailable(int yPos, int xPos){
        int rollCount = 0;
        int[][] offsets = {{-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}};
        
        for (int[] offset : offsets) {
            int ny = yPos + offset[0];
            int nx = xPos + offset[1];
            
            if (ny >= 0 && ny <= yMaxIndex && nx >= 0 && nx <= xMaxIndex) {
                if (grid[ny][nx] == '@' || grid[ny][nx] == 'X') rollCount++;
            }
        }
        return rollCount < 4;
    }

    private void printGrid() {
        for (int y = 0; y <= yMaxIndex; y++) {
            for (int x = 0; x <= xMaxIndex; x++) {
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private void clearProcessed() {
        for (int y = 0; y <= yMaxIndex; y++) {
            for (int x = 0; x <= xMaxIndex; x++) {
                if (grid[y][x] == 'X') {
                    grid[y][x] = '.';
                }
            }
        }
    }
}
