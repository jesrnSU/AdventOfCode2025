package day9;

import util.FileUtil;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.TreeSet;

public class MovieTheater1 {
  private ArrayDeque<Tile> tiles;
  private TreeSet<GridArea> sortedAreas = new TreeSet<>((r1, r2) -> {
    int compare = Long.compare(r2.getArea(), r1.getArea());
    if (compare != 0)
      return compare;

    compare = Integer.compare(r1.getTile1().getX(), r2.getTile1().getX());
    if (compare != 0)
      return compare;

    compare = Integer.compare(r1.getTile1().getY(), r2.getTile1().getY());
    if (compare != 0)
      return compare;

    compare = Integer.compare(r1.getTile2().getX(), r2.getTile2().getX());
    if (compare != 0)
      return compare;

    return Integer.compare(r1.getTile2().getY(), r2.getTile2().getY());
  });

  public MovieTheater1() {
    try {
      tiles = FileUtil.readTiles("src/day9/data.txt");
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  // The entire grid/presum solution below does not work on larger datasets but
  // was interesting to learn...
  public long solve2() {
    Tile firstTile = tiles.peek();
    Tile currentTile = null;
    Tile previousTile = null;
    int totalCols = FileUtil.getTileGridLargestX() + 2;
    int totalRows = FileUtil.getTileGridLargestY() + 2;
    System.out.println("Rows : " + totalRows);
    System.out.println("Cols : " + totalCols);
    long[] grid = new long[totalRows * totalCols];
    long[][] prefixSum;

    while (!tiles.isEmpty()) {
      currentTile = tiles.pop();
      addGridTile(grid, currentTile, previousTile, totalCols);
      previousTile = currentTile;
      for (Tile tile : tiles) {
        GridArea rect = new GridArea(currentTile, tile);
        sortedAreas.add(rect);
      }
    }

    // Connect last and first tile
    addGridTile(grid, currentTile, firstTile, totalCols);
    fillInternalGrid(grid, totalCols, totalRows);
    prefixSum = buildPrefixSumGrid(grid, totalCols, totalRows);

    // For debugging
    // printGrid(grid, totalCols, totalRows);
    // printPrefixSumGrid(prefixSum, totalCols, totalRows);

    return largestValidArea(prefixSum);
  }

  private void fillInternalGrid(long[] grid, int totalCols, int totalRows) {
    for (int y = 1; y < totalRows - 1; y++) {
      boolean inside = false;
      boolean edgeStartedGoingUp = false;

      for (int x = 1; x < totalCols - 1; x++) {
        int index = (y * totalCols) + x;

        if (grid[index] == 1) {
          boolean wallUp = (grid[index - totalCols] == 1);
          boolean wallDown = (grid[index + totalCols] == 1);

          if (wallUp && wallDown && grid[index + 1] == 0) {
            inside = !inside;
          } else if ((wallUp || wallDown) && grid[index - 1] == 0) {
            edgeStartedGoingUp = wallUp;
          } else if ((wallUp || wallDown) && grid[index + 1] == 0) {
            if (wallUp != edgeStartedGoingUp) {
              inside = !inside;
            }
          }
        } else {
          if (inside) {
            grid[index] = 1;
          }
        }
      }
    }
  }

  private long largestValidArea(long[][] prefixSum) {
    for (GridArea rect : sortedAreas) {
      int minX = Math.min(rect.getTile1().getX(), rect.getTile2().getX());
      int maxX = Math.max(rect.getTile1().getX(), rect.getTile2().getX());
      int minY = Math.min(rect.getTile1().getY(), rect.getTile2().getY());
      int maxY = Math.max(rect.getTile1().getY(), rect.getTile2().getY());

      long area = (long) (maxX - minX + 1) * (maxY - minY + 1);

      long valid = prefixSum[maxY + 1][maxX + 1]
          - prefixSum[minY][maxX + 1]
          - prefixSum[maxY + 1][minX]
          + prefixSum[minY][minX];

      if (area == valid) {
        return area;
      }
    }
    return 0;
  }

  private void printPrefixSumGrid(long[][] prefixSum, int totalCols, int totalRows) {
    for (int y = 0; y < totalRows + 1; y++) {
      for (int x = 0; x < totalCols + 1; x++) {
        System.out.format("%02d ", prefixSum[y][x]);
      }
      System.out.println();
    }
  }

  private long[][] buildPrefixSumGrid(long[] grid, int totalCols, int totalRows) {
    long[][] prefixSum = new long[totalRows + 1][totalCols + 1];

    for (int i = 1; i < totalRows; i++) {
      for (int j = 1; j < totalCols; j++) {
        long gridValue = grid[(i * totalCols) + j];

        prefixSum[i + 1][j + 1] = gridValue + prefixSum[i][j + 1]
            + prefixSum[i + 1][j] - prefixSum[i][j];
      }
    }
    return prefixSum;
  }

  private void printGrid(long[] grid, int totalCols, int totalRows) {
    for (int y = 0; y < totalRows; y++) {
      for (int x = 0; x < totalCols; x++) {
        int index = (y * totalCols) + x;

        if (grid[index] == 1) {
          System.out.print("#");
        } else {
          System.out.print(".");
        }
      }
      System.out.println();
    }
  }

  private void addGridTile(long[] grid, Tile currentTile, Tile previousTile, int totalCols) {
    grid[(currentTile.getY() * totalCols) + currentTile.getX()] = 1;

    if (previousTile == null) {
      return;
    }

    // Draw line between Tiles
    int start;
    int end;
    if (currentTile.getX() == previousTile.getX()) {
      start = Math.min(currentTile.getY(), previousTile.getY());
      end = Math.max(currentTile.getY(), previousTile.getY());
      for (int i = start; i < end; i++) {
        grid[(i * totalCols) + currentTile.getX()] = 1;
      }
    } else {
      start = Math.min(currentTile.getX(), previousTile.getX());
      end = Math.max(currentTile.getX(), previousTile.getX());
      for (int i = start; i < end; i++) {
        grid[(currentTile.getY() * totalCols) + i] = 1;
      }
    }
  }

  public long solve() {
    Tile currentTile;
    long largestArea = 0;
    long currentArea;

    while ((currentTile = tiles.poll()) != null) {
      for (Tile tile : tiles) {
        currentArea = new GridArea(currentTile, tile).getArea();
        if (currentArea > largestArea) {
          largestArea = currentArea;
        }
      }
    }
    return largestArea;
  }

}
