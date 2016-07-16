package com.solium.pcd.mapper;

import com.solium.pcd.domain.PokerChipList;
import com.solium.pcd.exception.MapperException;
import com.solium.pcd.exception.PokerChipException;
import com.solium.pcd.util.Constants.Algorithm;

import java.util.List;

public class PokerChipForBonusOneMapper extends PokerChipMapperBase implements IMapper {

    private static final int REQUIRED_NUMBER_OF_LINES = 4;

    @Override
    public PokerChipList mapFrom(List<String> pokerDetailsList) throws MapperException, PokerChipException {

        validateInput(pokerDetailsList);

        PokerChipList pokerList = getPokerList(pokerDetailsList.subList(1, (pokerDetailsList.size())));
        pokerList.setAlgorithm(Algorithm.BONUS_ONE);

        return pokerList;
    }

    @Override
    public void validateInput(List<String> pokerDetailsList) throws MapperException {

        checkNumberOfLines(pokerDetailsList, REQUIRED_NUMBER_OF_LINES);

        validateRegularInput(pokerDetailsList.subList(1, (pokerDetailsList.size())));
    }
}
