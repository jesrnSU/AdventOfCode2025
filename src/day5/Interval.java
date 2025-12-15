package day5;

import java.util.Iterator;

public class Interval {

    private long start;
    private long end;

    public Interval(long start, long end){
        this.start = start;
        this.end = end;
    }

    public long getRange(){
        return end - start + 1;
    }

    public boolean checkInInterval(long number){
        return (number >= start && number <= end); 
    }

    public long getEnd() {
        return end;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public void setEnd(long end) {
        this.end = end;
    }
}
