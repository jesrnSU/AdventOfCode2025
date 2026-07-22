package day7;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import util.FileUtil;

public class Laboratories2 {
  private final int X_LEFT;
  private final int X_RIGHT;
  private List<String> data;
  private HashMap<Integer,Integer> beamIndex = new HashMap<>();  
  private HashMap<Integer,Integer> splitIndexes = new HashMap<>();

  public Laboratories2() {
    try {
      data = FileUtil.readAllLines("src/day7/miniData.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }
    X_LEFT = 0;
    X_RIGHT = data.get(0).length();
  }

  public int solve() {
    int startingIndex = data.get(0).indexOf('S');
    beamIndex.put(startingIndex, 1);
    splitIndexes.put(startingIndex, 1);
    int count = 0;

    for (String s : data) {
      System.out.println(splitIndexes.values());
      for (Integer i : beamIndex.keySet()) {
        if (s.charAt(i) == '^') {
          count += beamIndex.get(i);
          addSplitBeams(i);
        }
      }
      beamIndex = new HashMap<>(splitIndexes); 
    }
    return count;
  }

  private void addSplitBeams(Integer i){
    if((i - 1) >= X_LEFT){
      if(splitIndexes.get(i - 1) == null){
        splitIndexes.put(i - 1, beamIndex.get(i));
      }else{
        splitIndexes.put(i - 1, splitIndexes.get(i - 1) + 1);
      }
    }
    if((i + 1) <= X_RIGHT){
      if(splitIndexes.get(i + 1) == null){
        splitIndexes.put(i + 1, beamIndex.get(i));
      }else{
        splitIndexes.put(i + 1, splitIndexes.get(i + 1) + 1);
      }
    }
    splitIndexes.remove(i);
  }

  private int countTimelines(){
    int sum = 0;
    for(Integer i : beamIndex.values()){
      sum += i;
    }
    System.out.println(splitIndexes.values());
    return sum;
  }
}
