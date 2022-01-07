package com.example.Task1.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSPORT_PATTERN =
            "^[_A-Za-z0-9-\\+]";
    private static final String NAME_PATTERN =
            "[A-Za-z0-9_]+";

    public boolean validateEmail(final String hex) {

        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(hex);

        return matcher.matches();
    }
    public boolean validatePassport(final String hex) {

        pattern = Pattern.compile(PASSPORT_PATTERN);
        matcher = pattern.matcher(hex);

        return matcher.matches();
    }
    public boolean validateName(final String hex) {

        pattern = Pattern.compile(NAME_PATTERN);
        matcher = pattern.matcher(hex);

        return matcher.matches();
    }
}
