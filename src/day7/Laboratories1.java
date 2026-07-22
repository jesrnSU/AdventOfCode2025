package day7;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import util.FileUtil;

public class Laboratories1 {
  private final int X_LEFT;
  private final int X_RIGHT;
  private List<String> data;
  private HashSet<Integer> beamIndex = new HashSet<>();  
  private HashSet<Integer> splitIndexes = new HashSet<>();

  public Laboratories1() {
    try {
      data = FileUtil.readAllLines("src/day7/data.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }
    X_LEFT = 0;
    X_RIGHT = data.get(0).length();
  }

  public int solve() {
    int hitCounter = 0;
    int startingIndex = data.get(0).indexOf('S');
    beamIndex.add(startingIndex);
    splitIndexes.add(startingIndex);

    for (String s : data) {
      for (Integer i : beamIndex) {
        if (s.charAt(i) == '^') {
          hitCounter++;
          addSplitBeams(i);
        }
      }
      beamIndex = new HashSet<>(splitIndexes); 
    }
    return hitCounter;
  }

  private void addSplitBeams(Integer i){
    splitIndexes.remove(i);
    if((i - 1) >= X_LEFT){
      splitIndexes.add(i - 1);
    }
    if((i + 1) <= X_RIGHT){
      splitIndexes.add(i + 1);
    }
  }
}
