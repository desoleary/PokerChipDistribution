package com.solium.pcd.factory;

import com.solium.pcd.bo.PokerChipDistributionForBonusOneStrategy;
import com.solium.pcd.bo.PokerChipDistributionForBonusTwoStrategy;
import com.solium.pcd.bo.PokerChipDistributionStrategy;
import com.solium.pcd.mapper.PokerChipForBonusOneMapper;
import com.solium.pcd.mapper.PokerChipForBonusTwoMapper;
import com.solium.pcd.mapper.PokerChipMapper;
import com.solium.pcd.util.Constants;
import com.solium.pcd.util.Util;

import java.util.List;

public class PokerChipDistributionFactory {

    private PokerChipDistributionFactory() {
    }

    public static IPokerChipDistribution createPokerChipDistributionFor(List<String> inputList)
            throws IllegalArgumentException {

        if (inputList == null || inputList.isEmpty()) {
            throw new IllegalArgumentException(
                    "The input parameters are not valid, must not be null or empty but was found to be.");

        }

        String firstParam = inputList.get(0);
        if (firstParam.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("The first Input parameter is not valid, must not be null or empty but was found to be '%s'", firstParam));
        }

        if (Util.regexMatches(firstParam, Constants.BONUS_ONE_REGEX)) {
            return new PokerChipDistribution(new PokerChipForBonusOneMapper(), new PokerChipDistributionForBonusOneStrategy());
        } else if (Util.regexMatches(firstParam, Constants.BONUS_TWO_REGEX)) {
            return new PokerChipDistribution(new PokerChipForBonusTwoMapper(), new PokerChipDistributionForBonusTwoStrategy());
        } else {
            return new PokerChipDistribution(new PokerChipMapper(), new PokerChipDistributionStrategy());
        }
    }
}
