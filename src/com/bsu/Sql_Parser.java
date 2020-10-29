package com.bsu;

import java.io.IOException;
import java.util.*;

public class Sql_Parser {
    public void ParseSqlCommand(String cmd) {

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

        List<Company.CompanyFields> fields = new ArrayList<>();

        if (columnRange == 2 && words[1].equals("*")) {
            fields = Arrays.asList(Company.CompanyFields.values());
        } else {
            for (int i = 1; i < columnRange; i++) {
                for (Company.CompanyFields it : Company.CompanyFields.values()) {
                    if (words[i].equalsIgnoreCase(it.name())) {
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
            rowIndexes = Company.namesToIndexes(fields);
        }
    }
}
