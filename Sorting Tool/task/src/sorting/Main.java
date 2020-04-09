package sorting;

import java.util.*;

public class Main {

    public static void main(final String[] args) {
        if (args.length == 0) {
            return;
        }
        GenericsSorting genericsSorting = new GenericsSorting();
        if (args.length == 2 && args[0].contains("-dataType")) {
            genericsSorting.setType(args[1].toUpperCase());
        }

        Scanner scanner = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine()).append("\n");
        }
        genericsSorting.add(stringBuilder);
        genericsSorting.show();
    }
}
