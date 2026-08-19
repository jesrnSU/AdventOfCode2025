package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import day8.Coord;
import day9.Tile;

public final class FileUtil {
  private static int tileGridLargestX;
  private static int tileGridLargestY;

  private FileUtil() {
  }

  public static List<String> readAllLines(String filename) throws IOException {
    List<String> lines = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        lines.add(line);
      }
    }
    return lines;
  }

  public static ArrayDeque<Tile> readTiles(String filename) throws IOException {
    ArrayDeque<Tile> tiles = new ArrayDeque<>();
    int largestX = 0;
    int currentX = 0;
    int largestY = 0;
    int currentY = 0;

    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      String[] coords = new String[2];

      while ((line = br.readLine()) != null) {
        coords = line.split(",");
        currentX = Integer.parseInt(coords[0]);
        currentY = Integer.parseInt(coords[1]);
        
        if(currentX > largestX){
          largestX = currentX;
        }
        if(currentY > largestY){
          largestY = currentY;
        }
        tiles.add(new Tile(currentX, currentY));
      }
      tileGridLargestX = largestX;
      tileGridLargestY = largestY;
    }
    return tiles;
  }

  public static int getTileGridLargestX() {
    return tileGridLargestX;
  }

  public static int getTileGridLargestY() {
    return tileGridLargestY;
  }

  public static ArrayDeque<Coord> readCoords(String filename) throws IOException {
    ArrayDeque<Coord> queue = new ArrayDeque<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      String[] coords = new String[3];
      while ((line = br.readLine()) != null) {
        coords = line.split(",");
        queue.add(new Coord(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2])));
      }
    }
    return queue;
  }

  public static String readAsString(String filename) throws IOException {
    StringBuilder sb = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        sb.append(line).append("\n");
      }
    }
    return sb.toString();
  }
}