package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Aoc2Part1 {

    public long solve(String filename) {
        long count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            String[] input = null;

            while ((line = br.readLine()) != null) {
                input = line.split(",");
            }

            for (String s : input) {
                String[] ranges = s.split("-");
                count += countInrange(Long.parseLong(ranges[0]), Long.parseLong(ranges[1]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    private long countInrange(long startVal, long endVal) {
        long result = 0;
        for (long i = startVal; i <= endVal; i++) {
            long digits = (long) (Math.log10(i) + 1);
            if((digits % 2) == 0)
                continue;

            long divisor = (long) (Math.pow(10, digits / 2));

            long firstHalf = i / divisor;
            long secondHalf = i % divisor;

            if (firstHalf == secondHalf) {
                result += (i);
            }
        }
        return result;
    }
}
