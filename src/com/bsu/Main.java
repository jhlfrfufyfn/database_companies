package com.bsu;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

///TODO: create date format file
///TODO: add logger
///TODO: add encountering capital symbols
public class Main {

    //private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    private static final String CSV_FILENAME = "data/input.csv";
    static SimpleDateFormat dateFormat;

    public static void main(String[] args) {
        /*FileHandler fh = new FileHandler("log.txt");
        LOGGER.addHandler(fh);
        LOGGER.info("Program started: "
                + new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z").format(System.currentTimeMillis())
                + "\n\n");
        */
        System.out.println("Enter the date format(example: dd-MM-yyyy): ");
        String sDateFormat;

        try (Scanner cin = new Scanner(System.in)) {
            sDateFormat = cin.nextLine();
            dateFormat = new SimpleDateFormat(sDateFormat);

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
            System.out.println("Enter the query number(entering 0 means the end of the program): \n");
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
                    break;
                case 2:
                    System.out.println("Enter the branch: ");
                    catLine = cin.nextLine();
                    list = findByBranch(catLine, records);
                    System.out.println("Companies found: \n");
                    for (Company it : list) {
                        it.print();
                    }
                    break;
                case 3:
                    System.out.println("Enter the activity type: ");
                    catLine = cin.nextLine();
                    list = findByActType(catLine, records);
                    System.out.println("Companies found: \n");
                    for (Company it : list) {
                        it.print();
                    }
                    break;
                case 4:
                    System.out.println("Enter the dates in two different lines: ");
                    catLine = cin.nextLine();
                    Date date1 = Main.dateFormat.parse(catLine);
                    catLine = cin.nextLine();
                    Date date2 = Main.dateFormat.parse(catLine);
                    list = findByFDate(date1, date2, records);
                    System.out.println("Companies found: \n");
                    for (Company it : list) {
                        it.print();
                    }
                    break;
                case 5:
                    System.out.println("Enter the two numbers: ");
                    catLine = cin.nextLine();
                    String[] nums = catLine.split(" ");
                    int n1 = Integer.parseInt(nums[0]);
                    int n2 = Integer.parseInt(nums[1]);
                    list = findByEmplNumber(n1, n2, records);
                    System.out.println("Companies found: \n");
                    for (Company it : list) {
                        it.print();
                    }
                    break;
                default:
                    System.out.println("Wrong query number. Try again. \n");
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
            if (it.branch.equals(catLine)) {
                ans.add(it);
            }
        }
        return ans;
    }

    private static List<Company> findByActType(String catLine, List<Company> records) {
        List<Company> ans = new ArrayList<>();
        for (Company it : records) {
            if (it.activityType.equals(catLine)) {
                ans.add(it);
            }
        }
        return ans;
    }

    private static Company findByShortName(String catLine, List<Company> records) {
        for (Company it : records) {
            if (it.shortName.equals(catLine)) {
                return it;
            }
        }
        return Company.VOID_COMPANY;
    }
}
