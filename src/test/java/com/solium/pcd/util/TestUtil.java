package com.solium.pcd.util;

import com.solium.pcd.exception.PokerChipException;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    private TestUtil() {
    }

    public static List<String> getPokerChipDistributionList(final String chipBreakdown, final String buyInAmount, final String playerCount) throws PokerChipException {

        List<String> pokerList = new ArrayList<>();
        pokerList.add(chipBreakdown);
        pokerList.add(playerCount);
        pokerList.add(buyInAmount);

        return pokerList;
    }

    public static List<String> getPokerChipDistributionListForBonusOneAndTwo(final String problemType, final String chipBreakdown, final String buyInAmount, final String playerCount) throws PokerChipException {

        List<String> pokerList = new ArrayList<>();
        pokerList.add(problemType);
        pokerList.add(chipBreakdown);
        pokerList.add(playerCount);
        pokerList.add(buyInAmount);

        return pokerList;
    }
}
