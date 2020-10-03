package com.bsu;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

class Company {
    private String getNameKey() {
        return "name";
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
        return "shortName";
    }

    private String getActualizationDateKey() {
        return "actualizationDate";
    }

    private String getAddressKey() {
        return "adress";
    }

    private String getFoundationDateKey() {
        return "foundationDate";
    }

    private String getEmployeeNumberKey() {
        return "employeeNumber";
    }

    private String getAuditorKey() {
        return "auditor";
    }

    private String getEMailKey() {
        return "eMail";
    }

    private String getBranchKey() {
        return "branch";
    }

    private String getActivityTypeKey() {
        return "activityType";
    }

    private String getWebPageKey() {
        return "webPage";
    }

    enum CompanyFields {
        name(0), shortName(1), actualizationDate(2), address(3),
        foundationDate(4), employeeNumber(5), auditor(6), phoneNumber(7),
        eMail(8), branch(9), activityType(10), webPage(11);

        CompanyFields(int i) {
        }
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

}
