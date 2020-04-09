package sorting;

import java.util.*;

public class GenericsSorting {
    enum Type {
        wordType,
        lineType,
        longType
    }
    Type type;

    private List<Long> tList;
    private Deque<Long> tMaxValue;
    private Map<Long, Integer> tMaxCount;

    public GenericsSorting() {
        this.type = Type.wordType;
        this.tList = new ArrayList<>();
        this.tMaxValue = new ArrayDeque<>();
        this.tMaxCount = new HashMap<>();
    }

    public void add(StringBuilder stringBuilder) {
        for (String item : stringBuilder.toString().split("\\s+")) {
            try {
                add(Long.parseLong(item));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

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
