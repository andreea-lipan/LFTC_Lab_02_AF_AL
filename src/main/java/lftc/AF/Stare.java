package lftc.AF;

import java.util.Objects;

public class Stare {
    private String value;

    public Stare(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Stare) {
            return this.value.equals(((Stare) obj).getValue());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "{" + value + "}";
    }
}
