package lftc.AF;

import java.util.*;

/**
 * Automat finit
 */
public class Automat {
    public String type;
    private boolean determinist = true;
    private Map<Stare, Map<Tranzitie, Stare>> automat;
    private List<Tranzition> tranzitiiNotDet;
    private Stare stareInitiala;
    private Set<Stare> stari;
    private Set<Stare> stariFinale;
    private Set<Tranzitie> alfabet;
    private String longestSecv = "";

    /* CHECK IF SECV ACCEPTATA */
    public String checkSecventa(String secv) {
        if (!determinist) {
            System.out.println("Automatul nu e determinist");
            return null;
        }

        check(secv);

        System.out.println("Longest secv: " + longestSecv);

        if (longestSecv.equals("") && stariFinale.contains(stareInitiala)) {
            longestSecv = "epsilon";
        }
        if (longestSecv.equals("") && !stariFinale.contains(stareInitiala)) {
            longestSecv = "no secv found";
        }
        //System.out.println("Longest secv: " + longestSecv);

        return longestSecv;

    }

    public void check(String secv) {
        longestSecv = "";
        if (secv.startsWith("0b")) {
            System.out.println("HERE");
        }

        Stare stareCurenta = this.stareInitiala;
        for (int i = 0; i < secv.length(); i++) {
            Tranzitie tranzitie = new Tranzitie(String.valueOf(secv.charAt(i)));
            if (this.automat.get(stareCurenta).containsKey(tranzitie)) {
                stareCurenta = this.automat.get(stareCurenta).get(tranzitie);
            } else {
                return;
            }
            System.out.println("trecut: " + tranzitie.getValue());
            // save only if st finala
            if (stariFinale.contains(stareCurenta)) {
                System.out.println("final: " + stareCurenta.getValue());
                System.out.println("secv aut: " + secv);
                System.out.println("i" + i);
                longestSecv = secv.substring(0, i + 1);
                System.out.println("longest: " + longestSecv);
            }
        }

    }

    public String getLongestSecv() {
        return longestSecv;
    }

    /* CONSTRUCTOR */
    public Automat(String type) {
        this.type = type;
        this.tranzitiiNotDet = new ArrayList<>();
        this.automat = new HashMap<>();
        this.stari = new HashSet<>();
        this.stariFinale = new HashSet<>();
        this.alfabet = new HashSet<>();
    }

    /* SETTER & GETTERS */
    public void setStareInitiala(Stare stareInitiala) {
        this.stareInitiala = stareInitiala;
    }
    public void addStare(Stare stare) {
        if (!this.automat.containsKey(stare)) {
            this.automat.put(stare, new HashMap<>());
        }
        this.stari.add(stare);
    }
    public void addStareFinala(Stare stare) {
        this.stariFinale.add(stare);
    }
    public void addToAlfabet(Tranzitie tranzitie) {
        this.alfabet.add(tranzitie);
    }
    public void addTranzitieAutomat(Stare stare1, Tranzitie tranzitie, Stare stare2) {
        // check if tranzitie is already there
        // if it is, automat is nedet, end program
        if (automat.get(stare1).containsKey(tranzitie)) {
            //throw new RuntimeException("Automatul nu este determinist");
            System.out.println("Automatul nu e determinist");
            determinist = false;
        }
        tranzitiiNotDet.add(new Tranzition(stare1, tranzitie, stare2));
        this.automat.get(stare1).put(tranzitie, stare2);
    }

    public Stare getStareInitiala() {
        return stareInitiala;
    }
    public Set<Stare> getStari() {
        return stari;
    }
    public Set<Stare> getStariFinale() {
        return stariFinale;
    }
    public Set<Tranzitie> getAlfabet() {
        return alfabet;
    }
    public Map<Stare, Map<Tranzitie, Stare>> getAutomat() {
        return automat;
    }


    /* PRINT */
    public void printStari() {
        System.out.println("Stari: ");
        for (Stare stare : this.stari) {
            System.out.print(stare.getValue() + " ");
        }
        System.out.println();
    }
    public void printStariFinale() {
        System.out.println("Stari finale: ");
        for (Stare stare : this.stariFinale) {
            System.out.print(stare.getValue() + " ");
        }
        System.out.println();
    }
    public void printAlfabet() {
        System.out.println("Alfabet: ");
        for (Tranzitie tranzitie : this.alfabet) {
            System.out.print(tranzitie.getValue() + " ");
        }
        System.out.println();
    }
    public void printTranzitii() {
        System.out.println("Tranzitii: ");
        if (!determinist) {
            for (Tranzition tranzition : tranzitiiNotDet) {
                System.out.println(tranzition.getStare1().getValue() + " " + tranzition.getTranzitie().getValue() + " " + tranzition.getStare2().getValue());
            }
        } else {
            for (Stare stare : this.automat.keySet()) {
                for (Tranzitie tranzitie : this.automat.get(stare).keySet()) {
                    System.out.println(stare.getValue() + " " + tranzitie.getValue() + " " + this.automat.get(stare).get(tranzitie).getValue());
                }
            }
        }

    }
}
