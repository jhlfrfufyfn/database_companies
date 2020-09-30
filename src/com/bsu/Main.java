package com.bsu;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
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

    private static final String CSV_FILENAME = "data/input.csv";
    private static final String SETTINGS_FILENAME = "data/settings.txt";
    private static final String LOG_FILENAME = "data/logfile.txt";

    static SimpleDateFormat dateFormat;

    public static void main(String[] args) {
        LOGGER.setLevel(Level.FINE);
        File logFile = new File(LOG_FILENAME);
        FileHandler filehandler;

        try {
            logFile.createNewFile();
            filehandler = new FileHandler(LOG_FILENAME, true);
            filehandler.setLevel(Level.FINE);
            SimpleFormatter formatter = new SimpleFormatter();
            filehandler.setFormatter(formatter);
            LOGGER.addHandler(filehandler);
            LOGGER.fine("Program started ");
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
            try (Scanner fin = new Scanner(new File(CSV_FILENAME))) {
                while (fin.hasNext()) {
                    String[] data = fin.nextLine().split(";");
                    records.add(new Company(data));
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.toString());
            }

            queryCycle(cin, records);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
        }


    }

    private static void queryCycle(Scanner cin, List<Company> records) throws ParseException {
        while (true) {
            System.out.println("Enter the query number(enter 0 if you want to finish the program): ");
            int q;
            q = Integer.parseInt(cin.nextLine());
            if (q == 0) {
                break;
            }
            String catLine;
            List<Company> list;
            switch (q) {
                case 1:
                    System.out.println("Enter the short name: ");
                    catLine = cin.nextLine();
                    Company ans = findByShortName(catLine, records);
                    if (ans == Company.VOID_COMPANY) {
                        System.out.println("Company not found\n");
                    } else {
                        ans.print();
                    }
                    LOGGER.fine("Query 1, short name: " + catLine +
                            ", companies found: " + ((ans == Company.VOID_COMPANY) ? "1" : "0") + "\n");
                    break;
                case 2:
                    System.out.println("Enter the branch: ");
                    catLine = cin.nextLine();
                    list = findByBranch(catLine, records);
                    System.out.println("Companies found: ");
                    for (Company it : list) {
                        it.print();
                    }
                    LOGGER.fine("Query 2, branch: " + catLine + ", companies found: " + list.size() + "\n");
                    break;
                case 3:
                    System.out.println("Enter the activity type: ");
                    catLine = cin.nextLine();
                    list = findByActType(catLine, records);
                    System.out.println("Companies found: ");
                    for (Company it : list) {
                        it.print();
                    }
                    LOGGER.fine("Query 3, activity type: " + catLine + ", companies found: " + list.size() + "\n");
                    break;
                case 4:
                    System.out.println("Enter the dates in two different lines: ");
                    catLine = cin.nextLine();
                    Date date1 = Main.dateFormat.parse(catLine);
                    String catLine2 = cin.nextLine();
                    Date date2 = Main.dateFormat.parse(catLine);
                    list = findByFDate(date1, date2, records);
                    System.out.println("Companies found: ");
                    for (Company it : list) {
                        it.print();
                    }
                    LOGGER.fine("Query 4, dates: " + catLine + " , " + catLine2
                            + ", companies found: " + list.size() + "\n");
                    break;
                case 5:
                    System.out.println("Enter the two numbers: ");
                    catLine = cin.nextLine();
                    String[] nums = catLine.split(" ");
                    int n1 = Integer.parseInt(nums[0]);
                    int n2 = Integer.parseInt(nums[1]);
                    list = findByEmplNumber(n1, n2, records);
                    System.out.println("Companies found: ");
                    for (Company it : list) {
                        it.print();
                    }
                    LOGGER.fine("Query 5, employee numbers: " + n1 + " , " + n2
                            + ", companies found: " + list.size() + "\n");
                    break;
                default:
                    System.out.println("Wrong query number. Try again.");
            }

        }
    }

    private static List<Company> findByFDate(Date date1, Date date2, List<Company> records) {
        List<Company> ans = new ArrayList<>();
        for (Company it : records) {
            if (date1.before(it.foundationDate) && date2.after(it.foundationDate)) {
                ans.add(it);
            }
        }
        return ans;
    }

    private static List<Company> findByEmplNumber(int n1, int n2, List<Company> records) {
        List<Company> ans = new ArrayList<>();
        for (Company it : records) {
            if (n1 <= it.employeeNumber && it.employeeNumber <= n2) {
                ans.add(it);
            }
        }
        return ans;
    }

    private static List<Company> findByBranch(String catLine, List<Company> records) {
        List<Company> ans = new ArrayList<>();
        for (Company it : records) {
            if (it.branch.equalsIgnoreCase(catLine)) {
                ans.add(it);
            }
        }
        return ans;
    }

    private static List<Company> findByActType(String catLine, List<Company> records) {
        List<Company> ans = new ArrayList<>();
        for (Company it : records) {
            if (it.activityType.equalsIgnoreCase(catLine)) {
                ans.add(it);
            }
        }
        return ans;
    }

    private static Company findByShortName(String catLine, List<Company> records) {
        for (Company it : records) {
            if (it.shortName.equalsIgnoreCase(catLine)) {
                return it;
            }
        }
        return Company.VOID_COMPANY;
    }
}
