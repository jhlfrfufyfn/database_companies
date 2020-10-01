package com.bsu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static java.lang.System.exit;

public class Main {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    private static final String SETTINGS_FILENAME = "data/settings.txt";
    private static final String LOG_FILENAME = "data/logfile.txt";
    private static String CSV_IN_FILENAME;
    private static String CSV_OUT_FILENAME;

    static SimpleDateFormat dateFormat;

    public static void main(String[] args) {
        LOGGER.setLevel(Level.FINE);
        File logFile = new File(LOG_FILENAME);
        FileHandler filehandler;

        if (args.length == 2) {
            CSV_IN_FILENAME = args[0];
            CSV_OUT_FILENAME = args[1];
        } else {
            System.out.println("Default filenames: input.csv, output.txt" + System.lineSeparator());
        }

        try {
            boolean isCreated = logFile.createNewFile();
            filehandler = new FileHandler(LOG_FILENAME, true);
            filehandler.setLevel(Level.FINE);
            SimpleFormatter formatter = new SimpleFormatter();
            filehandler.setFormatter(formatter);
            LOGGER.addHandler(filehandler);
            LOGGER.fine("Program started " + System.lineSeparator());
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }

        try (Scanner fin = new Scanner(new File(SETTINGS_FILENAME))) {
            String sDateFormat = fin.nextLine();
            dateFormat = new SimpleDateFormat(sDateFormat);
        } catch (Exception ex) {
            System.out.println("Error: date format in settings.txt is wrong. Program is terminated.");
            exit(1);
        }

        try (Scanner cin = new Scanner(System.in)) {
            List<Company> records = new ArrayList<>();
            try (Scanner fin = new Scanner(new File(CSV_IN_FILENAME))) {
                while (fin.hasNext()) {
                    String[] data = fin.nextLine().split(";");
                    records.add(new Company(data));
                }
            } catch (Exception ex) {
                System.err.println("Error: " + ex.toString());
            }

            queryCycle(cin, records);
        } catch (Exception ex) {
            System.err.println("Error: " + ex.toString());
        }


    }

    private static void queryCycle(Scanner cin, List<Company> records) {
        try (FileWriter fout = new FileWriter(CSV_OUT_FILENAME)) {
            while (true) {
                System.out.println("Enter the query number(enter 0 if you want to finish the program): ");
                int q;
                q = Integer.parseInt(cin.nextLine());
                if (q == 0) {
                    break;
                }
                String catLine;
                String queryInfo;
                List<Company> list;
                switch (q) {
                    case 1:
                        System.out.println("Enter the short name: ");
                        catLine = cin.nextLine();
                        queryInfo = "Query 1, short name: " + catLine;
                        Company ans = Query.findByShortName(catLine, records);
                        if (ans == Company.VOID_COMPANY) {
                            fout.write("Company not found" + System.lineSeparator());
                        } else {
                            ans.print(fout);
                        }
                        LOGGER.fine(queryInfo + ", companies found: "
                                + ((ans == Company.VOID_COMPANY) ? "1" : "0")
                                + System.lineSeparator());
                        break;
                    case 2:
                        System.out.println("Enter the branch: ");
                        catLine = cin.nextLine();
                        queryInfo = "Query 2, branch: " + catLine;
                        list = Query.findByBranch(catLine, records);
                        fout.write("Companies found: " + System.lineSeparator());
                        if (list.isEmpty()) {
                            fout.write("NONE" + System.lineSeparator());
                        }
                        for (Company it : list) {
                            it.print(fout);
                        }
                        LOGGER.fine(queryInfo + ", companies found: "
                                + list.size() + System.lineSeparator());
                        break;
                    case 3:
                        System.out.println("Enter the activity type: ");
                        catLine = cin.nextLine();
                        queryInfo = "Query 3, activity type: " + catLine;
                        list = Query.findByActType(catLine, records);
                        fout.write("Companies found: " + System.lineSeparator());
                        if (list.isEmpty()) {
                            fout.write("NONE" + System.lineSeparator());
                        }
                        for (Company it : list) {
                            it.print(fout);
                        }
                        LOGGER.fine(queryInfo + ", companies found: "
                                + list.size() + System.lineSeparator());
                        break;
                    case 4:
                        System.out.println("Enter the dates in two different lines: ");
                        catLine = cin.nextLine();
                        queryInfo = "Query 4, dates: " + catLine;
                        Date date1 = Main.dateFormat.parse(catLine);
                        String catLine2 = cin.nextLine();
                        Date date2 = Main.dateFormat.parse(catLine);
                        list = Query.findByFDate(date1, date2, records);
                        fout.write("Companies found: " + System.lineSeparator());
                        if (list.isEmpty()) {
                            fout.write("NONE" + System.lineSeparator());
                        }
                        for (Company it : list) {
                            it.print(fout);
                        }
                        LOGGER.fine(queryInfo + " , " + catLine2
                                + ", companies found: " + list.size() + System.lineSeparator());
                        break;
                    case 5:
                        System.out.println("Enter the two numbers: ");
                        catLine = cin.nextLine();
                        String[] nums = catLine.split(" ");
                        int n1 = Integer.parseInt(nums[0]);
                        int n2 = Integer.parseInt(nums[1]);
                        queryInfo = "Query 5, employee numbers: " + n1 + " , " + n2;
                        list = Query.findByEmplNumber(n1, n2, records);
                        fout.write("Companies found: " + System.lineSeparator());
                        if (list.isEmpty()) {
                            fout.write("NONE" + System.lineSeparator());
                        }
                        for (Company it : list) {
                            it.print(fout);
                        }
                        LOGGER.fine(queryInfo
                                + ", companies found: " + list.size() + System.lineSeparator());
                        break;
                    default:
                        System.out.println("Wrong query number. Try again.");
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
        }
    }
}
