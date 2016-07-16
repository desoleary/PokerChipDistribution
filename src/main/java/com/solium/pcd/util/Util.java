package com.solium.pcd.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class Util {

    private static final int MONETARY_CHARACTER_OFFSET_INDEX = 1;
    private static final String DOLLAR_FORMAT = "#0.00";

    private Util() {
    }

    /**
     * rounds value to desired decimal places.
     *
     * @param val                   big decimal value
     * @param numberOfDecimalPlaces number of decimal places
     * @return big decimal with specified decimal places
     */
    public static BigDecimal roundToDecimalPlaces(final BigDecimal val, final int numberOfDecimalPlaces) {

        int ROUNDING_MODE = BigDecimal.ROUND_HALF_EVEN;
        return val.setScale(numberOfDecimalPlaces, ROUNDING_MODE);
    }

    /**
     * @param val
     * @param numberOfDecimalPlaces
     * @return
     * @throws NumberFormatException
     */
    public static BigDecimal roundToDecimalPlaces(final String val, final int numberOfDecimalPlaces) throws NumberFormatException {
        return roundToDecimalPlaces(new BigDecimal(val), numberOfDecimalPlaces);
    }

    /**
     * @param currency
     * @param numberOfDecimalPlaces
     * @return big decimal value of given currency
     */
    public static BigDecimal convertAmountToBigDecimal(final String currency, final int numberOfDecimalPlaces) {
        return roundToDecimalPlaces(new BigDecimal(currency), numberOfDecimalPlaces);
    }

    /**
     * @param currency
     * @param numberOfDecimalPlaces
     * @return big decimal value of given currency
     */
    public static BigDecimal convertMonetaryAmountToBigDecimal(final String currency, final int numberOfDecimalPlaces) {
        return roundToDecimalPlaces(new BigDecimal(currency.substring(MONETARY_CHARACTER_OFFSET_INDEX)), numberOfDecimalPlaces);
    }

    public static BigDecimal divideFor(final BigDecimal val1, final BigDecimal val2, final int numberOfDecimalPlaces) {
        return val1.divide(val2, numberOfDecimalPlaces, RoundingMode.HALF_UP);
    }

    /**
     * @param val
     * @return dollar amount as String
     */
    public static String getDollarAmount(final BigDecimal val) {

        DecimalFormat currency = new DecimalFormat(DOLLAR_FORMAT);

        return String.format("$%s", currency.format(val));
    }

    /**
     * @param param
     * @param regex
     * @return boolean value to determine if regex matches
     */
    public static boolean regexMatches(String param, Pattern regex) {
        return regex.matcher(param).matches();
    }
}
