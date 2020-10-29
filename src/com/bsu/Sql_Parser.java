package com.bsu;

import java.io.IOException;
import java.util.*;

public class Sql_Parser {
    public void ParseSqlCommand(String cmd) {

    }

    static void getInfo(List<Company> c,) {

    }

    public void foo(String cmd) throws IOException {
        String[] words = cmd.split(" ");
        Vector<String> vec = new Vector<String>(Arrays.asList(words));
        ArrayList<Integer> indexToRemove = new ArrayList<>();
        for (int i = 0; i < vec.size(); i++) {
            if (vec.get(i).equals("")) {
                indexToRemove.add(i);
            }
        }
        for (int i = indexToRemove.size() - 1; i >= 0; i--) {
            vec.remove(indexToRemove.get(i).intValue());
        }

        if (!words[0].equals("SELECT")) {
            throw new IOException("Error: not a SELECT query");
        }

        int columnRange = 1;
        while (!words[columnRange].equals("FROM")) {
            columnRange++;
        }
        if (columnRange == 1) {
            throw new IOException("Error: no columns in a select query");
        }

        List<String> fields = new ArrayList<>();

        if (columnRange == 2 && words[1].equals("*")) {
            fields = Arrays.asList(Company.FIELD_LIST);
        } else {
            for (int i = 1; i < columnRange; i++) {
                for (String it : Company.FIELD_LIST) {
                    if (words[i].equalsIgnoreCase(it)) {
                        fields.add(it);
                    }
                }
            }
        }

        columnRange++;
        if (!words[columnRange].equals("company_table")) {
            throw new InputMismatchException("Error: wrong table name.");
        }

        columnRange++;

        List<Integer> rowIndexes = new ArrayList<>();

        if (!words[columnRange].equalsIgnoreCase("WHERE")) {

        }
    }
}
