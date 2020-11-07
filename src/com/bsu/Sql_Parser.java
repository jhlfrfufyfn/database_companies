package com.bsu;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Sql_Parser {
    public void ParseSqlCommand(String cmd) {

    }

    static void printInfo(FileWriter fw, List<Company> c) {

    }

    public List<Company> processQuery(String cmd, List<Company> records) throws IOException {
        String[] words = cmd.split(" ");
        Vector<String> vec = new Vector<String>(Arrays.asList(words));
        ArrayList<Integer> indexToRemove = new ArrayList<>();
        for (int i = 0; i < vec.size(); i++) {
            if (vec.get(i).equals("") || vec.get(i).equals(" ")) {
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

        if (!words[columnRange].equalsIgnoreCase("WHERE")) {
            throw new InputMismatchException("Error: where doesn't exist");
        }

        columnRange++;
        List<String> logicalExpression = new ArrayList<>(Arrays.asList(words).subList(columnRange, words.length));
        for (String str : logicalExpression) {
            str = str.replaceAll("[()]", "");
        }

        int requestType = 0;
        for (String word : words) {
            if (word.equalsIgnoreCase("shortname")) {
                requestType = 1;
            } else if (word.equalsIgnoreCase("activitytype")) {
                requestType = 2;
            } else if (word.equalsIgnoreCase("employee")) {
                requestType = 3;
            }
        }
        String[] logicalExpArray = new String[logicalExpression.size()];
        logicalExpArray = logicalExpression.toArray(logicalExpArray);
        List<Company> result = new ArrayList<>();
        for (Company comp : records) {
            if (requestType == 1) {
                if (Logic_Parser.ParseStringExpression(logicalExpArray, comp.shortName, "shortName")) {
                    result.add(comp);
                }
            }
            if (requestType == 2) {
                if (Logic_Parser.ParseNumberExpression(logicalExpArray, comp.employeeNumber, "employeeNumber")) {
                    result.add(comp);
                }
            }
            if (requestType == 3) {
                if (Logic_Parser.ParseStringExpression(logicalExpArray, comp.shortName, "activityType")) {
                    result.add(comp);
                }
            }
        }
        return result;
    }
}
