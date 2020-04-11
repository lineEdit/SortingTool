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
        for (var entry : mapArgs.entrySet()) {
            switch (entry.getKey()) {
                case "-dataType":
                    if (entry.getValue() == null) {
                        System.out.println("No data type defined!");
                        return;
                    }
                    genericsSorting.setType(entry.getValue());
                    break;
                case "-sortingType":
                    if (entry.getValue() == null) {
                        System.out.println("No sorting type defined!");
                        return;
                    }
                    genericsSorting.setSorting(entry.getValue());
                    break;
                case "-inputFile":
                    if (entry.getValue() == null) {
                        System.out.println("No inputFile name defined!");
                        return;
                    }
                    genericsSorting.setInput(GenericsSorting.DataInput.INPUTFILE);
                    genericsSorting.setInputName(entry.getValue());
                    break;
                case "-outputFile":
                    if (entry.getValue() == null) {
                        System.out.println("No inputFile name defined!");
                        return;
                    }
                    genericsSorting.setOutput(GenericsSorting.DataOutput.OUTPUTFILE);
                    genericsSorting.setOutputName(entry.getValue());
                    break;
                default:
                    System.out.println("\"" + entry.getKey() + "\" isn't a valid parameter. It's skipped.");
            }
        }
        genericsSorting.run();
        genericsSorting.sort();
    }
}
