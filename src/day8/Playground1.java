package day8;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import util.FileUtil;

import java.util.List;

public class Playground1 {

  private List<String> data;
  private ArrayList<Coord> processedCoords = new ArrayList<>();
  private Map<Float, Edge> sortedDistances = new TreeMap<>(); 

  public Playground1(){
    try{
      data = FileUtil.readAllLines("src/day8/testdata.txt");
    }catch(IOException e){
      System.out.println(e.getMessage());
    }
  }
  public long solve(){
    System.out.println(data.toString());
    return 0;
  }
}
