package com.bsu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Query {
    static List<Company> findByFDate(Date date1, Date date2, List<Company> records) {
        List<Company> ans = new ArrayList<>();
        for (Company it : records) {
            if (date1.before(it.foundationDate) && date2.after(it.foundationDate)) {
                ans.add(it);
            }
        }
        return ans;
    }

    static List<Company> findByEmplNumber(int n1, int n2, List<Company> records) {
        List<Company> ans = new ArrayList<>();
        for (Company it : records) {
            if (n1 <= it.employeeNumber && it.employeeNumber <= n2) {
                ans.add(it);
            }
        }
        return ans;
    }

    static List<Company> findByBranch(String catLine, List<Company> records) {
        List<Company> ans = new ArrayList<>();
        for (Company it : records) {
            if (it.branch.equalsIgnoreCase(catLine)) {
                ans.add(it);
            }
        }
        return ans;
    }

    static List<Company> findByActType(String catLine, List<Company> records) {
        List<Company> ans = new ArrayList<>();
        for (Company it : records) {
            if (it.activityType.equalsIgnoreCase(catLine)) {
                ans.add(it);
            }
        }
        return ans;
    }

    static Company findByShortName(String catLine, List<Company> records) {
        for (Company it : records) {
            if (it.shortName.equalsIgnoreCase(catLine)) {
                return it;
            }
        }
        return Company.VOID_COMPANY;
    }
}
