package day10;

import java.util.Arrays;

public class State {
    private long lights;
    private int depth;
    private int[] joltage;

    public State(long lights, int depth){
        this.lights = lights;
        this.depth = depth;
    }

    public State(long lights, int depth, int[] joltage){
        this.lights = lights;
        this.depth = depth;
        this.joltage = joltage;        
    }

    public int getDepth() {
        return depth;
    }

    public long getLights() {
        return lights;
    }

    public int[] getJoltage() {
        return joltage;
    }

    @Override
    public String toString() {
        return "[" + lights + " : " + depth + " : " + Arrays.toString(joltage) + "]";
    }

}
