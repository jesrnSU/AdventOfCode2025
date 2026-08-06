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
  private HashMap<Integer, Long> beamIndex = new HashMap<>();  
  private HashMap<Integer, Long> splitIndexes = new HashMap<>();

  public Laboratories2() {
    try {
      data = FileUtil.readAllLines("src/day7/data.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }
    X_LEFT = 0;
    X_RIGHT = data.get(0).length();
  }

  public long solve() {
    long total = 0;
    long start = 1;
    int startingIndex = data.get(0).indexOf('S');
    beamIndex.put(startingIndex, start);
    splitIndexes.put(startingIndex, start);

    for (String s : data) {
      for (Integer i : beamIndex.keySet()) {
        if (s.charAt(i) == '^') {
          addSplitBeams(i);
        }
      }
      beamIndex = new HashMap<>(splitIndexes);
    }
    
    for(Long e : beamIndex.values()){
      total += e;
    }
    return total;
  }

  private void addSplitBeams(Integer i){
    if((i - 1) >= X_LEFT){
      if(splitIndexes.get(i - 1) != null){
        splitIndexes.put(i - 1, splitIndexes.get(i - 1) + splitIndexes.get(i));
      }else{
        splitIndexes.put(i - 1, splitIndexes.get(i));
      }
    }
    if((i + 1) <= X_RIGHT){
      if(splitIndexes.get(i + 1) != null){
        splitIndexes.put(i + 1, splitIndexes.get(i + 1) + splitIndexes.get(i));
      }else{
        splitIndexes.put(i + 1, splitIndexes.get(i));
      }
    }
    splitIndexes.remove(i);
  }
}
