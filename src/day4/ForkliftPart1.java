package day4;

import util.*;
import java.util.List;

public class ForkliftPart1 {
    private final String DAY4INPUT = "src/day4/data.txt";
    private final String DAY4TESTINPUT = "src/day4/datatest.txt";
    private int xAxis;
    private int yAxis;

    public int solve() {
        int count = 0;
        List<String> lines = null;
        try {
            lines = FileUtil.readAllLines(DAY4INPUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        yAxis = lines.size() - 1;
        xAxis = lines.get(0).length() - 1;
       
        // Check corner positions. A corner can't have more than 3 adjacent positions. => If roll, then available.
        count += checkCornersPositions(lines.get(0), lines.get(yAxis));

        // Check top and bot rows characters
        count += checkAdjacentEdgeCase(lines.get(0), lines.get(1));
        count += checkAdjacentEdgeCase(lines.get(yAxis), lines.get(yAxis - 1));

        // Start building the 2 left and rightmost columns of characters
        char[] yAxisLeftEdge = new char[yAxis + 1];
        char[] yAxisLeftNeighbour = new char[yAxis + 1];
        yAxisLeftEdge[0] = lines.get(0).charAt(0);
        yAxisLeftNeighbour[0] = lines.get(0).charAt(1);
        char[] yAxisRightEdge = new char[yAxis + 1];
        char[] yAxisRightNeighbour = new char[yAxis + 1];
        yAxisRightEdge[0] = lines.get(0).charAt(xAxis);
        yAxisRightNeighbour[0] = lines.get(0).charAt(xAxis - 1);

        // Loop to count every character with 8 adjacent positions
        for (int i = 1; i < yAxis; i++) {
            String topLine = lines.get(i - 1);
            String midLine = lines.get(i);
            String botLine = lines.get(i + 1);

            count += checkAdjacent(topLine, midLine, botLine);

            yAxisLeftEdge[i] = lines.get(i).charAt(0);
            yAxisLeftNeighbour[i] = lines.get(i).charAt(1);
            yAxisRightEdge[i] = lines.get(i).charAt(xAxis);
            yAxisRightNeighbour[i] = lines.get(i).charAt(xAxis - 1);
        }

        // Add the last characters to the left and rightmost columns 
        yAxisLeftEdge[yAxis] = lines.get(yAxis).charAt(0);
        yAxisLeftNeighbour[yAxis] = lines.get(yAxis).charAt(1);
        yAxisRightEdge[yAxis] = lines.get(yAxis).charAt(xAxis);
        yAxisRightNeighbour[yAxis] = lines.get(yAxis).charAt(xAxis - 1);

        count += checkAdjacentEdgeCase(String.valueOf(yAxisLeftEdge), String.valueOf(yAxisLeftNeighbour));
        count += checkAdjacentEdgeCase(String.valueOf(yAxisRightEdge), String.valueOf(yAxisRightNeighbour));
        return count;
    }
    
    private int checkAdjacent(String top, String mid, String bot) {
        int availableRollCount = 0;

        for (int i = 1, length = mid.length(); i < length - 1; i++) {
            if (mid.charAt(i) != '@')
                continue;
            if(isPaperRollAvailable(top.charAt(i - 1), top.charAt(i), top.charAt(i + 1), mid.charAt(i - 1), mid.charAt(i + 1), bot.charAt(i - 1), bot.charAt(i), bot.charAt(i + 1))){ 
                availableRollCount++;
            }
        }
        return availableRollCount;
    }

    private int checkAdjacentEdgeCase(String current, String neighbour) {
        int availableRollCount = 0;

        for (int i = 1, length = current.length(); i < length - 1; i++) {
            if (current.charAt(i) != '@') 
                continue;
            if(isPaperRollAvailable(
                ' '                , ' '            , ' ', 
                current.charAt(i - 1),                        current.charAt(i + 1), 
                neighbour.charAt(i - 1), neighbour.charAt(i), neighbour.charAt(i + 1))){

                    availableRollCount++;
            }
        }
        return availableRollCount;
    }

    private int checkCornersPositions(String topLine, String botLine){
        int count = 0;
        if (topLine.charAt(0) == '@')
            count++;
        if (topLine.charAt(xAxis) == '@')
            count++;
        if (botLine.charAt(0) == '@')
            count++;
        if (botLine.charAt(xAxis) == '@')
            count++;
        return count;
    }

    private boolean isPaperRollAvailable(char t1, char t2, char t3, char m1, char m3, char b1, char b2, char b3) {
        int rollCount = 0;
        if (t1 == '@') rollCount++;
        if (t2 == '@') rollCount++;
        if (t3 == '@') rollCount++;
        if (m1 == '@') rollCount++;
        if (m3 == '@') rollCount++;
        if (b1 == '@') rollCount++;
        if (b2 == '@') rollCount++;
        if (b3 == '@') rollCount++;

        if(rollCount < 4)
            return true;
        return false;
    }
}
