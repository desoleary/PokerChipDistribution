package com.solium.pcd.bo;

import com.solium.pcd.domain.PokerChip;
import com.solium.pcd.domain.PokerChips;
import com.solium.pcd.exception.CalculationException;
import com.solium.pcd.exception.PokerChipException;
import com.solium.pcd.util.Constants;
import com.solium.pcd.util.Util;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.ListIterator;

abstract class PokerChipDistributionStrategyBase {

    final PokerChips getOptimumDistribution(final PokerChips pokerChips) throws CalculationException, PokerChipException {

        BigDecimal remainingBuyIn = pokerChips.getBuyInAmount();

        //########## iterate over PokerChips, lowest denomination to highest

        for (PokerChip pokerChip : pokerChips) {
            pokerChip.setBuyInQuantity(pokerChip.buyInQuantityFor(remainingBuyIn));
            remainingBuyIn = Util.subtractFor(remainingBuyIn, pokerChip.getBuyInAmount());
        }

        //########## remainingBuyIn < 0
        if (remainingBuyIn.compareTo(new BigDecimal(0.00)) < 0) {
            Collections.reverse(pokerChips);
            ListIterator<PokerChip> iter = pokerChips.listIterator();

            BigDecimal overBuyIn = remainingBuyIn.abs();

            //########## - reverse pokerChip by demonination (highest to lowest) and get overBuyIn
            //########## - iterate over pokerChips until end or remainingBuyIn = 0

            while (iter.hasNext() && remainingBuyIn.compareTo(new BigDecimal("0.00")) != 0) {
                PokerChip current = iter.next();

                BigDecimal denomination = current.getDenomination();

                int maxQuantityAllowed = Util.divideFor(overBuyIn, denomination).intValue();

                //########## -- maxQuantityAllowed > 0
                if (maxQuantityAllowed > 0) {

                    int buyInQuantity = current.getBuyInQuantity();
                    //########## --- if maxQuantityAllowed > buyInQuantity, then set maxQuantityAllowed to buyInQuantity
                    if (maxQuantityAllowed > buyInQuantity) {
                        maxQuantityAllowed = buyInQuantity;
                    }

                    //########## --- reduce buyInQuantity, overBuyIn  by maxQuantityAllowed
                    current.setBuyInQuantity(buyInQuantity - maxQuantityAllowed);
                    overBuyIn = Util.subtractFor(overBuyIn, denomination.multiply(new BigDecimal(maxQuantityAllowed)));
                    remainingBuyIn = Util.roundToDecimalPlaces(remainingBuyIn.add(denomination.multiply(new BigDecimal(maxQuantityAllowed))));
                }
            }

            //########## - reverse result
            Collections.reverse(pokerChips);
        }

        BigDecimal totalBuyIn = new BigDecimal("0.00");
        //########## totalBuyIn = 0, iterate over result
        for (PokerChip current : pokerChips) {

            //########## - if quantitySetAside > 0
            if (current.getQuantitySetAside() > 0) {
                //########## -- set quantitySetAside = 0, increase buyInQuantity and buyInAmount, by that amount
                int quantitySetAside = current.getQuantitySetAside();
                BigDecimal denomination = current.getDenomination();
                current.setQuantitySetAside(0);
                current.setBuyInQuantity(current.getBuyInQuantity() + quantitySetAside);
                pokerChips.setBuyInAmount(Util.roundToDecimalPlaces(pokerChips.getBuyInAmount().add(denomination.multiply(new BigDecimal(quantitySetAside)))));
            }

            //########## - totalBuyIn += denomination * buyInQuantity
            int buyInQty = current.getBuyInQuantity();
            BigDecimal denomination = current.getDenomination();
            totalBuyIn = Util.roundToDecimalPlaces(totalBuyIn.add(denomination.multiply(new BigDecimal(Integer.toString(buyInQty)))));
        }

        //########## throw exception is buyInAmount != totalBuyIn
        if (!(0 == pokerChips.getBuyInAmount().compareTo(totalBuyIn))) {
            throw new CalculationException(String.format("Unable to get optimum poker chip distribution from given inputs. Expected buy in was %s but was calculated as %s", pokerChips.getBuyInAmount(), totalBuyIn));
        }

        //########## reverse pokerChips to prepare for output
        // Reverse the poker distribution pokerChips such that the highest denomination will be at the top as required for output.
        Collections.reverse(pokerChips);

        return pokerChips;
    }

    //TODO move to PokerChipDistributionForBonusOneStrategy
    final PokerChips getPokerListWithBuyInOfOneForAllDenominations(final PokerChips list) throws PokerChipException {

        BigDecimal initialBuyIn = new BigDecimal("0.00");
        for (PokerChip current : list) {
            current.setQuantitySetAside(Constants.BONUS_TWO_MIN_QUANTITY);
            initialBuyIn = Util.roundToDecimalPlaces(initialBuyIn.add(current.getDenomination()));
        }
        list.setBuyInAmount(Util.subtractFor(list.getBuyInAmount(), initialBuyIn));

        return list;
    }
}
