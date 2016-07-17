package com.solium.pcd.mapper;

import com.solium.pcd.domain.PokerChips;
import com.solium.pcd.exception.MapperException;
import com.solium.pcd.exception.PokerChipException;
import com.solium.pcd.util.Constants.Algorithm;

import java.util.List;

public class PokerChipMapper extends PokerChipMapperBase implements IMapper {

    private static final int REQUIRED_NUMBER_OF_LINES = 3;

    @Override
    public PokerChips mapFrom(List<String> pokerDetailsList) throws MapperException, PokerChipException {

        validateInput(pokerDetailsList);

        PokerChips pokerList = getPokerList(pokerDetailsList);
        pokerList.setAlgorithm(Algorithm.BASIC);

        return pokerList;
    }

    @Override
    public void validateInput(List<String> pokerList) throws MapperException {

        checkNumberOfLines(pokerList, REQUIRED_NUMBER_OF_LINES);

        validateRegularInput(pokerList);
    }

}
