package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JoltagePart1 {
    private static final String DAY3INPUT = "src/day3/data.txt";
    private static final String DAY3TESTINPUT = "src/day3/datatest.txt";

    public int solve(){
        int count = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(DAY3INPUT))){
            String line;

            while((line = br.readLine()) != null){
                char largest = '0', secondLargest = '0';
                int length = line.length();
                for(int i = 0; i < length; i++){
                    char currentChar = line.charAt(i);

                    if(currentChar > largest){
                        if((i + 1) < length){
                            largest = currentChar;
                            secondLargest = '0';
                        }
                        else{
                            if(currentChar > secondLargest)
                                secondLargest = currentChar;
                        }
                    }else if(currentChar > secondLargest){
                        secondLargest = currentChar;
                    }
                }
                int tmp = Integer.parseInt(new StringBuilder().append(largest).append(secondLargest).toString());
                System.out.println("Adding : " + tmp);
                count += tmp;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return count;
    }
}
