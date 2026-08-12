package day9;

import util.FileUtil;

import java.io.IOException;
import java.util.ArrayDeque;

public class MovieTheater1 {
  ArrayDeque<Tile> tiles;

  public MovieTheater1(){
    try{
      tiles = FileUtil.readTiles("src/day9/data.txt");
    }catch(IOException e){
      System.out.println(e);
    }
  }

  public long solve2(){
    
    return 0;
  }

  public long solve(){
    Tile currentTile;
    long largestArea = 0;
    long currentArea;

    while((currentTile = tiles.poll()) != null){
      for(Tile tile : tiles){
        currentArea = calculateArea(currentTile, tile);
        if(currentArea > largestArea){
          largestArea = currentArea;
        }
      }
    }
    return largestArea;
  }

  private long calculateArea(Tile x, Tile y){
    long width = 0;
    long height = 0;

    width = Math.abs(x.getX() - y.getX()) + 1;
    height = Math.abs(x.getY() - y.getY()) + 1;

    return width * height;
  }

}
