package sorting;

import java.util.*;

public class Main {

    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine()).append("\n");
        }
        GenericsSorting genericsSorting = new GenericsSorting();
        genericsSorting.add(stringBuilder);
        genericsSorting.show();
    }
}
