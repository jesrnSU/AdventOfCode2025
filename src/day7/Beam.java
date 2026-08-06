package day7;

public class Beam {
  private int splitCount; 

  public Beam(int splitCount) {
    this.splitCount = splitCount;
  }

    public int getSplitCount() {
    return splitCount;
  }

  @Override
  public String toString() {
    return " Split count: " + splitCount;
  }
}
