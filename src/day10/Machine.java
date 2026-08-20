package day10;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Machine {
  private boolean[] lights;
  private List<List<Integer>> buttons;
  private int[] joltage;

  public Machine(boolean[] lights, List<List<Integer>> wiring, int[] joltage){
    this.lights = lights;
    this.buttons = wiring;
    this.joltage = joltage;
  }

  public List<List<Integer>> getButtons() {
    return buttons;
  }

  public int[] getJoltage() {
    return joltage;
  }

  public boolean[] getLights() {
    return lights;
  }


  @Override
  public String toString() {
    return Arrays.toString(lights) + "\n" + buttons + "\n" + Arrays.toString(joltage) + "\n"; 
  }


}
