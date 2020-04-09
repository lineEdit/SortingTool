package sorting;

import java.util.*;

public class Main {

    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        GenericsSorting genericsSorting = new GenericsSorting();
        while (scanner.hasNextLong()) {
            genericsSorting.add(scanner.nextLong());
        }
        genericsSorting.show();
    }
}
