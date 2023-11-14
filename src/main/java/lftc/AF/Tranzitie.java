package lftc.AF;

import java.util.Objects;

public class Tranzitie {
    private String value;

    public Tranzitie(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tranzitie tranzitie = (Tranzitie) o;
        return value.equals(tranzitie.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
