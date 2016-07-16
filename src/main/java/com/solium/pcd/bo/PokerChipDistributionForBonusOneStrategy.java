package com.solium.pcd.bo;

import com.solium.pcd.domain.PokerChipList;
import com.solium.pcd.exception.CalculationException;
import com.solium.pcd.exception.PokerChipException;

public class PokerChipDistributionForBonusOneStrategy extends PokerChipDistributionStrategyBase implements IPokerChipDistributionStrategy {

    @Override
    public PokerChipList calculate(PokerChipList list) throws CalculationException, PokerChipException {

        //TODO: what the heck is this about
        PokerChipList pokerList = getPokerListWithBuyInOfOneForAllDenominations(list);
        pokerList = getOptimumDistribution(list);

        return pokerList;
    }
}
