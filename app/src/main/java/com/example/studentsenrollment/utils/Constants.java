package com.example.studentsenrollment.utils;

import java.util.ArrayList;

public class Constants {

    public static final String LOGIN_PREFERENCE = "LoginPreference";
    public static final String USERNAME = "Username";
    public static final String PASSWORD = "Password";
    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String SELECT_YEAR = "Select year *";
    public static final String SELECT_DEPARTMENT = "Select department *";

    public static ArrayList<String> getYears() {
        ArrayList<String> years = new ArrayList<>();

        years.add("1st year");
        years.add("2nd year");
        years.add("3rd year");
        years.add("4th year");

        return years;
    }

    public static ArrayList<String> getDepartments() {
        ArrayList<String> departments = new ArrayList<>();

        departments.add("IT");
        departments.add("CSE");
        departments.add("Mechanical");
        departments.add("Civil");
        departments.add("ECE");
        departments.add("EEE");

        return departments;
    }

}
