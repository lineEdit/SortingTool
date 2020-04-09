package sorting;

import java.util.Deque;
import java.util.List;
import java.util.Map;

public class GenericsSorting {
    private List<Long> tList;
    private Deque<Long> tMaxValue;
    private Map<Long, Integer> tMaxCount;

    public void add(Long value) {
        tList.add(value);
        if (tMaxValue.size() > 0) {
            if (value > tMaxValue.peek()) {
                tMaxValue.push(value);
            }
        } else {
            tMaxValue.push(value);
        }
        if (tMaxCount.containsKey(value)) {
            tMaxCount.replace(value, tMaxCount.get(value) + 1);
        } else {
            tMaxCount.put(value, 1);
        }
    }

    public void show() {
        System.out.println("Total numbers: " + tList.size());
        System.out.println("The greatest number: "
                + tMaxValue.peek()
                + "(" + tMaxCount.get(tMaxValue.peek()) + " time(s)).");
    }
}
