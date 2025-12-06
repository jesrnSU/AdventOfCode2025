package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Aoc2Part2 {

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
        long temp;
        for (long i = startVal; i <= endVal; i++) {
            long digits = (long) (Math.log10(i) + 1);
            if ((digits % 3) == 0) {
                temp = calculateUneven(i, digits, 3);
                if (temp > 0) {
                    System.out.println("ADD :" + temp);
                    result += temp;
                    continue;
                }
            }
            if ((digits % 4) == 0) {
                temp = calculateUneven(i, digits, 4);
                if (temp > 0) {
                    System.out.println("ADD :" + temp);
                    result += temp;
                    continue;
                }
            }
            if((digits % 5) == 0){
                temp = calculateUneven(i, digits, 5);
                if(temp > 0){
                    System.out.println("ADD :" + temp);
                    result += temp;
                    continue;
                }
            }
            if ((digits % 7) == 0) {
                temp = calculateUneven(i, digits, 7);
                if (temp > 0) {
                    System.out.println("ADD :" + temp);
                    result += temp;
                    continue;
                }
            }
            if ((digits % 2) == 0) {
                temp = calculateEven(i, digits);
                if(temp > 0){
                    System.out.println("ADD :" + temp);
                    result += temp;
                    continue;
                }
            }
            temp = sequenceSameDigit(i);
            result += temp;
        }
        return result;
    }

    private long calculateEven(long i, long digits) {
        long divisor = (long) (Math.pow(10, digits / 2));

        long firstHalf = i / divisor;
        long secondHalf = i % divisor;

        if (firstHalf == secondHalf) {
            return i;
        }
        return 0;
    }

    private long calculateUneven(long i, long digits, int modNum) {
        int groupSize = (int) digits / modNum;  
        long groupDivisor = (long) Math.pow(10, groupSize);
        
        long firstGroup = i % groupDivisor;
        long remaining = i / groupDivisor;
        
        for (int g = 1; g < modNum; g++) {
            long currentGroup = remaining % groupDivisor;
            if (currentGroup != firstGroup) {
                return 0;
            }
            remaining /= groupDivisor;
        }
        
        return i;
    }

    private long sequenceSameDigit(long i){
        int digit = (int) (i % 10);
        i/=10;

        while(i > 0){
            if(i % 10 != digit) return 0;
            i /= 10;
        }
        return i;
    }
}
