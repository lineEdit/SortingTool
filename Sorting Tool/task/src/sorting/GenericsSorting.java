package sorting;

import java.util.*;
import java.util.stream.Stream;

public class GenericsSorting {
    private DataType type;
    private DataSorting sorting;
    private List<String> list;
    private Map<String, Integer> maxCount;

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public void setType(String type) {
        if (type == null) {
            this.type = DataType.WORD;
        } else {
            try {
                this.type = DataType.valueOf(type.toUpperCase());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public DataSorting getSorting() {
        return sorting;
    }

    public void setSorting(DataSorting sorting) {
        this.sorting = sorting;
    }

    public void setSorting(String sorting) {
        if (sorting == null) {
            this.sorting = DataSorting.NATURAL;
        } else {
            try {
                this.sorting = DataSorting.valueOf(sorting.toUpperCase());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public GenericsSorting() {
        this.type = DataType.WORD;
        this.list = new ArrayList<>();
        this.maxCount = new TreeMap<>();
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
        if (maxCount.containsKey(value)) {
            maxCount.replace(value, maxCount.get(value) + 1);
        } else {
            maxCount.put(value, 1);
        }
    }

    public void sort() {
        String string = "";
        int size = list.size();
        switch (type) {
            case LINE:
                Collections.sort(list);
                string = "Total lines: %d.\n";
                break;
            case WORD:
                Collections.sort(list);
                string = "Total words: %d.\n";
                break;
            case LONG:
                List<Long> array = new ArrayList<>();
                for (String item : list) {
                    array.add(Long.parseLong(item));
                }
                Collections.sort(array);
                list.clear();
                for (Long item : array) {
                    list.add(String.valueOf(item));
                }
                string = "Total numbers: %d.\n";
                break;
        }
        switch (sorting) {
            case NATURAL:
                System.out.format(string + "Sorted data:", size);
                for (String item : list) {
                    System.out.print(" " + item);
                }
                break;
            case BYCOUNT:
                Map<String, Integer> mapSort = new LinkedHashMap<>();
                for (String item : list) {
                    mapSort.put(item, maxCount.get(item));
                }

                LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
                mapSort.entrySet().stream().sorted(Map.Entry.comparingByValue())
                        .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

                System.out.format(string, size);
                for (var item : reverseSortedMap.entrySet()) {
                    int weight = 100 * item.getValue() / size;
                    System.out.println(item.getKey() + ": " + item.getValue() + " time(s), " + weight + "%");
                }
                break;
        }
    }
}
