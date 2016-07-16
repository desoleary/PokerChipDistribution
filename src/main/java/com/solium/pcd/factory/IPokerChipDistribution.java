package com.solium.pcd.factory;

import com.solium.pcd.domain.PokerChipList;
import com.solium.pcd.exception.AlgorithmException;
import com.solium.pcd.exception.CalculationException;
import com.solium.pcd.exception.MapperException;
import com.solium.pcd.exception.PokerChipException;

import java.util.List;

public interface IPokerChipDistribution {
    PokerChipList pokerDistributionFor(List<String> pokerList) throws CalculationException, MapperException, PokerChipException, AlgorithmException;
}
