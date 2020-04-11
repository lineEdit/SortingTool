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

    private long parseValue(String value) {
        switch (type) {
            case LINE:
            case WORD:
                return value.length();
            case LONG:
                return Long.parseLong(value);
        }
        return 0L;
    }

    public void add(String value) {
        long input = parseValue(value);

        if (list.size() > 0) {
            if (isMax(input, value)) {
                list.add(value);
            } else if (input <= parseValue(list.get(0))) {
                list.add(0, value);
            } else {
                long abs = Math.abs(input - parseValue(maxValue.getFirst()))
                        - Math.abs(input - parseValue(maxValue.getLast()));
                if(abs == 0) {
                    int i = list.size() / 2;
                    long diff = input - parseValue(list.get(i));
                    long oldDiff = Math.abs(diff);
                    while (diff != 0) {
                        if (oldDiff > Math.abs(diff)) break;
                        if (diff > 0) ++i;
                        if (diff < 0) --i;
                        diff = input - parseValue(list.get(i));
                        oldDiff = Math.abs(diff);
                    }
                    list.add(i, value);
                } else if (abs < 0) {
                    for (int i = 0; i < list.size(); ++i) {
                        if (input <= parseValue(list.get(i))) {
                            list.add(i, value);
                            break;
                        }
                    }
                } else {
                    for (int i = list.size() - 1; i >= 0; --i) {
                        if (input >= parseValue(list.get(i))) {
                            list.add(i, value);
                            break;
                        }
                    }
                }
            }
        } else {
            list.add(value);
            maxValue.add(value);
        }
        if (maxCount.containsKey(value)) {
            maxCount.replace(value, maxCount.get(value) + 1);
        } else {
            maxCount.put(value, 1);
        }
    }

    private boolean isMax(long input, String value) {
        if (maxValue.size() > 0) {
            if (input >= parseValue(maxValue.peek())) {
                maxValue.push(value);
                return true;
            }
        } else {
            maxValue.push(value);
            return true;
        }
        return false;
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

    public void sort() {
        String string = "";
        int size = list.size();
        switch (type) {
            case LINE:
                string = "Total lines: %d.\nSorted data: ";
                break;
            case WORD:
                string = "\nTotal words: %d.\nSorted data: ";
                break;
            case LONG:
                string = "Total numbers: %d.\nSorted data: ";
                break;
        }
        System.out.format(string, size);
        for (String item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}
