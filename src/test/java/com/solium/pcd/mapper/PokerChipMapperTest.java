package com.solium.pcd.mapper;

import com.solium.pcd.domain.PokerChips;
import com.solium.pcd.exception.AlgorithmException;
import com.solium.pcd.exception.CalculationException;
import com.solium.pcd.exception.MapperException;
import com.solium.pcd.exception.PokerChipException;
import com.solium.pcd.util.Constants.Algorithm;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PokerChipMapperTest {

    @Test
    public void shouldReturnOptimumDistributionForProblemOne() throws AlgorithmException, CalculationException, MapperException, PokerChipException {

        PokerChips actual = new PokerChipMapper().mapFrom(getTestCaseOneData());

        assertEquals(Algorithm.BASIC, actual.getAlgorithm());
        assertEquals("$0.05", actual.get(0).getDenominationInDollars());
        assertEquals(10, actual.get(0).getQuantity());
        assertEquals("$0.10", actual.get(1).getDenominationInDollars());
        assertEquals(10, actual.get(1).getQuantity());
        assertEquals("$0.25", actual.get(2).getDenominationInDollars());
        assertEquals(10, actual.get(2).getQuantity());
        assertEquals("$0.50", actual.get(3).getDenominationInDollars());
        assertEquals(10, actual.get(3).getQuantity());
        assertEquals("$1.00", actual.get(4).getDenominationInDollars());
        assertEquals(5, actual.get(4).getQuantity());
        assertEquals("$2.00", actual.get(5).getDenominationInDollars());
        assertEquals(5, actual.get(5).getQuantity());
    }

    @Test
    public void shouldReturnOptimumDistributionForProblemTwo() throws AlgorithmException, CalculationException, MapperException, PokerChipException {

        PokerChips actual = new PokerChipForBonusOneMapper().mapFrom(getTestCaseTwoData());

        assertEquals(Algorithm.BONUS_ONE, actual.getAlgorithm());
        assertEquals("$0.05", actual.get(0).getDenominationInDollars());
        assertEquals(10, actual.get(0).getQuantity());
        assertEquals("$0.10", actual.get(1).getDenominationInDollars());
        assertEquals(10, actual.get(1).getQuantity());
        assertEquals("$0.25", actual.get(2).getDenominationInDollars());
        assertEquals(10, actual.get(2).getQuantity());
        assertEquals("$0.50", actual.get(3).getDenominationInDollars());
        assertEquals(10, actual.get(3).getQuantity());
        assertEquals("$1.00", actual.get(4).getDenominationInDollars());
        assertEquals(5, actual.get(4).getQuantity());
        assertEquals("$2.00", actual.get(5).getDenominationInDollars());
        assertEquals(5, actual.get(5).getQuantity());
    }

    @Test
    public void shouldReturnOptimumDistributionForProblemThree() throws AlgorithmException, CalculationException, MapperException, PokerChipException {

        PokerChips actual = new PokerChipForBonusTwoMapper().mapFrom(getTestCaseThreeData());

        assertEquals(Algorithm.BONUS_TWO, actual.getAlgorithm());
        assertEquals("$0.01", actual.get(0).getDenominationInDollars());
        assertEquals(10, actual.get(0).getQuantity());
        assertEquals("Taupe", actual.get(0).getColor());
        assertEquals("$0.05", actual.get(1).getDenominationInDollars());
        assertEquals(10, actual.get(1).getQuantity());
        assertEquals("Yellow", actual.get(1).getColor());
        assertEquals("$0.10", actual.get(2).getDenominationInDollars());
        assertEquals(10, actual.get(2).getQuantity());
        assertEquals("Green", actual.get(2).getColor());
        assertEquals("$0.25", actual.get(3).getDenominationInDollars());
        assertEquals(10, actual.get(3).getQuantity());
        assertEquals("Black", actual.get(3).getColor());
        assertEquals("$0.50", actual.get(4).getDenominationInDollars());
        assertEquals(5, actual.get(4).getQuantity());
        assertEquals("Blue", actual.get(4).getColor());
        assertEquals("$1.00", actual.get(5).getDenominationInDollars());
        assertEquals(5, actual.get(5).getQuantity());
        assertEquals("Red", actual.get(5).getColor());
    }

    private List<String> getTestCaseOneData() {

        List<String> pokerDetailsList = new ArrayList<>();

        pokerDetailsList.add("50/$2.00,50/$1.00,100/$0.50,100/$0.25,100/$0.10,100/$0.05");
        pokerDetailsList.add("10");
        pokerDetailsList.add("$10.00");

        return pokerDetailsList;
    }

    private List<String> getTestCaseTwoData() {

        List<String> pokerDetailsList = new ArrayList<>();

        pokerDetailsList.add("B1");
        pokerDetailsList.add("50/$2.00,50/$1.00,100/$0.50,100/$0.25,100/$0.10,100/$0.05");
        pokerDetailsList.add("10");
        pokerDetailsList.add("$10.00");

        return pokerDetailsList;
    }

    private List<String> getTestCaseThreeData() {

        List<String> pokerDetailsList = new ArrayList<>();

        pokerDetailsList.add("B2");
        pokerDetailsList.add("50/Red,50/Blue,100/Black,100/Green,100/Yellow,100/Taupe");
        pokerDetailsList.add("10");
        pokerDetailsList.add("$10.00");

        return pokerDetailsList;
    }
}
