package com.solium.pcd.bo;

import com.solium.pcd.domain.PokerChip;
import com.solium.pcd.domain.PokerChipList;
import com.solium.pcd.exception.CalculationException;
import com.solium.pcd.exception.PokerChipException;
import com.solium.pcd.util.Constants;
import com.solium.pcd.util.Util;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.ListIterator;

abstract class PokerChipDistributionStrategyBase {

    final PokerChipList getOptimumDistribution(final PokerChipList list) throws CalculationException, PokerChipException {

        PokerChipList resultList = list;
        BigDecimal buyInAmount = resultList.getBuyInAmount();
        BigDecimal remainingBuyIn = buyInAmount;

        for (PokerChip current : resultList) {
            int quantity = current.getQuantity();
            BigDecimal denomination = current.getDenomination();

            int maxQuantityAllowed = Util.divideFor(remainingBuyIn, denomination, Constants.NUMBER_OF_DECIMAL_PLACES).intValue();
            if (maxQuantityAllowed >= quantity) {
                current.setBuyInQuantity(quantity);
                remainingBuyIn = Util.roundToDecimalPlaces(remainingBuyIn.subtract(denomination.multiply(new BigDecimal(quantity))), Constants.NUMBER_OF_DECIMAL_PLACES);
            } else if (maxQuantityAllowed > 0 && maxQuantityAllowed <= quantity) {
                current.setBuyInQuantity(maxQuantityAllowed);
                remainingBuyIn = Util.roundToDecimalPlaces(remainingBuyIn.subtract(denomination.multiply(new BigDecimal(maxQuantityAllowed))), Constants.NUMBER_OF_DECIMAL_PLACES);
            }

            if (remainingBuyIn.compareTo(new BigDecimal("0.00")) > 0 && (current.getQuantity() - current.getBuyInQuantity() > 0)) {
                current.setBuyInQuantity(current.getBuyInQuantity() + 1);
                remainingBuyIn = Util.roundToDecimalPlaces(remainingBuyIn.subtract(denomination), Constants.NUMBER_OF_DECIMAL_PLACES);
            }
        }

        if (remainingBuyIn.compareTo(new BigDecimal("0.00")) < 0) {
            Collections.reverse(resultList);
            ListIterator<PokerChip> iter = resultList.listIterator();

            BigDecimal overBuyIn = remainingBuyIn.abs();

            while (iter.hasNext() && remainingBuyIn.compareTo(new BigDecimal("0.00")) != 0) {
                PokerChip current = iter.next();

                BigDecimal denomination = current.getDenomination();

                int maxQuantityAllowed = Util.divideFor(overBuyIn, denomination, Constants.NUMBER_OF_DECIMAL_PLACES).intValue();

                if (maxQuantityAllowed > 0) {

                    int buyInQuantity = current.getBuyInQuantity();
                    if (maxQuantityAllowed > buyInQuantity) {
                        maxQuantityAllowed = buyInQuantity;
                    }

                    current.setBuyInQuantity(buyInQuantity - maxQuantityAllowed);
                    overBuyIn = Util.roundToDecimalPlaces(overBuyIn.subtract(denomination.multiply(new BigDecimal(maxQuantityAllowed))), Constants.NUMBER_OF_DECIMAL_PLACES);
                    remainingBuyIn = Util.roundToDecimalPlaces(remainingBuyIn.add(denomination.multiply(new BigDecimal(maxQuantityAllowed))), Constants.NUMBER_OF_DECIMAL_PLACES);
                }
            }
            Collections.reverse(resultList);
        }

        BigDecimal totalBuyIn = new BigDecimal("0.00");
        for (PokerChip current : resultList) {

            if (current.getQuantitySetAside() > 0) {
                int quantitySetAside = current.getQuantitySetAside();
                BigDecimal denomination = current.getDenomination();
                current.setQuantitySetAside(0);
                current.setBuyInQuantity(current.getBuyInQuantity() + quantitySetAside);
                list.setBuyInAmount(Util.roundToDecimalPlaces(list.getBuyInAmount().add(denomination.multiply(new BigDecimal(quantitySetAside))), Constants.NUMBER_OF_DECIMAL_PLACES));
            }

            int buyInQty = current.getBuyInQuantity();
            BigDecimal denomination = current.getDenomination();
            totalBuyIn = Util.roundToDecimalPlaces(totalBuyIn.add(denomination.multiply(new BigDecimal(Integer.toString(buyInQty)))), Constants.NUMBER_OF_DECIMAL_PLACES);
        }

        if (!(0 == resultList.getBuyInAmount().compareTo(totalBuyIn))) {
            throw new CalculationException(String.format("Unable to get optimum poker chip distribution from given inputs. Expected buy in was %s but was calculated as %s", resultList.getBuyInAmount(), totalBuyIn));
        }

        // Reverse the poker distribution list such that the highest denomination will be at the top as required for output.
        Collections.reverse(resultList);

        return resultList;
    }

    final PokerChipList getPokerListWithBuyInOfOneForAllDenominations(final PokerChipList list) throws PokerChipException {

        BigDecimal initialBuyIn = new BigDecimal("0.00");
        for (PokerChip current : list) {
            current.setQuantitySetAside(Constants.BONUS_TWO_MIN_QUANTITY);
            initialBuyIn = Util.roundToDecimalPlaces(initialBuyIn.add(current.getDenomination()), Constants.NUMBER_OF_DECIMAL_PLACES);
        }
        list.setBuyInAmount(Util.roundToDecimalPlaces(list.getBuyInAmount().subtract(initialBuyIn), Constants.NUMBER_OF_DECIMAL_PLACES));

        return list;
    }
}
