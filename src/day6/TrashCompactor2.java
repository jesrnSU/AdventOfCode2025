package day6;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import util.FileUtil;

public class TrashCompactor2 {
    private List<String> data;
    private int length;
    public TrashCompactor2() {
        try {
            data = FileUtil.readAllLines("src/day6/data.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        length = data.get(0).length();
    }

    public BigInteger solve() {
        String[] row5ops = data.get(4).trim().split("\\s+");
        BigInteger total = BigInteger.ZERO;
        int currentoperator = row5ops.length - 1;
        int currentIndex = data.get(0).length() - 1;

        for (int i = 0; i < row5ops.length; i++) {
            ArrayList<String> problem = new ArrayList<>();
            long colResult = 0;
            String col = "";
            do {
                col = new StringBuilder().append(data.get(0).charAt(currentIndex)).append(data.get(1).charAt(currentIndex)).append(data.get(2).charAt(currentIndex)).append(data.get(3).charAt(currentIndex)).toString();
                problem.add(col);
                currentIndex--;
            } while (!col.isBlank() && currentIndex >= 0);

            if(currentIndex != -1)
                problem.removeLast();

            boolean isAdd = false;
            if(row5ops[currentoperator].equals("+")){
                isAdd = true;
                System.out.println("+");
            }else{
                colResult = 1;
            }

            for(String s : problem){
                System.out.println(s);
                s = s.trim();
                if(isAdd){
                    colResult += Long.parseLong(s);
                }else{
                    colResult *= Long.parseLong(s);
                } 
            }
            total = total.add(BigInteger.valueOf(colResult));
            currentoperator--;
        }
        return total;
    }
}
