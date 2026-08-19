package day10;

import java.util.Arrays;
import java.util.List;

public class Machine {
  private char[] lights;
  private List<List<Integer>> buttons;
  private int[] joltage;

  public Machine(char[] lights, List<List<Integer>> wiring, int[] joltage){
    this.lights = lights;
    this.buttons = wiring;
    this.joltage = joltage;
  }

  public List<List<Integer>> getButtons() {
    return buttons;
  }


  @Override
  public String toString() {
    return Arrays.toString(lights) + "\n" + buttons + "\n" + Arrays.toString(joltage) + "\n"; 
  }


}
