package com.solium.pcd.mapper;

import com.solium.pcd.domain.PokerChip;
import com.solium.pcd.domain.PokerChipList;
import com.solium.pcd.exception.MapperException;
import com.solium.pcd.exception.PokerChipException;
import com.solium.pcd.util.Constants;
import com.solium.pcd.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class PokerChipMapperBase {

    void checkNumberOfLines(List<String> pokerList, int numLines) throws MapperException {
        if (pokerList.size() != numLines) {
            throw new MapperException(String.format("Input parameter count is incorrect, should be %d but is %d", numLines, pokerList.size()));
        }
    }

    private void validateChipBreakdownRegex(final String chipsBreakdown, final Pattern regex) throws MapperException {

        String[] chipBreakdownArray = chipsBreakdown.split(",");
        if (chipBreakdownArray.length == 0) {
            throw new MapperException(String.format("Chip breakdown invalid, should be of the format 00/$0.00 but is [%s]", chipsBreakdown));
        }

        for (String chipBreakDown : chipBreakdownArray) {
            Matcher m = regex.matcher(chipBreakDown);

            if (!m.find()) {
                throw new MapperException(String.format("Chip breakdown invalid, should be of the format 00/$0.00 but is [%s]", chipBreakDown));
            }
        }
    }

    private void validateNumberOfPlayersRegex(final String numberOfPlayers, final Pattern regex) throws MapperException {
        if (!Util.regexMatches(numberOfPlayers, regex)) {
            throw new MapperException(String.format("Input number of players is invalid, should be a number but is %s", numberOfPlayers));
        }
    }

    private void validateCurrencyRegex(final String buyIn, final Pattern regex) throws MapperException {
        if (!Util.regexMatches(buyIn, regex)) {
            throw new MapperException(String.format("Input buy in is invalid, should be of the format $0.00 but is %s", buyIn));
        }
    }

    void validateRegularInput(List<String> pokerList) throws MapperException {
        Iterator<String> listItr = pokerList.iterator();
        String chipBreakdown = listItr.next();
        String numberOfPlayers = listItr.next();
        String buyIn = listItr.next();

        validateChipBreakdownRegex(chipBreakdown, Constants.REGULAR_CHIP_BREAKDOWN_REGEX);

        validateNumberOfPlayersRegex(numberOfPlayers, Constants.INTEGER_REGEX);

        validateCurrencyRegex(buyIn, Constants.CURRENCY_REGEX);
    }

    void validateBonusTwoInput(List<String> pokerList) throws MapperException {

        Iterator<String> listItr = pokerList.iterator();
        String chipBreakdown = listItr.next();
        String numberOfPlayers = listItr.next();
        String buyIn = listItr.next();

        validateChipBreakdownRegex(chipBreakdown, Constants.BONUS_TWO_CHIP_BREAKDOWN_REGEX);

        validateNumberOfPlayersRegex(numberOfPlayers, Constants.INTEGER_REGEX);

        validateCurrencyRegex(buyIn, Constants.CURRENCY_REGEX);
    }

    private final List<PokerChip> getChipBreakdownList(final String chipsBreakDown, final int numberOfPlayers) throws MapperException, PokerChipException {

        String[] chipBreakdownArray = chipsBreakDown.split(",");
        if (chipBreakdownArray.length == 0) {
            throw new MapperException(String.format("Input chip breakdown is invalid, should be in format of 00/$0.00,.. but is %s", chipsBreakDown));
        }

        List<PokerChip> list = new ArrayList<>();

        for (String chipBreakDown : chipBreakdownArray) {
            Matcher m = Constants.REGULAR_CHIP_BREAKDOWN_REGEX.matcher(chipBreakDown);

            if (m.find()) {
                int quantity = Integer.parseInt(m.group(1)) / numberOfPlayers;
                BigDecimal denomination = Util.convertAmountToBigDecimal(m.group(2));

                list.add(new PokerChip(denomination, quantity));
            } else {
                System.out.println(String.format("Chip breakdown invalid, should be of the format 00/$0.00 but is [%s]", chipBreakDown));
            }
        }

        return list;
    }

    private final List<PokerChip> getBonusTwoChipBreakdownList(String chipsBreakDown, final int numberOfPlayers) throws MapperException, PokerChipException {

        String[] chipBreakdownArray = chipsBreakDown.split(",");
        if (chipBreakdownArray.length == 0) {
            throw new MapperException(String.format("Input chip breakdown is invalid, should be in format of 00/Color,.. but is %s", chipsBreakDown));
        }

        List<PokerChip> list = new ArrayList<>();

        int currencyIndex = chipBreakdownArray.length;

        for (String chipBreakDown : chipBreakdownArray) {
            Matcher m = Constants.BONUS_TWO_CHIP_BREAKDOWN_REGEX.matcher(chipBreakDown);

            if (m.find()) {

                currencyIndex--;

                int quantity = Integer.parseInt(m.group(1)) / numberOfPlayers;
                BigDecimal denomination = Constants.availableCurrencies[currencyIndex];
                String color = m.group(2);

                list.add(new PokerChip(color, denomination, quantity));
            } else {
                System.out.println(String.format("Chip breakdown invalid, should be of the format 00/Color but is [%s]", chipBreakDown));
            }
        }

        return list;
    }

    final PokerChipList getPokerList(List<String> pokerDetailsList) throws MapperException, PokerChipException {

        Iterator<String> listItr = pokerDetailsList.iterator();
        String chipBreakdown = listItr.next();

        int numberOfPlayers = Integer.parseInt(listItr.next());
        BigDecimal buyIn = Util.convertMonetaryAmountToBigDecimal(listItr.next());

        List<PokerChip> list = getChipBreakdownList(chipBreakdown, numberOfPlayers);

        PokerChipList pokerList = new PokerChipList(list);
        pokerList.setBuyInAmount(buyIn);
        pokerList.setPlayerCount(numberOfPlayers);

        return pokerList;
    }

    final PokerChipList getBonusTwoPokerList(List<String> pokerDetailsList) throws MapperException, PokerChipException {

        Iterator<String> listItr = pokerDetailsList.iterator();
        String chipBreakdown = listItr.next();
        int numberOfPlayers = Integer.parseInt(listItr.next());
        BigDecimal buyIn = Util.convertMonetaryAmountToBigDecimal(listItr.next());

        List<PokerChip> list = getBonusTwoChipBreakdownList(chipBreakdown, numberOfPlayers);

        PokerChipList pokerList = new PokerChipList(list);
        pokerList.setBuyInAmount(buyIn);
        pokerList.setPlayerCount(numberOfPlayers);

        return pokerList;
    }
}
