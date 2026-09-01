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
  private int minButtonPresses;

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
    int result = 0;
    for(Machine m : machines){
      result += solveMachine(m.getButtons(), m.getJoltage()); 
    }

    return result;
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
        currentLights = updateLights(currentLights, btnSequence);

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

  public int solveMachine(List<List<Integer>> wiring, int[] goalJoltage) {
        int numCounters = goalJoltage.length;
        
        Queue<JoltageState> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        int[] startJoltage = new int[numCounters];
        queue.add(new JoltageState(startJoltage, 0));
        visited.add(Arrays.toString(startJoltage));

        while (!queue.isEmpty()) {
            JoltageState current = queue.poll();

            if (Arrays.equals(current.joltage, goalJoltage)) {
                return current.presses;
            }

            for (List<Integer> wire : wiring) {
                int[] nextJoltage = current.joltage.clone();
                boolean validMove = true;

                for (Integer btn : wire) {
                    nextJoltage[btn]++;
                    if (nextJoltage[btn] > goalJoltage[btn]) {
                        validMove = false;
                        break; 
                    }
                }

                if (!validMove) {
                    continue; 
                }

                String stateStr = Arrays.toString(nextJoltage);
                if (!visited.contains(stateStr)) {
                    visited.add(stateStr);
                    queue.add(new JoltageState(nextJoltage, current.presses + 1));
                }
            }
        }

        return -1; 
    }

    private static class JoltageState {
        int[] joltage;
        int presses;

        JoltageState(int[] joltage, int presses) {
            this.joltage = joltage;
            this.presses = presses;
        }
    }

  private void dfs(List<List<Integer>> wiring, int[] goalJoltage, int[] currentJoltage, int currentPresses, int startIndex) {
    if(currentPresses >= minButtonPresses){
      return;
    }

    if(Arrays.equals(goalJoltage, currentJoltage)){
      minButtonPresses = currentPresses;
      return;
    }

    int nextPresses = currentPresses + 1;

    for(int i = startIndex; i < wiring.size(); i++){
      List<Integer> wire = wiring.get(i);

      if(isNextJoltageInvalid(currentJoltage, goalJoltage, wire)){
        continue;
      }

      updateJoltage(currentJoltage, wire, 1);
      dfs(wiring, goalJoltage, currentJoltage, nextPresses, startIndex);
      updateJoltage(currentJoltage, wire, -1);
    }
  }

  private boolean isNextJoltageInvalid(int[] currentJoltage, int[] goalJoltage, List<Integer> wire){
    for(Integer btn : wire){
      if((currentJoltage[btn] + 1) > goalJoltage[btn]){
        return true;
      }
    }
    return false;
  }

  private int[] updateJoltage(int[] currentJoltage, List<Integer> wire, int mode){
    for(Integer btn : wire){
      currentJoltage[btn] += mode;
    }
    return currentJoltage;
  }

  private long updateLights(long currentLights, List<Integer> wire){
    for(Integer btn : wire){
      currentLights ^= (1L << btn);
    }
    return currentLights;
  }
}
