import day1.Aoc1Part1;
import day1.Aoc1Part2;
import day2.Aoc2Part1;
import day2.Aoc2Part2;

public class App {
    public static void main(String[] args) throws Exception {
        /*
         * Aoc1Part1 aoc1 = new Aoc1Part1();
         * int count = aoc1.solve("src/day1/1-indata.txt");
         * System.out.println("Aoc1 : " + count);
         * 
         * Aoc1Part2 aoc2 = new Aoc1Part2();
         * int count2 = aoc2.solve("src/day1/indatatest1.txt");
         * System.out.println("Aoc2 : " + count2);
         * 
         * Aoc2Part1 aocDay2 = new Aoc2Part1();
         * long count3 = aocDay2.solve("src/day2/data.txt");
         * System.out.println(count3);
         */

        Aoc2Part2 p2 = new Aoc2Part2();
        System.out.println(p2.solve("src/day2/datatest.txt"));
    }
}
