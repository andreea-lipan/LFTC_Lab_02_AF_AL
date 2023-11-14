package lftc;


import lftc.AF.Automat;
import lftc.AF.AutomatReader;
import lftc.AL.Analizator.Analizator;
import lftc.AL.Analizator.Atom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void generateAF_identificatori() throws IOException {

        FileWriter myWriter = new FileWriter("C:\\Users\\deeal\\UNI\\LFTC\\LFTC_Lab_02_AF_AL\\src\\main\\resources\\AF_identificatori");

        int nrStari = 8;
        
        // alfabetul
        for (char c = 'a'; c <= 'z'; c++) {
            myWriter.write(c + " ");
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            myWriter.write(c + " ");
        }
        for (int c = 1; c <= 9; c++) {
            myWriter.write(c + " ");
        }
        myWriter.write("\n");

        // toate starile
        for (int i = 0; i < nrStari; i++) {
            myWriter.write("s" + i + " ");
        }
        myWriter.write("\n");

        // st init
        myWriter.write("s0\n");

        // st finale
        for (int i = 1; i < nrStari; i++) {
            myWriter.write("s" + i + " ");
        }
        myWriter.write("\n");
        
        // tranzitiile
        for (int i = 1; i < nrStari; i++) {
            if (i == 1) {
                for (char c = 'a'; c <= 'z'; c++) {
                    myWriter.write("s0" + " " + c + " " + "s1" + "\n");
                }
                for (char c = 'A'; c <= 'Z'; c++) {
                    myWriter.write("s0" + " " + c + " " + "s1" + "\n");
                }
            } else {
                for (char c = 'a'; c <= 'z'; c++) {
                    myWriter.write("s" + (i - 1) + " " + c + " " + "s" + i + "\n");
                }
                for (char c = 'A'; c <= 'Z'; c++) {
                    myWriter.write("s" + (i - 1) + " " + c + " " + "s" + i + "\n");
                }
                for (int c = 1; c <= 9; c++) {
                    myWriter.write("s" + (i - 1) + " " + c + " " + "s" + i + "\n");
                }
            }
        }

        myWriter.write("done");
        myWriter.close();
    }
    public static void main(String[] args) {
//        try {
//            generateAF_identificatori();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // id for the example files, can be 1 / 2 / 3
        String fileID = "1";
        String fileName = "programs\\0" + fileID + "\\program_0"+ fileID +".txt";

        Automat automatCONSTIntregi = new Automat("const");
        Automat automatCONSTReale = new Automat("const");
        Automat automatID = new Automat("id");
        Automat automatSymbols = new Automat("symbol");

        // read automate
        AutomatReader automatReader = new AutomatReader();
        automatReader.readFromFile(automatCONSTIntregi, "C:\\Users\\deeal\\UNI\\LFTC\\LFTC_Lab_02_AF_AL\\src\\main\\resources\\AF_constanteIntregi");
        automatReader.readFromFile(automatCONSTReale, "C:\\Users\\deeal\\UNI\\LFTC\\LFTC_Lab_02_AF_AL\\src\\main\\resources\\AF_constanteReale");
        automatReader.readFromFile(automatID, "C:\\Users\\deeal\\UNI\\LFTC\\LFTC_Lab_02_AF_AL\\src\\main\\resources\\AF_identificatori");
        automatReader.readFromFile(automatSymbols, "C:\\Users\\deeal\\UNI\\LFTC\\LFTC_Lab_02_AF_AL\\src\\main\\resources\\AF_symbols");

        // save automate in a list to easily iterate over them
        List<Automat> automatList = List.of(automatCONSTReale, automatCONSTIntregi, automatID, automatSymbols);


        Analizator analizator = new Analizator();

        // Read Program
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            int contorLinii = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Process line
                String longestSequence = "";
                String secvType = "";
                line = line.trim();

                while (line.length() > 0) {
                    System.out.println("line: " + line);
                    line = line.trim();
                    for (Automat automat : automatList) {
                        // check which automat accepts the longest sequence
                        String sequence = automat.checkSecventa(line);
                        if (!sequence.equals("no secv found")) {
                            if ((sequence.length() > longestSequence.length())) {
                                longestSequence = sequence;
                                secvType = automat.type;
                            }
                        }
                    }
                    //System.out.println("longestSequence: " + longestSequence + "  type : " + secvType);
                    if (longestSequence.isEmpty()) {
                        // its something we dont accept
                        throw new RuntimeException("Bad input on the line: " + contorLinii);
                    } else  {
                        line = line.substring(longestSequence.length()); // remove the subsecventa from the line
                    }
                    analizator.analizeLine(longestSequence, secvType);
                    longestSequence = "";
                }

                contorLinii++;
            }



            // print the fip & ts tabele
            analizator.printToFile(fileID);

        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            ex.printStackTrace();
        }


    }
}