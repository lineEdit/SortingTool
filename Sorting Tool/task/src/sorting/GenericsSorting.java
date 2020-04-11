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
        if (list.size() > 0) {
            if (compare(value, maxValue.peek()) >= 0) {
                list.add(value);
                maxValue.push(value);
            } else if (compare(value, list.get(0)) < 0) {
                list.add(0, value);
            } else {
                if (compare(value, maxValue.peekFirst()) == compare(value, maxValue.peekLast())) {
                    int i = list.size() / 2;
                    long diff = compare(value, list.get(i));
                    long oldDiff = Math.abs(diff);
                    while (diff != 0) {
                        if (oldDiff > Math.abs(diff)) break;
                        if (diff > 0) ++i;
                        if (diff < 0) --i;
                        diff = compare(value, list.get(i));
                        oldDiff = Math.abs(diff);
                    }
                    list.add(i, value);
                } else if (compare(value, maxValue.peekFirst()) > 0) {
                    for (int i = list.size() - 1; i >= 0; --i) {
                        if (compare(value, list.get(i)) >= 0) {
                            list.add(i, value);
                            break;
                        }
                    }
                } else {
                    for (int i = 0; i < list.size(); ++i) {
                        if (compare(value, list.get(i)) <= 0) {
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

    private int compare(String first, String second) {
        switch (type) {
            case LINE:
                return first.length() - second.length();
            case WORD:
                if (first.length() == second.length()) {
//                    return Character.compare(first.charAt(0), second.charAt(0));
                    return (int) first.charAt(0) - (int) second.charAt(0);
                }
                return first.length() - second.length();
            case LONG:
                return (int) (Long.parseLong(first) - Long.parseLong(second));
        }
        return 0;
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
                string = "Total lines: %d.\nSorted data:";
                break;
            case WORD:
                Collections.sort(list);
                string = "Total numbers: %d.\nSorted data:";
                break;
            case LONG:
                string = "Total numbers: %d.\nSorted data:";
                break;
        }
        System.out.format(string, size);
        for (String item : list) {
            System.out.print(" " + item);
        }
        System.out.println();
    }
}
