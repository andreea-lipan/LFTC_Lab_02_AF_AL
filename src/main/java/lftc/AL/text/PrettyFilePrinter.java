package lftc.AL.text;

import lftc.AL.TAD.AtomHashMap;
import lftc.AL.TAD.Node;
import lftc.AL.Analizator.Atom;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PrettyFilePrinter {

    private String FIP = "";
    private String TS_CONST = "";
    private String TS_ID = "";

    public void print(List<Atom> fip, AtomHashMap ts_const, AtomHashMap ts_ids, String fileId) {
        setFilePaths(fileId);
        printFIB(fip);
        printTS_CONST(ts_const);
        printTS_ID(ts_ids);
    }

    public void printFIB(List<Atom> fip) {
        try {
            FileWriter myWriter = new FileWriter(FIP);
            myWriter.write("  FIP:\n");
            myWriter.write(String.format("|%-10s|%8s|%8s|\n", "nume", "cod_atom", "cod_ts"));
            System.out.println("Successfully wrote to the file.");
            for (Atom atom : fip) {
                myWriter.write(String.format("|%-10s|%3s%2s%3s|%3s%2s%3s|\n", atom.getNume(), "", atom.getCod_atom(), "", "", atom.getCod_ts(), ""));
                myWriter.write("------------------------------\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void printTS_CONST(AtomHashMap ts_const) {
        System.out.println("TS_CONST: " + ts_const.table.length);

        try {
            FileWriter tsConstWriter = new FileWriter(TS_CONST);
            tsConstWriter.write("\n");
            tsConstWriter.write("  TS_CONST:\n");
            tsConstWriter.write("\n");
            tsConstWriter.write(String.format("|%-8s|%8s|\n", "nume", "cod_ts"));
            System.out.println("Successfully wrote to the file.");
            tsConstWriter.write("-------------------\n");

            for (Node<String, Integer> atom : ts_const.table) {
                if (atom != null) {
                    tsConstWriter.write(String.format("|%2s%4s%2s|%3s%2s%3s|\n", "", atom.key, "", "", atom.value, ""));
                    tsConstWriter.write("-------------------\n");
                } else {
                    tsConstWriter.write(String.format("|%3s%2s%3s|%3s%2s%3s|\n", "", "-", "", "", "-", ""));
                    tsConstWriter.write("-------------------\n");
                }
            }
            tsConstWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void printTS_ID(AtomHashMap ts_ids) {
        System.out.println("TS_ID: " + ts_ids.table.length);

        try {
            FileWriter tsIdWriter = new FileWriter(TS_ID);
            tsIdWriter.write("\n");
            tsIdWriter.write("  TS_CONST:\n");
            tsIdWriter.write("\n");
            tsIdWriter.write(String.format("|%4s%4s%4s|%1s%6s%1s|\n", "", "NUME", "", "", "COD_TS", ""));
            System.out.println("Successfully wrote to the file.");
            tsIdWriter.write("-----------------------\n");

            for (Node<String, Integer> atom : ts_ids.table) {
                if (atom != null) {
                    tsIdWriter.write(String.format("|%2s%8s%2s|%3s%2s%3s|\n", "", atom.key, "", "", atom.value, ""));
                    tsIdWriter.write("-----------------------\n");
                } else {
                    tsIdWriter.write(String.format("|%3s%6s%3s|%3s%2s%3s|\n", "", "-", "", "", "-", ""));
                    tsIdWriter.write("-----------------------\n");
                }
            }
            tsIdWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void setFilePaths(String fileID) {
        switch (fileID) {
            case "1" -> {
                FIP = Utils.FIP_01;
                TS_CONST = Utils.TS_CONST_01;
                TS_ID = Utils.TS_ID_01;
            }
            case "2" -> {
                FIP = Utils.FIP_02;
                TS_CONST = Utils.TS_CONST_02;
                TS_ID = Utils.TS_ID_02;
            }
            case "3" -> {
                FIP = Utils.FIP_03;
                TS_CONST = Utils.TS_CONST_03;
                TS_ID = Utils.TS_ID_03;
            }
        }
    }


}