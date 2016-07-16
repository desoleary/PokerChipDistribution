package com.solium.pcd.command;

import com.solium.pcd.domain.PokerChipList;
import com.solium.pcd.exception.AlgorithmException;
import com.solium.pcd.exception.CalculationException;
import com.solium.pcd.exception.MapperException;
import com.solium.pcd.exception.PokerChipException;
import com.solium.pcd.factory.IPokerChipDistribution;
import com.solium.pcd.factory.PokerChipDistributionFactory;

import java.util.List;

public class PokerChipDistributionCommand {

    public final PokerChipList execute(final List<String> inputList) throws MapperException, CalculationException, AlgorithmException, PokerChipException {
        IPokerChipDistribution mappedInput = PokerChipDistributionFactory.createPokerChipDistributionFor(inputList);
        return mappedInput.pokerDistributionFor(inputList);
    }
}
