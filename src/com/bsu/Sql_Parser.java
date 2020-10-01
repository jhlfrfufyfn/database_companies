package com.bsu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class Sql_Parser {
    public void ParseSqlCommand(String cmd) {

    }

    public void foo(String cmd) {
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


    }
}
