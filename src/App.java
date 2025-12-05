import day1.Aoc1;
import day1.Aoc2;
import day2.AocDay2;

public class App {
    public static void main(String[] args) throws Exception {
        Aoc1 aoc1 = new Aoc1();
        int count = aoc1.solve("src/day1/1-indata.txt");
        System.out.println("Aoc1 : " + count);

        Aoc2 aoc2 = new Aoc2();
        int count2 = aoc2.solve("src/day1/indatatest1.txt");
        System.out.println("Aoc2 : " + count2);

        AocDay2 aocDay2 = new AocDay2();
        long count3 = aocDay2.solve("src/day2/data.txt");
        System.out.println(count3);
    }
}
