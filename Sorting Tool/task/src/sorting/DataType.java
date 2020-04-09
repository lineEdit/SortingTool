package sorting;

public enum DataType {
    LINE("line"),
    WORD("word"),
    LONG("long");

    private final String type;

    DataType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
