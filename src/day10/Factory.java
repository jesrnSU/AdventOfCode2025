package day10;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.FileUtil;

public class Factory {
  private List<String> data;
  private List<Machine> machines;

  public Factory(){
    try{
    data = FileUtil.readAllLines("src/day10/testData.txt");
    }catch(IOException e){
      System.out.println(e.getMessage());
    }
    createMachines(data);
  }

  private List<Machine> createMachines(List<String> data){
    machines = new ArrayList<>();
    char[] lights;
    List<List<Integer>> wiring;
    int[] joltages = null;
    Pattern pattern = Pattern.compile("\\((\\d+(?:,\\s*\\d+)*)\\)");    
    Pattern pattern2 = Pattern.compile("\\{(\\d+(?:,\\s*\\d+)*)\\}");
    Matcher matcher;
    List<Integer> wire = null;

    for(String s : data){
      lights = s.substring(s.indexOf("[") + 1, s.indexOf("]")).toCharArray();
      wiring = new ArrayList<>();
      matcher = pattern.matcher(s);
      while(matcher.find()){
        wire = new ArrayList<>();
        for(String num : matcher.group(1).split(",")){
          wire.add(Integer.parseInt(num.trim()));
        }
        wiring.add(wire);
      }

      matcher = pattern2.matcher(s);
      if(matcher.find()){
        String numbers = matcher.group(1);
        joltages = Arrays.stream(numbers.split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
      }
      machines.add(new Machine(lights, wiring, joltages));
    }
    return null;
  }

  public long solve(){
    for(Machine m : machines){
      System.out.println(m.toString()); 

      for(List<Integer> buttons : m.getButtons()){
        
      }
    }
    return 0;
  }
}

