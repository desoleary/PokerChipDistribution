package com.solium.pcd.bo;

import com.solium.pcd.domain.PokerChipList;
import com.solium.pcd.exception.CalculationException;
import com.solium.pcd.exception.PokerChipException;

public interface IPokerChipDistributionStrategy {

    PokerChipList calculate(PokerChipList list) throws CalculationException, PokerChipException;
}
