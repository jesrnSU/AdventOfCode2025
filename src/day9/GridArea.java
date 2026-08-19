package day9;

public class GridArea {
  private Tile tile1;
  private Tile tile2;
  private long area;

  public GridArea(Tile t1, Tile t2){
    this.tile1 = t1;
    this.tile2 = t2;
    area = calculateArea(t1, t2);
  }

  private long calculateArea(Tile x, Tile y){
    long width = 0;
    long height = 0;

    width = Math.abs(x.getX() - y.getX()) + 1;
    height = Math.abs(x.getY() - y.getY()) + 1;

    return width * height;
  }

  public Tile getTile1() {
    return tile1;
  }

  public Tile getTile2() {
    return tile2;
  }

  public long getArea() {
    return area;
  }

  @Override
  public String toString() {
    return "Area: " + this.area + " Between : " + tile1 + " and " + tile2;
  }
}
