package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JoltagePart2 {

    private static final String DAY3INPUT = "src/day3/data.txt";
    private static final String DAY3TESTINPUT = "src/day3/datatest.txt";

    private static final int NRLENGTH = 12;

    public JoltagePart2() {
    }

    public long solve() throws IOException {
        String line;
        long count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(DAY3INPUT))) {

            while ((line = reader.readLine()) != null) {
                char[] largest = new char[NRLENGTH];
                int lineLength = line.length();
                int indexHelper = 0;

                for (int i = 0; i < lineLength; i++) {
                    char currentChar = line.charAt(i);
                    boolean reset = false;

                    if (i > (lineLength - NRLENGTH)) {
                        indexHelper++;
                    }

                    for (int j = indexHelper; j < NRLENGTH; j++) {
                        if (!reset && currentChar > largest[j]) {
                            largest[j] = currentChar;
                            reset = true;
                        } else if (reset) {
                            largest[j] = '0';
                        }
                    }
                }
                count += Long.parseLong(String.valueOf(largest));
            }
            return count;
        }
    }
}
