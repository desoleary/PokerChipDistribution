package com.solium.pcd.bo;

import com.solium.pcd.domain.PokerChips;
import com.solium.pcd.exception.CalculationException;
import com.solium.pcd.exception.PokerChipException;

public class PokerChipDistributionStrategy extends PokerChipDistributionStrategyBase implements IPokerChipDistributionStrategy {

    @Override
    public PokerChips calculate(PokerChips list) throws CalculationException, PokerChipException {

        PokerChips pokerList = getOptimumDistribution(list);
        return pokerList;
    }
}
