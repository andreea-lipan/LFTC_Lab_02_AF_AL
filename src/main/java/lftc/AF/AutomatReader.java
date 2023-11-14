package lftc.AF;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AutomatReader {

    private Scanner scanner;

    public void readFromFile(Automat automat, String path) {
        try {
            scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // scanner = new Scanner(AutomatReader.class.getClassLoader().getResourceAsStream("/automat.txt"));
        readAlfabet(automat);
        readStari(automat);
        readStareInitiala(automat);
        readStariFinale(automat);
        readTranzitii(automat);
    }
    public void readFromKeyboard(Automat automat){
        scanner = new Scanner(System.in);
        readAlfabet(automat);
        readStari(automat);
        readStareInitiala(automat);
        readStariFinale(automat);
        readTranzitii(automat);
    }

    //todo ceal mai lunga secventa acceptata sa fie epsilon if start init is final

    public void readAlfabet(Automat automat) {
        System.out.println("Introduceti alfabetul: a b c ...");
        String input = scanner.nextLine();
        String[] alfabet = input.split(" ");
        for (String s : alfabet) {
            automat.addToAlfabet(new Tranzitie(s));
        }
    }
    public void readStari(Automat automat) {
        System.out.println("Introduceti starile: a b c ...");
        String input = scanner.nextLine();
        String[] stari = input.split(" ");
        for (String s : stari) {
            automat.addStare(new Stare(s));
        }

    }
    public void readStareInitiala(Automat automat) {
        System.out.println("Introduceti starea initiala: a ");
        String input = scanner.nextLine();
        while (!automat.getStari().contains(new Stare(input))) {
            System.out.println("Starea initiala nu este in multimea starilor");
            input = scanner.nextLine();
        }
        automat.setStareInitiala(new Stare(input));
    }
    public void readStariFinale(Automat automat) {
        System.out.println("Introduceti starile finale: a b c ...");
        String input = scanner.nextLine();
        String[] stari = input.split(" ");
        for (String s : stari) {
            automat.addStareFinala(new Stare(s));
        }
    }

    public void readTranzitii(Automat automat) {
        System.out.println("Introduceti tranzitiile: a b c enter d e f ...");
        String input;
        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            if (input.equals("done")) {
                break;
            }
            String[] tranzitie = input.split(" ");

            // verific daca starile exista in multimea starilor
            if (!automat.getStari().contains(new Stare(tranzitie[0]))) {
                System.out.println("Starea " + tranzitie[0] + " nu este in multimea starilor");
                continue;
            }
            if (!automat.getStari().contains(new Stare(tranzitie[2]))) {
                System.out.println("Starea " + tranzitie[2] + " nu este in multimea starilor");
                continue;
            }
            // verific daca tranzitia exista in alfabet
            if (!automat.getAlfabet().contains(new Tranzitie(tranzitie[1]))) {
                System.out.println("Tranzitia " + tranzitie[1] + " nu este in alfabet");
                continue;
            }
            //adaug tranzitia in automat
            automat.addTranzitieAutomat(new Stare(tranzitie[0]), new Tranzitie(tranzitie[1]), new Stare(tranzitie[2]));

        }
    }

    public String readSecv() {
        System.out.println("Introduceti secventa: abab");
        Scanner scanner2 = new Scanner(System.in);
        String input = scanner2.nextLine();
        return input;
    }
}
