package com.solium.pcd.bo;

import com.solium.pcd.domain.PokerChipList;
import com.solium.pcd.exception.CalculationException;
import com.solium.pcd.exception.PokerChipException;

public class PokerChipDistributionStrategy extends PokerChipDistributionStrategyBase implements IPokerChipDistributionStrategy {

    @Override
    public PokerChipList calculate(PokerChipList list) throws CalculationException, PokerChipException {

        PokerChipList pokerList = getOptimumDistribution(list);
        return pokerList;
    }
}
