package com.bsu;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

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

    Company(String name, String shortName, Date actualizationDate, String address, Date foundationDate,
            int employeeNumber, String auditor, String phoneNumber, String eMail, String branch, String activityType,
            String webPage) {
        this.name = name;
        this.shortName = shortName;
        this.actualizationDate = actualizationDate;
        this.address = address;
        this.foundationDate = foundationDate;
        this.employeeNumber = employeeNumber;
        this.auditor = auditor;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
        this.branch = branch;
        this.activityType = activityType;
        this.webPage = webPage;
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

    @Override
    public String toString() {
        return name + ";" + shortName + ";" + actualizationDate.toString() + ";"
                + address + ";" + foundationDate.toString() + ";" + employeeNumber + ";"
                + auditor + ";" + phoneNumber + ";" + eMail + ";" + branch + ";" + activityType + ";"
                + webPage + ";";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return employeeNumber == company.employeeNumber &&
                Objects.equals(name, company.name) &&
                Objects.equals(shortName, company.shortName) &&
                Objects.equals(actualizationDate, company.actualizationDate) &&
                Objects.equals(address, company.address) &&
                Objects.equals(foundationDate, company.foundationDate) &&
                Objects.equals(auditor, company.auditor) &&
                Objects.equals(phoneNumber, company.phoneNumber) &&
                Objects.equals(eMail, company.eMail) &&
                Objects.equals(branch, company.branch) &&
                Objects.equals(activityType, company.activityType) &&
                Objects.equals(webPage, company.webPage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, shortName, actualizationDate, address, foundationDate, employeeNumber,
                auditor, phoneNumber, eMail, branch, activityType, webPage);
    }

    void print(FileWriter fw) throws IOException {
        fw.write(this.toString() + System.lineSeparator());
    }
}
