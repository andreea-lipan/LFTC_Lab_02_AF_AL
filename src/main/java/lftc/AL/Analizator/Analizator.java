package lftc.AL.Analizator;

import lftc.AL.TAD.AtomHashMap;
import lftc.AL.text.PrettyFilePrinter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Analizator {
    Map<String, Integer> keywords;
    List<Atom> fip;
    AtomHashMap tsConsts;
    AtomHashMap tsIds;
    int lineContor;

    public Analizator() {
        keywords = new HashMap<>();
        fip = new ArrayList<>();
        tsIds = new AtomHashMap();
        tsConsts = new AtomHashMap();
        lineContor = 0;
        readCoduri();
    }

//    public void analize(String fileName, String fileId) {
//        // initialize
//        keywords = new HashMap<>();
//        fip = new ArrayList();
//        tsIds = new AtomHashMap();
//        tsConsts = new AtomHashMap();
//        lineContor = 0;
//
//        // Read Tabela cu keyword-uri si operatori
//        readCoduri();
//
//        // Read Program
//        try {
//            File file = new File(fileName);
//            Scanner scanner = new Scanner(file);
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//
//                // Process line
//                lineContor++;
//                analizeLine(line);
//            }
//
//        } catch (FileNotFoundException ex) {
//            System.out.println("File not found!");
//            ex.printStackTrace();
//        }
//
//        // Print output to file
//    }

    public void printToFile(String fileId) {
        PrettyFilePrinter prettyFilePrinter = new PrettyFilePrinter();
        prettyFilePrinter.print(fip, tsConsts, tsIds, fileId);
    }

    public void readCoduri() {
        try {
            File file = new File("C:\\Users\\deeal\\UNI\\LFTC\\LFTC_Lab_02_AF_AL\\src\\main\\resources\\operatori_cuv_cheie.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //System.out.println("line: " + line);
                String[] words = line.split(" ");
                keywords.put(words[0], Integer.parseInt(words[1]));
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            ex.printStackTrace();
        }
    }

    public void analizeLine(String word, String secvType) {
//        line = line.trim();
//        String[] words = line.split(" ");

        int contorFIP = 0;
        //for (String word : words) {
            System.out.println("word: " + word);
            if ((secvType.equals("id") || secvType.equals("symbol")) && (keywords.containsKey(word))) {
                System.out.println("keyword");
                fip.add(new Atom(word, keywords.get(word).toString(), ""));
                contorFIP++;
            } else if (secvType.equals("const")) {
                System.out.println("const");
                //add ts
                Integer hashIndex = tsConsts.put(word);
                //add fip
                fip.add(new Atom(word, String.valueOf(1), hashIndex.toString()));
            } else if (secvType.equals("id")) {
                System.out.println("id");
                //add ts
                Integer hashIndex = tsIds.put(word);
                //add fip
                fip.add(new Atom(word, String.valueOf(0), hashIndex.toString()));
            } else {
                System.out.println("smth else: " + word);
                throw new RuntimeException("Unknown word on line: " + lineContor);
            }
       // }
    }

}

