package lftc.AL.Analizator;

public class Atom {
    private String nume;
    private String cod_atom;
    private String cod_ts;


    public Atom(String nume, String cod_atom, String cod_ts) {
        this.nume = nume;
        this.cod_atom = cod_atom;
        this.cod_ts = cod_ts;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCod_atom() {
        return cod_atom;
    }

    public void setCod_atom(String cod_atom) {
        this.cod_atom = cod_atom;
    }

    public String getCod_ts() {
        return cod_ts;
    }

    public void setCod_ts(String cod_ts) {
        this.cod_ts = cod_ts;
    }


    @Override
    public String toString() {
        return nume + "  |  " + cod_atom + "  |  " + cod_ts;
    }
}
