package utilities;

import common.Constants;
import org.junit.Assert;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by aaronbriel on 9/27/16.
 */
public class CommonUtils {

    private static Logger LOGGER = Logger.getLogger(CommonUtils.class.getName());

    /**
     * Returns an expiration year
     *
     * @param year The number of years to increment
     * @return Integer of calendar year
     */
    public int getExpYear(int year) {
        return Calendar.getInstance().get(Calendar.YEAR) + year;
    }


    /**
     * Returns the substring of a string between delimiters
     *
     * @param text       The text to pull the string from
     * @param delimiter1 The string preceding the string to extract
     * @param delimiter2 The string following the string to extract
     * @return String
     */
    public static String getSubstring(String text, String delimiter1, String delimiter2) {
        return text.substring(text.indexOf(delimiter1) + delimiter1.length(), text.indexOf(delimiter2));
    }

    /**
     * It checks if 2 String regions match, but what's important is that it also has an overload with a handy ignoreCase parameter
     *
     * @param sourceText
     * @param patternText
     * @return boolean value of result
     */
    public static boolean containsIgnoreCase(String sourceText, String patternText) {
        final int length = patternText.length();

        // Empty string is contained
        if (length == 0)
            return true;

        final char firstLo = Character.toLowerCase(patternText.charAt(0));
        final char firstUp = Character.toUpperCase(patternText.charAt(0));

        for (int i = sourceText.length() - length; i >= 0; i--) {
            // Quick check before calling the more expensive regionMatches() method:
            final char ch = sourceText.charAt(i);
            if (ch != firstLo && ch != firstUp)
                continue;

            if (sourceText.regionMatches(true, i, patternText, 0, length))
                return true;
        }
        return false;
    }

    /**
     * Creates a new file with fileName if it does not exist, then adds
     * or appends text to it
     *
     * @param fileName The file to create and/or append
     * @param text     The text to append to the file
     */
    public static void appendFile(String fileName, String text) {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            LOGGER.info("IOException: " + e.getMessage());
        }
    }


    /**
     * Checks if the strings in an array are sorted
     *
     * @param array      The array to check
     * @return boolean  Whether the array was sorted
     */
    public static boolean isArraySorted(String[] array) {
        String previous = "";
        for (final String current: array) {
            if (current.compareTo(previous) < 0)
                return false;
            previous = current;
        }
        return true;
    }


    /**
     * Checks if the strings in a list are sorted
     *
     * @param list      The list to check
     * @return boolean  Whether the array was sorted
     */
    public static boolean isListSorted(ArrayList<String> list) {
        String previous = "";
        for (final String current: list) {
            if (current.compareTo(previous) < 0)
                return false;
                previous = current;
        }
        return true;
    }

    /**
     * Replaces the long month name in a string with the short month name
     *
     * @param text - String in which replacements will be made
     * @return - the text after replacements have been made
     */
    public static String replaceLongMonthWithShortMonth(String text) {
        LOGGER.info("replaceLongMonthWithShortMonth started with input text:  '" + text + "'");
    	text = text.replace(Constants.JANUARY, Constants.JAN);
        text = text.replace(Constants.FEBRUARY, Constants.FEB);
        text = text.replace(Constants.MARCH, Constants.MAR);
        text = text.replace(Constants.APRIL, Constants.APR);
        text = text.replace(Constants.JUNE, Constants.JUN);
        text = text.replace(Constants.JULY, Constants.JUL);
        text = text.replace(Constants.AUGUST, Constants.AUG);
        text = text.replace(Constants.SEPTEMBER, Constants.SEP);
        text = text.replace(Constants.OCTOBER, Constants.OCT);
        text = text.replace(Constants.NOVEMBER, Constants.NOV);
        text = text.replace(Constants.DECEMBER, Constants.DEC);
        LOGGER.info("replaceLongMonthWithShortMonth completed returning text:  '" + text + "'");
        return text;
    }

    /**
     * Replaces the short month name in a string with the long month name
     *
     * @param text - String in which replacements will be made
     * @return - the text after replacements have been made
     */
    public static String replaceShortMonthWithLongMonth(String text) {
        LOGGER.info("replaceShortMonthWithLongMonth started with input text:  '" + text + "'");
        text = text.replace(Constants.JAN, Constants.JANUARY);
        text = text.replace(Constants.FEB, Constants.FEBRUARY);
        text = text.replace(Constants.MAR, Constants.MARCH);
        text = text.replace(Constants.APR, Constants.APRIL);
        text = text.replace(Constants.JUN, Constants.JUNE);
        text = text.replace(Constants.JUL, Constants.JULY);
        text = text.replace(Constants.AUG, Constants.AUGUST);
        text = text.replace(Constants.SEP, Constants.SEPTEMBER);
        text = text.replace(Constants.OCT, Constants.OCTOBER);
        text = text.replace(Constants.NOV, Constants.NOVEMBER);
        text = text.replace(Constants.DEC, Constants.DECEMBER);
        LOGGER.info("replaceShortMonthWithLongMonth completed returning text:  '" + text + "'");
        return text;
    }

    /**
     * Replaces the short day of week name in a string with the long day of week name
     * 
     * @param text - String in which replacements will be made
     * @return - the text after replacements have been made
     */
    public static String replaceShortDayWithLongDay(String text) {
        LOGGER.info("replaceShortDayWithLongDay started with input text:  '" + text + "'");
    	text = text.replace(Constants.MON, Constants.MONDAY);
    	text = text.replace(Constants.TUE, Constants.TUESDAY);
    	text = text.replace(Constants.WED, Constants.WEDNESDAY);
    	text = text.replace(Constants.THU, Constants.THURSDAY);
    	text = text.replace(Constants.FRI, Constants.FRIDAY);
    	text = text.replace(Constants.SAT, Constants.SATURDAY);
    	text = text.replace(Constants.SUN, Constants.SUNDAY);
        LOGGER.info("replaceShortDayWithLongDay completed returning text:  '" + text + "'");
        return text;
    }

    /**
     * Replaces the long day of week name in a string with the short day of week name
     * 
     * @param text - String in which replacements will be made
     * @return - the text after replacements have been made
     */
    public static String replaceLongDayWithShortDay(String text) {
        LOGGER.info("replaceLongDayWithShortDay started with input text:  '" + text + "'");
    	text = text.replace(Constants.MONDAY, Constants.MON);
    	text = text.replace(Constants.TUESDAY, Constants.TUE);
    	text = text.replace(Constants.WEDNESDAY, Constants.WED);
    	text = text.replace(Constants.THURSDAY, Constants.THU);
    	text = text.replace(Constants.FRIDAY, Constants.FRI);
    	text = text.replace(Constants.SATURDAY, Constants.SAT);
    	text = text.replace(Constants.SUNDAY, Constants.SUN);
        LOGGER.info("replaceLongDayWithShortDay completed returning text:  '" + text + "'");
        return text;
    }

    /**
     * Round to certain number of decimals
     *
     * @param floatValue    The float to round up
     * @param decimalPlace  The numbers of decimals
     * @return float        The rounded float
     */
    public static float round(float floatValue, int decimalPlace) {
        return BigDecimal.valueOf(floatValue).setScale(decimalPlace,BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * get a random number within specified range and starting minimum number
     *
     * @param min - the minimum number
     * @param range - the range size of numbers to choose from
     * @return - the random number
     */
    public static int getRandomNumber(int min, int range) {
        LOGGER.info("getRandomNumber started");
        if (range <= 0) {
            Assert.fail("The range (" + range + ") must be a positive number");
        }
        Random rand = new Random();
        LOGGER.info("getRandomNumber completed");
        return rand.nextInt(range) + min;
    }
}