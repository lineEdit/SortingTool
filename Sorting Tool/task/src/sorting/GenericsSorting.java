package sorting;

import java.util.*;

public class GenericsSorting {
    private DataType type;
    private List<String> list;
    private Deque<String> maxValue;
    private Map<String, Integer> maxCount;

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public void setType(String type) {
        try {
            this.type = DataType.valueOf(type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GenericsSorting() {
        this.type = DataType.WORD;
        this.list = new ArrayList<>();
        this.maxValue = new ArrayDeque<>();
        this.maxCount = new HashMap<>();
    }

    public void add(StringBuilder stringBuilder) {
        switch (type) {
            case LINE:
                for (String item : stringBuilder.toString().split("\n")) {
                    add(item);
                }
                break;
            case WORD:
            case LONG:
                for (String item : stringBuilder.toString().split("\\s+")) {
                    add(item);
                }
                break;
        }
    }

    public void add(String value) {
        list.add(value);
        if (maxValue.size() > 0) {
            switch (type) {
                case WORD:
                case LINE:
                    if (value.length() > maxValue.peek().length()) {
                        maxValue.push(value);
                    }
                    break;
                case LONG:
                    assert maxValue.peek() != null;
                    if (Long.parseLong(value) > Long.parseLong(maxValue.peek())) {
                        maxValue.push(value);
                    }
                    break;
            }
        } else {
            maxValue.push(value);
        }
        if (maxCount.containsKey(value)) {
            maxCount.replace(value, maxCount.get(value) + 1);
        } else {
            maxCount.put(value, 1);
        }
    }

    public void show() {
        String string = "";
        int size = list.size();
        int time = maxCount.get(maxValue.peek());
        int weight = 100 * time / size;
        switch (type) {
            case LINE:
                string = "Total lines: %d\nThe longest line:\n%s\n(%d + time(s), %d%%).";
                break;
            case WORD:
                string = "Total words: %d\nThe longest word: %s (%d + time(s), %d%%).";
                break;
            case LONG:
                string = "Total numbers: %d\nThe greatest number: %s (%d + time(s), %d%%).";
                break;
        }
        System.out.format(string, size, maxValue.peek(), time, weight);
    }
}
