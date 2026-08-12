package day8;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import util.FileUtil;

public class Playground1 {
  private ArrayDeque<Coord> unprocessedCoords;
  private Map<Long, Edge> sortedDistances = new TreeMap<>();
  private ArrayList<HashSet<Coord>> circuits = new ArrayList<>();

  public Playground1() {
    try {
      unprocessedCoords = FileUtil.readCoords("src/day8/data.txt");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public long solve() {
    while (!unprocessedCoords.isEmpty()) {
      Coord current = unprocessedCoords.pop();
      calculateDistances(current);
    }

    createCircuits();

    List<Integer> largest = circuits.stream()
      .map(HashSet::size)
      .sorted(Comparator.reverseOrder())
      .limit(3)
      .collect(Collectors.toList());

    long result = 1;
    for(int sizes : largest){
      result *= sizes;
    }
    return result;
  }

  private void calculateDistances(Coord current) {
    for (Coord c : unprocessedCoords) {
      Edge e = new Edge(current, c);
      sortedDistances.put(e.getDistance(), e);
    }
  }

  private void createCircuits() {
    for (Map.Entry<Long, Edge> l : sortedDistances.entrySet()) {
      Coord c1 = l.getValue().getPoint1();
      Coord c2 = l.getValue().getPoint2();

      HashSet<Coord> set1 = null;
      HashSet<Coord> set2 = null;

      for (HashSet<Coord> set : circuits) {
        if (set.contains(c1)) {
          set1 = set;
        }
        if (set.contains(c2)) {
          set2 = set;
        }
      }

      if (set1 == null && set2 == null) {
        HashSet<Coord> newSet = new HashSet<>();
        newSet.add(c1);
        newSet.add(c2);
        circuits.add(newSet);
      } else if (set1 != null && set2 != null) {
        if (set1 != set2) {
          set1.addAll(set2);
          circuits.remove(set2);

          if(set1.size() == 1000){
            System.out.println("Last : " + c1 + " and " + c2);
            return;
          }
        }
      } else {
        HashSet<Coord> targetSet = (set1 != null) ? set1 : set2;
        targetSet.add(c1);
        targetSet.add(c2);
        if(targetSet.size() == 1000){
          System.out.println("Last : " + c1 + " and " + c2);
          return;
        }
      }
    }
  }
}
