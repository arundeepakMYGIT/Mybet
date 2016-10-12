package com.mybet.java.util;

public class ApplicationUtil {

    public static String errorMessage(Exception e) {
        if (e == null) {
            return "ERROR: unspecified error occurred.";
        }
        return "ERROR: " + e.getLocalizedMessage() + ", CAUSE: " + (e.getCause() != null ? e.getCause().getLocalizedMessage() : "unspecified cause");
    }
}
