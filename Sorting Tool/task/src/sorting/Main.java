package sorting;

import java.util.*;

public class Main {

    public static void main(final String[] args) {
        if (args.length == 0) {
            return;
        }

        Map<String, String> mapArgs = new HashMap<>();
        for (int i = 0; i < args.length; ++i) {
            if (i + 1 < args.length) {
                mapArgs.put(args[i], args[i + 1]);
                ++i;
            } else {
                mapArgs.put(args[i], null);
            }
        }

        GenericsSorting genericsSorting = new GenericsSorting();
        Scanner scanner = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine()).append("\n");
        }

        genericsSorting.setSorting(DataSorting.NATURAL);
        genericsSorting.setType(DataType.WORD);

        for (var entry : mapArgs.entrySet()) {
            switch (entry.getKey()) {
                case "-dataType":
                    genericsSorting.setType(entry.getValue());
                    break;
                case "-sortingType":
                    genericsSorting.setSorting(entry.getValue());
                    break;
            }
        }
//        System.out.println(genericsSorting.getType());
//        System.out.println(genericsSorting.getSorting());
        genericsSorting.add(stringBuilder);
        genericsSorting.sort();
    }
}
