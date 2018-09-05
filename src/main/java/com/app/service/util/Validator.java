package com.app.service.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static Boolean validateLogInForm(String email, String password) {
        return validateMail(email) && validatePassword(password);
    }

    public static Boolean validateRegistrationForm(Map<String, String[]> paramMap) {
        return !(paramMap.get("firstName")[0].isEmpty() && paramMap.get("lastName")[0].isEmpty() &&
                paramMap.get("email")[0].isEmpty() && paramMap.get("password")[0].isEmpty()
                && paramMap.get("confirmPassword")[0].isEmpty()) && (
                validateConfirmPassword(paramMap.get("password")[0], paramMap.get("confirmPassword")[0])
                        && validateMail(paramMap.get("email")[0]));
    }

    public static Boolean validateCreateQuestionForm(Map<String, String[]> paramMap) {

        return (paramMap.get("correctAnswer")[0].equals("answer3") && paramMap.get("answer3")[0].isEmpty()) ||
                (paramMap.get("correctAnswer")[0].equals("answer4") && paramMap.get("answer4")[0].isEmpty());
    }

    private static Boolean validateMail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static Boolean validatePassword(String password) {
        return password != null && password.length() >= 8;
    }

    private static Boolean validateConfirmPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
