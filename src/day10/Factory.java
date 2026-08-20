package day10;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
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
    machines = createMachines(data);
  }

  private List<Machine> createMachines(List<String> data){
    ArrayList<Machine> initMachines = new ArrayList<>();
    boolean[] lights;
    List<List<Integer>> wiring;
    int[] joltages = null;
    Pattern pattern = Pattern.compile("\\((\\d+(?:,\\s*\\d+)*)\\)");    
    Pattern pattern2 = Pattern.compile("\\{(\\d+(?:,\\s*\\d+)*)\\}");
    Matcher matcher;
    List<Integer> wire = null;
    int lightsCount = 0;

    for(String s : data){
      lightsCount = 0;
      char[] lightsChars = s.substring(s.indexOf("[") + 1, s.indexOf("]")).toCharArray();
      lights = new boolean[lightsChars.length];
      for(char c : lightsChars){
        if(c == '.'){
          lights[lightsCount++] = false;
        }else{
          lights[lightsCount++] = true;
        }
      }
      wiring = new LinkedList<>();
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
      initMachines.add(new Machine(lights, wiring, joltages));
    }
    return initMachines;
  }

  public long solve(){
    int minButtonPresses = 0;
    /*for(Machine m : machines){
      minButtonPresses += bfs(m.getLights(), m.getButtons());
    }*/

    Machine m = machines.get(0);
    bfs(m.getLights(), m.getButtons());
    return minButtonPresses;
  }

  private boolean containsLights(Set<boolean[]> visitedLights, boolean[] currentLights){
    return visitedLights.stream().anyMatch(arr -> Arrays.equals(arr, currentLights));
  }

  private int bfs(boolean[] goalLights, List<List<Integer>> wiring){
    Queue<boolean[]> states = new LinkedList<>();
    Set<boolean[]> visitedLights = new HashSet<>();
    int buttonPresses = 0;

    System.out.println("GOAL : " + Arrays.toString(goalLights));
    
    boolean[] startingState = new boolean[goalLights.length];
    Arrays.fill(startingState, false);
    states.add(startingState);

    while(!states.isEmpty()){
      boolean[] currentState = states.poll();
      buttonPresses++;

      System.out.println();
      System.out.println("-------------------------");
      System.out.println();

      for(List<Integer> btnSequence : wiring){
        boolean[] currentLights = currentState.clone();
        System.out.println("Current State : " + Arrays.toString(currentState));
        // Toggle sequence on current state
        for(Integer button : btnSequence){
          currentLights[button] = !currentState[button];
        }

        System.out.println("Applied : " + btnSequence);
        System.out.println("Current Lights : " + Arrays.toString(currentLights));
        System.out.println();

        if(Arrays.equals(currentLights, goalLights)){
          System.out.println("Sol found : " + Arrays.toString(currentLights) + buttonPresses);
          return buttonPresses;
        }else if(!containsLights(visitedLights, currentLights)){
          states.add(currentLights.clone());
        }
      }
      visitedLights.add(currentState.clone());
    }
    System.out.println();
    return 0;
  }
}

