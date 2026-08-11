package day8;

import java.util.Objects;

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
  public boolean equals(Object o){
    if(this == o) return true;
    if(o == null || getClass() != o.getClass()) return false;
    Coord coord = (Coord) o;
    return this.getX() == coord.getX() && this.getY() == coord.getY() && this.getZ() == coord.getZ();
  }

  @Override
  public int hashCode(){
    return Objects.hash(this.x, this.y, this.z);
  }
  
  @Override
  public String toString() {
    return "(" + this.x + "," + this.y + "," + this.z + ")";
  }
}
