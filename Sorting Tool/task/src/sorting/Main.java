package sorting;

import java.util.*;

public class Main {

    public static void main(final String[] args) {
        if (args.length == 0) {
            return;
        }
        Map<String, String> mapArgs = new HashMap<>();
        for (int i = 0; i < args.length; ++i) {
            switch (args[i]) {
                case "-dataType":
                    if (i + 1 < args.length) {
                        mapArgs.put(args[i], args[i + 1]);
                    }
                    break;
                case "-sortIntegers":
                    mapArgs.put(args[i], "");
                    break;
            }
        }

        GenericsSorting genericsSorting = new GenericsSorting();
        Scanner scanner = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine()).append("\n");
        }

        for (var entry : mapArgs.entrySet()) {
            switch (entry.getKey()) {
                case "-dataType":
                    genericsSorting.setType(DataType.valueOf(entry.getValue().toUpperCase()));
                    break;
                case "-sortIntegers":
                    genericsSorting.setType(DataType.LONG);
                    break;
            }
        }

        genericsSorting.add(stringBuilder);

        if (mapArgs.containsKey("-dataType")
                && mapArgs.containsKey("-sortIntegers")) {
            genericsSorting.sort();
        } else {
            for (var entry : mapArgs.entrySet()) {
                switch (entry.getKey()) {
                    case "-dataType":
                        genericsSorting.show();
                        break;
                    case "-sortIntegers":
                        genericsSorting.sort();
                        break;
                }
            }
        }
    }
}
