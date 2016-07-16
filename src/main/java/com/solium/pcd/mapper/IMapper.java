package com.solium.pcd.mapper;

import com.solium.pcd.domain.PokerChipList;
import com.solium.pcd.exception.MapperException;
import com.solium.pcd.exception.PokerChipException;

import java.util.List;

public interface IMapper {

    PokerChipList mapFrom(List<String> pokerListDetails) throws MapperException, PokerChipException;

    void validateInput(List<String> pokerList) throws MapperException;
}
