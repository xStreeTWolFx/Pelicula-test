package com.example.peliculatest.utilities;

public class TextUtil {

    public static String BLANK_REGEX = "^[\\s]*$";

    public static boolean isBlank(String input) {
        return input == null || input.matches(BLANK_REGEX);
    }


    public static String formatDouble(Double number) {

        if (number == null || number < 0)
            return "";
        else
            return String.valueOf(number);
    }

    public static Double parseDouble(String number) {

        if (number == null || isBlank(number))
            return null;
        else
            try {
                return Double.valueOf(number);
            } catch (NumberFormatException n) {
                n.printStackTrace();
                return 0D;
            }

    }

}
