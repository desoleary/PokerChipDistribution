package com.solium.pcd.bo;

import com.solium.pcd.domain.PokerChips;
import com.solium.pcd.exception.CalculationException;
import com.solium.pcd.exception.PokerChipException;

public class PokerChipDistributionForBonusOneStrategy extends PokerChipDistributionStrategyBase implements IPokerChipDistributionStrategy {

    @Override
    public PokerChips calculate(PokerChips list) throws CalculationException, PokerChipException {
        PokerChips pokerList = getPokerListWithBuyInOfOneForAllDenominations(list);
        return getOptimumDistribution(pokerList);
    }
}
