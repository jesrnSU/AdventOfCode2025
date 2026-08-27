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

  public Factory() {
    try {
      data = FileUtil.readAllLines("src/day10/testData.txt");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    machines = createMachines(data);
  }

  private List<Machine> createMachines(List<String> data) {
    ArrayList<Machine> initMachines = new ArrayList<>();
    List<List<Integer>> wiring;
    int[] joltages = null;
    Pattern pattern = Pattern.compile("\\((\\d+(?:,\\s*\\d+)*)\\)");
    Pattern pattern2 = Pattern.compile("\\{(\\d+(?:,\\s*\\d+)*)\\}");
    Matcher matcher;
    List<Integer> wire = null;

    for (String s : data) {
      String lightsString = s.substring(s.indexOf("[") + 1, s.indexOf("]"));
      long goalLights = 0L;
      for(int i = 0; i < lightsString.length(); i++){
        if(lightsString.charAt(i) == '#'){
          goalLights |= (1L << i);
        }
      }


      wiring = new LinkedList<>();
      matcher = pattern.matcher(s);
      while (matcher.find()) {
        wire = new ArrayList<>();
        for (String num : matcher.group(1).split(",")) {
          wire.add(Integer.parseInt(num.trim()));
        }
        wiring.add(wire);
      }

      matcher = pattern2.matcher(s);
      if (matcher.find()) {
        String numbers = matcher.group(1);
        joltages = Arrays.stream(numbers.split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
      }
      initMachines.add(new Machine(goalLights, wiring, joltages));
    }
    return initMachines;
  }

  public long solve() {
    long minButtonPresses = 0;
    for (Machine m : machines) {
      minButtonPresses += bfs(m.getLights(), m.getButtons());
    }

    // Machine m = machines.get(0);
    // bfs(m.getLights(), m.getButtons());
    return minButtonPresses;
  }

  public long solve2(){
    long minButtonPresses = 0;
    /*for(Machine m : machines){
      minButtonPresses += bfs(m.getLights(), m.getButtons(), m.getJoltage());
    }*/

    Machine m = machines.get(0);
    bfs(m.getLights(), m.getButtons(), m.getJoltage());

    return minButtonPresses;
  }

  private long bfs(long goalLights, List<List<Integer>> wiring) {
    Queue<State> states = new LinkedList<>();
    Set<Long> visitedLights = new HashSet<>();
    int buttonPresses = 0;

    long startingState = 0L;
    states.add(new State(startingState, buttonPresses));

    while (!states.isEmpty()) {
      State currentState = states.poll();

      for (List<Integer> btnSequence : wiring) {
        long currentLights = currentState.getLights();
        // Toggle sequence on current state
        for (Integer button : btnSequence) {
          currentLights ^= (1L << button);
        }

        if (currentLights == goalLights) {
          return currentState.getDepth() + 1;
        }

        if (visitedLights.add(currentLights)) {
          states.add(new State(currentLights, currentState.getDepth() + 1));
        }
      }
    }
    return 0;
  }

  private long bfs(long goalLights, List<List<Integer>> wiring, int[] goalJoltage) {
    Queue<State> states = new LinkedList<>();
    int buttonPresses = 0;

    int maxTries = 0;

    long startingState = 0L;
    int[] startingJoltage = new int[goalJoltage.length];

    System.out.println("Goal : " + goalLights + " : " + Arrays.toString(goalJoltage));

    states.add(new State(startingState, buttonPresses, startingJoltage));

    while (!states.isEmpty()) {
      maxTries++;
      State currentState = states.poll();
      System.out.println("Starting state: " + currentState);

      for (List<Integer> btnSequence : wiring) {
        long currentLights = currentState.getLights();
        int[] currentJoltage = currentState.getJoltage().clone(); 

        // Toggle sequence on current state
        for (Integer button : btnSequence) {
          currentLights ^= (1L << button);
          currentJoltage[button]++;

          if(currentJoltage[button] > goalJoltage[button]){
            continue;
          }
        }

        System.out.println("\t" + currentLights + " : " + currentState.getDepth() + " : " + Arrays.toString(currentJoltage));

        if (currentLights == goalLights && Arrays.equals(goalJoltage, currentJoltage)) {
          return currentState.getDepth() + 1;
        }

        if(maxTries > 50){
          return 0;
        }

        states.add(new State(currentLights, currentState.getDepth() + 1, currentJoltage));
      }
    }
    return 0;
  }
}
