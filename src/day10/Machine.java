package day10;

import java.util.Arrays;
import java.util.List;

public class Machine {
  private long lights;
  private List<List<Integer>> buttons;
  private int[] joltage;

  public Machine(long lights, List<List<Integer>> wiring, int[] joltage){
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

  public long getLights() {
    return lights;
  }

  @Override
  public String toString() {
    return lights + "\n" + buttons + "\n" + Arrays.toString(joltage) + "\n"; 
  }
}
