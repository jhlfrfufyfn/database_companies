package com.bsu;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Company {
    static final String[] FIELD_LIST = {"name", "shortName", "actualizationDate", "address", "foundationDate", "employeeNumber,",
            "auditor", "phoneNumber", "eMail", "branch", "activityType", "webPage"};

    private String getNameKey() {
        return FIELD_LIST[0];
    }

    static Company VOID_COMPANY;
    String name;
    String shortName;
    Date actualizationDate;
    String address;
    Date foundationDate;
    int employeeNumber;
    String auditor;
    String phoneNumber;
    String eMail;
    String branch;
    String activityType;
    String webPage;

    private String getShortNameKey() {
        return FIELD_LIST[1];
    }

    private String getActualizationDateKey() {
        return FIELD_LIST[2];
    }

    private String getAddressKey() {
        return FIELD_LIST[3];
    }

    private String getFoundationDateKey() {
        return FIELD_LIST[4];
    }

    private String getEmployeeNumberKey() {
        return FIELD_LIST[5];
    }

    private String getAuditorKey() {
        return FIELD_LIST[6];
    }

    private String getPhoneNumber() {
        return FIELD_LIST[7];
    }

    private String getEMailKey() {
        return FIELD_LIST[8];
    }

    private String getBranchKey() {
        return FIELD_LIST[9];
    }

    private String getActivityTypeKey() {
        return FIELD_LIST[10];
    }

    private String getWebPageKey() {
        return FIELD_LIST[11];
    }

    @Override
    public String toString() {
        return name + ";" + shortName + ";" + actualizationDate.toString() + ";"
                + address + ";" + foundationDate.toString() + ";" + employeeNumber + ";"
                + auditor + ";" + phoneNumber + ";" + eMail + ";" + branch + ";" + activityType + ";"
                + webPage + ";";
    }

    Company(String[] aData) throws ParseException {
        name = aData[0];
        shortName = aData[1];
        actualizationDate = Main.dateFormat.parse(aData[2]);
        address = aData[3];
        foundationDate = Main.dateFormat.parse(aData[4]);
        employeeNumber = Integer.parseInt(aData[5]);
        auditor = aData[6];
        phoneNumber = aData[7];
        eMail = aData[8];
        branch = aData[9];
        activityType = aData[10];
        webPage = aData[11];
    }

    void print(FileWriter fw) throws IOException {
        fw.write(name + ";" + shortName + ";" + actualizationDate.toString() + ";"
                + address + ";" + foundationDate.toString() + ";" + employeeNumber + ";"
                + auditor + ";" + phoneNumber + ";" + eMail + ";" + branch + ";" + activityType + ";"
                + webPage + ";" + System.lineSeparator());
    }

    public String[] getFields(List<String> list, Company c) {
        List<String> ans = new ArrayList<>();
        for (String str : list) {
            if (str.equalsIgnoreCase(FIELD_LIST[0])) {
                ans.add(c.name);
            }
            if (str.equalsIgnoreCase(FIELD_LIST[1])) {
                ans.add(c.shortName);
            }
            if (str.equalsIgnoreCase(FIELD_LIST[2])) {
                ans.add(c.actualizationDate.toString());
            }
            if (str.equalsIgnoreCase(FIELD_LIST[3])) {
                ans.add(c.address);
            }
            if (str.equalsIgnoreCase(FIELD_LIST[4])) {
                ans.add(c.foundationDate.toString());
            }
            if (str.equalsIgnoreCase(FIELD_LIST[5])) {
                ans.add(Integer.toString(c.employeeNumber));
            }
            if (str.equalsIgnoreCase(FIELD_LIST[6])) {
                ans.add(c.auditor);
            }
            if (str.equalsIgnoreCase(FIELD_LIST[7])) {
                ans.add(c.phoneNumber);
            }
            if (str.equalsIgnoreCase(FIELD_LIST[8])) {
                ans.add(c.eMail);
            }
            if (str.equalsIgnoreCase(FIELD_LIST[9])) {
                ans.add(c.branch);
            }
            if (str.equalsIgnoreCase(FIELD_LIST[10])) {
                ans.add(c.activityType);
            }
            if (str.equalsIgnoreCase(FIELD_LIST[11])) {
                ans.add(c.webPage);
            }
        }
        return (String[]) ans.toArray();
    }

    boolean checkShortName(String s) {
        if (s.equalsIgnoreCase(shortName)) {

        }
    }

    enum CompanyFields {
        name(0), shortName(1), actualizationDate(2), address(3),
        foundationDate(4), employeeNumber(5), auditor(6), phoneNumber(7),
        eMail(8), branch(9), activityType(10), webPage(11);

        CompanyFields(int i) {
        }
    }
}
