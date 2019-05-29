package com.saphal.getfit.utils;

import android.util.Log;

import android.content.Context;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelperUtils {
    private static final String TAG = "HelperUtils";

    public HelperUtils(Context applicationContext) {
    }

    public static boolean isShortPassword(String password) {

        Log.d(TAG, "isValidPassword: length: " + password.length());
        return password.length() <= 5;
    }
    public static boolean isValidEmail(String email) {
        String regexEmail = "([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";

        return email.matches(regexEmail);
    }


    public boolean isValidPhone(String phone) {
        String regexPhone = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";

        return phone.matches(regexPhone);
    }


    private static boolean hasSpecialChars(String input) {

        Pattern regexSpecialChars = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher inputStr = regexSpecialChars.matcher(input);
        boolean hasSpecialChars = inputStr.find();

        if (!hasSpecialChars) {
            return false;
        }

        return true;
    }
}
