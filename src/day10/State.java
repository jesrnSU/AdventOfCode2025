package day10;

import java.util.Arrays;

public class State {

    private long lights;
    private int depth;
    public State(long lights, int depth){
        this.lights = lights;
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }

    public long getLights() {
        return lights;
    }

    @Override
    public String toString() {
        return "[" + lights + " : " + depth;
    }

}
