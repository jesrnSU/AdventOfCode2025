package day5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import util.FileUtil;

public class CafeteriaPart1 {

    public int solve(){
        List<String> input = null;
        try{
            input = FileUtil.readAllLines("src/day5/data.txt");
        }catch(IOException e){
            e.printStackTrace();
        }

        Iterator<String> it = input.iterator();
        boolean stopReadingIntervalValues = false;
        List<Interval> intervals = new ArrayList<>();
        String s = null;
        int count = 0;

        while (it.hasNext()) {
            if((s = it.next()).isEmpty()){
                stopReadingIntervalValues = true;
                s = it.next();
            }

            if(!stopReadingIntervalValues){
                String[] splitString = s.split("-");
                Interval inter = new Interval(Long.parseLong(splitString[0]), Long.parseLong(splitString[1])); 
                intervals.add(inter);
            }

            if(stopReadingIntervalValues){
                for(Interval i : intervals){
                    if(i.checkInInterval(Long.parseLong(s))){
                        count++;
                        break;
                    }
                }
            }
        }
        return count;
    }
}
