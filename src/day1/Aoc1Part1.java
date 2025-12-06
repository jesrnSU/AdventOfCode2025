package day1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Aoc1Part1 {
    
    public int solve(String fileName) throws IOException {
        int zeroHitCount = 0;
        int pos = 50;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while((line = br.readLine()) != null){
                int val = Integer.parseInt(line.substring(1));
                if (line.charAt(0) == 'R') {
                    pos += val;
                } else {
                    pos -= val;
                }

                if((pos = (pos % 100)) == 0){
                    zeroHitCount++;
                }
            }
        }
        return zeroHitCount;
    }
}
