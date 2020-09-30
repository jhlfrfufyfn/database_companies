package com.bsu;

import java.text.ParseException;
import java.util.Date;

class Company {
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

    void print() {
        System.out.println(name + ",  " + shortName + ",  " + actualizationDate.toString() + ",  "
                + address + ",  " + foundationDate.toString() + ",  " + employeeNumber + ",  "
                + auditor + ",  " + phoneNumber + ",  " + eMail + ",  " + branch + ",  " + activityType + ",  "
                + webPage + ";");
        System.out.print('\n');
    }

}
