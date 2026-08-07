package day8;

import java.util.ArrayList;
import java.util.HashMap;

public class Coord {
  private int x;
  private int y;
  private int z;

  public Coord(int x, int y, int z){
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getZ() {
    return z;
  }

  @Override
  public String toString() {
    return "(" + this.x + "," + this.y + "," + this.z + ")";
  }
}
