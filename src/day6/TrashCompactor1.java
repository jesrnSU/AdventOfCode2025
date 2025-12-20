package day6;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import util.FileUtil;

public class TrashCompactor1 {
    private List<String> data;
    
    public TrashCompactor1(){
        try{
            data = FileUtil.readAllLines("src/day6/data.txt");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public BigInteger solve(){
        String[] row1num = data.get(0).trim().split("\\s+"); 
        String[] row2num = data.get(1).trim().split("\\s+"); 
        String[] row3num = data.get(2).trim().split("\\s+"); 
        String[] row4num = data.get(3).trim().split("\\s+"); 
        String[] row5ops = data.get(4).trim().split("\\s+"); 
        BigInteger total = BigInteger.ZERO;

        for(int i = 0; i < row5ops.length; i++){
            BigInteger colResult;
            if(row5ops[i].equals("+")){
              colResult = new BigInteger(row1num[i])
                    .add(new BigInteger(row2num[i]))
                    .add(new BigInteger(row3num[i]))
                    .add(new BigInteger(row4num[i]));
            } else {
                colResult = new BigInteger(row1num[i])
                    .multiply(new BigInteger(row2num[i]))
                    .multiply(new BigInteger(row3num[i]))
                    .multiply(new BigInteger(row4num[i]));
            }
            total = total.add(colResult);
        }
        return total;
    }
}
