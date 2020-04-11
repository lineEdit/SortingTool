package sorting;

import java.util.*;

public class GenericsSorting {
    public enum DataType {
        LINE,
        WORD,
        LONG
    }
    public enum DataSorting {
        NATURAL,
        BYCOUNT
    }
    public enum DataInput {
        SCANNER,
        INPUTFILE
    }
    public enum DataOutput {
        SCANNER,
        OUTPUTFILE
    }

    private DataType type;
    private DataSorting sorting;
    private DataInput input;
    private DataOutput output;
    private final List<String> list;
    private final Map<String, Integer> maxCount;

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public void setType(String type) {
        try {
            this.type = DataType.valueOf(type.toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DataSorting getSorting() {
        return sorting;
    }

    public void setSorting(DataSorting sorting) {
        this.sorting = sorting;
    }

    public void setSorting(String sorting) {
        try {
            this.sorting = DataSorting.valueOf(sorting.toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DataInput getInput() {
        return input;
    }

    public void setInput(DataInput input) {
        this.input = input;
    }

    public void setInput(String input) {
        try {
            this.input = DataInput.valueOf(input.toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DataOutput getOutput() {
        return output;
    }

    public void setOutput(DataOutput output) {
        this.output = output;
    }

    public void setOutput(String output) {
        try {
            this.output = DataOutput.valueOf(output.toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GenericsSorting() {
        this.type = DataType.WORD;
        this.sorting = DataSorting.NATURAL;
        this.input = DataInput.SCANNER;
        this.output = DataOutput.SCANNER;
        this.list = new ArrayList<>();
        this.maxCount = new TreeMap<>();
    }

    public void run() {
//        Scanner scanner = new Scanner(System.in);
//        StringBuilder stringBuilder = new StringBuilder();
//        while (scanner.hasNextLine()) {
//            stringBuilder.append(scanner.nextLine()).append("\n");
//        }
//        add(stringBuilder);
    }

    private void add(StringBuilder stringBuilder) {
        switch (type) {
            case LINE:
                for (String item : stringBuilder.toString().split("\n")) {
                    add(item);
                }
                break;
            case WORD:
                for (String item : stringBuilder.toString().split("\\s+")) {
                    add(item);
                }
                break;
            case LONG:
                for (String item : stringBuilder.toString().split("\\s+")) {
                    if (item.matches("-?\\d+")) {
                        add(item);
                    } else {
                        System.out.println("\"" + item + "\" isn't a long. It's skipped.");
                    }
                }
                break;
        }
    }

    private void add(String value) {
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
