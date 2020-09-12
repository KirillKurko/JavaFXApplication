package code;

public enum Type {
    NAME("Name"),
    SURNAME("Surname"),
    FATHERLAND("Fatherland"),
    SORTED_SURNAME("Processed");

    private String value;
    Type(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
