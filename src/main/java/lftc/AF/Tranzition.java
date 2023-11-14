package lftc.AF;

public class Tranzition {
    private Stare stare1;
    private Tranzitie tranzitie;
    private Stare stare2;

    public Tranzition(Stare stare1, Tranzitie tranzitie, Stare stare2) {
        this.stare1 = stare1;
        this.tranzitie = tranzitie;
        this.stare2 = stare2;
    }

    public Stare getStare1() {
        return stare1;
    }

    public void setStare1(Stare stare1) {
        this.stare1 = stare1;
    }

    public Tranzitie getTranzitie() {
        return tranzitie;
    }

    public void setTranzitie(Tranzitie tranzitie) {
        this.tranzitie = tranzitie;
    }

    public Stare getStare2() {
        return stare2;
    }

    public void setStare2(Stare stare2) {
        this.stare2 = stare2;
    }
}
