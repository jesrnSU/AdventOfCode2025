package day1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Aoc2 {

    public static final int ROTATIONS = 100;

    public int solve(String fileName) throws IOException {
        int zeroHitCount = 0;
        int pos = 50;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while((line = br.readLine()) != null){
                int val = Integer.parseInt(line.substring(1));
                if(val >= ROTATIONS){
                    zeroHitCount += val / ROTATIONS;
                    val %= ROTATIONS;
                }

                if (line.charAt(0) == 'R') {
                    pos += val;
                    if(pos > 99){
                        zeroHitCount++;
                        pos %= ROTATIONS;
                    }
                } else {
                    if(pos == 0){
                        pos -= val;
                        pos = ROTATIONS + pos;
                    }else{
                        pos -= val;

                        if(pos < 0){
                            zeroHitCount++;
                            pos = ROTATIONS + pos;
                        }else if(pos == 0){
                            zeroHitCount++;
                        }
                    }
                }
            }
        }
        return zeroHitCount;
    }
}
