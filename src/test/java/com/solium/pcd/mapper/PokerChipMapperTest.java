package com.solium.pcd.mapper;

import com.solium.pcd.domain.PokerChipList;
import com.solium.pcd.exception.AlgorithmException;
import com.solium.pcd.exception.CalculationException;
import com.solium.pcd.exception.MapperException;
import com.solium.pcd.exception.PokerChipException;
import com.solium.pcd.util.Constants.Algorithm;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PokerChipMapperTest {

    @Test
    public void shouldReturnOptimumDistributionForProblemOne() throws AlgorithmException, CalculationException, MapperException, PokerChipException {

        PokerChipList actual = new PokerChipMapper().mapFrom(getTestCaseOneData());

        Assert.assertEquals(Algorithm.BASIC, actual.getAlgorithm());
        Assert.assertEquals("$0.05", actual.get(0).getDenominationInDollars());
        Assert.assertEquals(10, actual.get(0).getQuantity());
        Assert.assertEquals("$0.10", actual.get(1).getDenominationInDollars());
        Assert.assertEquals(10, actual.get(1).getQuantity());
        Assert.assertEquals("$0.25", actual.get(2).getDenominationInDollars());
        Assert.assertEquals(10, actual.get(2).getQuantity());
        Assert.assertEquals("$0.50", actual.get(3).getDenominationInDollars());
        Assert.assertEquals(10, actual.get(3).getQuantity());
        Assert.assertEquals("$1.00", actual.get(4).getDenominationInDollars());
        Assert.assertEquals(5, actual.get(4).getQuantity());
        Assert.assertEquals("$2.00", actual.get(5).getDenominationInDollars());
        Assert.assertEquals(5, actual.get(5).getQuantity());
    }

    @Test
    public void shouldReturnOptimumDistributionForProblemTwo() throws AlgorithmException, CalculationException, MapperException, PokerChipException {

        PokerChipList actual = new PokerChipForBonusOneMapper().mapFrom(getTestCaseTwoData());

        Assert.assertEquals(Algorithm.BONUS_ONE, actual.getAlgorithm());
        Assert.assertEquals("$0.05", actual.get(0).getDenominationInDollars());
        Assert.assertEquals(10, actual.get(0).getQuantity());
        Assert.assertEquals("$0.10", actual.get(1).getDenominationInDollars());
        Assert.assertEquals(10, actual.get(1).getQuantity());
        Assert.assertEquals("$0.25", actual.get(2).getDenominationInDollars());
        Assert.assertEquals(10, actual.get(2).getQuantity());
        Assert.assertEquals("$0.50", actual.get(3).getDenominationInDollars());
        Assert.assertEquals(10, actual.get(3).getQuantity());
        Assert.assertEquals("$1.00", actual.get(4).getDenominationInDollars());
        Assert.assertEquals(5, actual.get(4).getQuantity());
        Assert.assertEquals("$2.00", actual.get(5).getDenominationInDollars());
        Assert.assertEquals(5, actual.get(5).getQuantity());
    }

    @Test
    public void shouldReturnOptimumDistributionForProblemThree() throws AlgorithmException, CalculationException, MapperException, PokerChipException {

        PokerChipList actual = new PokerChipForBonusTwoMapper().mapFrom(getTestCaseThreeData());

        Assert.assertEquals(Algorithm.BONUS_TWO, actual.getAlgorithm());
        Assert.assertEquals("$0.01", actual.get(0).getDenominationInDollars());
        Assert.assertEquals(10, actual.get(0).getQuantity());
        Assert.assertEquals("Taupe", actual.get(0).getColor());
        Assert.assertEquals("$0.05", actual.get(1).getDenominationInDollars());
        Assert.assertEquals(10, actual.get(1).getQuantity());
        Assert.assertEquals("Yellow", actual.get(1).getColor());
        Assert.assertEquals("$0.10", actual.get(2).getDenominationInDollars());
        Assert.assertEquals(10, actual.get(2).getQuantity());
        Assert.assertEquals("Green", actual.get(2).getColor());
        Assert.assertEquals("$0.25", actual.get(3).getDenominationInDollars());
        Assert.assertEquals(10, actual.get(3).getQuantity());
        Assert.assertEquals("Black", actual.get(3).getColor());
        Assert.assertEquals("$0.50", actual.get(4).getDenominationInDollars());
        Assert.assertEquals(5, actual.get(4).getQuantity());
        Assert.assertEquals("Blue", actual.get(4).getColor());
        Assert.assertEquals("$1.00", actual.get(5).getDenominationInDollars());
        Assert.assertEquals(5, actual.get(5).getQuantity());
        Assert.assertEquals("Red", actual.get(5).getColor());
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
