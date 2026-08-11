package day8;

public class Edge {
  private long distance;
  private Coord point1;
  private Coord point2;

  public Edge(Coord p1, Coord p2){
    this.point1 = p1;
    this.point2 = p2;
    this.distance = calculateDistance();
  }

  private long calculateDistance(){
    long v1, v2, v3;

    v1 = point1.getX() - point2.getX();
    v2 = point1.getY() - point2.getY();
    v3 = point1.getZ() - point2.getZ();

    v1 *= v1;
    v2 *= v2;
    v3 *= v3;
    return v1 + v2 + v3;
  }

  public Coord getPoint1() {
    return point1;
  }

  public Coord getPoint2() {
    return point2;
  }

  public long getDistance() {
    return distance;
  }

  @Override
  public String toString() {
    return  point1.toString() + " - " + point2.toString() + " = " + distance;
  }
}
