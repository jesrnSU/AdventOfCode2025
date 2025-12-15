package day5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import util.FileUtil;

public class CafeteriaPart2 {

    public long solve() {
        List<String> input = null;
        try {
            input = FileUtil.readAllLines("src/day5/data.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Iterator<String> it = input.iterator();
        List<Interval> intervals = new ArrayList<>();
        String s = null;

        while (it.hasNext()) {
            if ((s = it.next()).isEmpty()) {
                break;
            }
            String[] splitString = s.split("-");
            intervals.add(new Interval(Long.parseLong(splitString[0]), Long.parseLong(splitString[1])));
        }

        Collections.sort(intervals, Comparator.comparingLong(Interval::getStart));

        List<Interval> merged = new ArrayList<>();
        for (Interval current : intervals) {
            if (merged.isEmpty()) {
                merged.add(new Interval(current.getStart(), current.getEnd()));
            } else {
                Interval last = merged.get(merged.size() - 1);
                if (current.getStart() <= last.getEnd() + 1) {
                    last.setEnd(Math.max(last.getEnd(), current.getEnd()));
                } else {
                    merged.add(new Interval(current.getStart(), current.getEnd()));
                }
            }
        }

        long result = 0;
        for (Interval i2 : merged) {
            result += i2.getRange();
        }
        return result;
    }
}
