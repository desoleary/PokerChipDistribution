package com.solium.pcd.domain;

import com.solium.pcd.exception.PokerChipException;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class PokerChipTest {

    @Test
    public void shouldBeValidPokerChipObject() throws PokerChipException {

        PokerChip chip = new PokerChip(new BigDecimal("1.00"), 1);

        Assert.assertEquals(new BigDecimal("1.00"), chip.getDenomination());
        Assert.assertEquals(1, chip.getQuantity());
    }

    @Test
    public void shouldBeValidPokerChipObjectTestTwo() throws PokerChipException {

        PokerChip chip = new PokerChip("Green", new BigDecimal("1.00"), 1);

        Assert.assertEquals(new BigDecimal("1.00"), chip.getDenomination());
        Assert.assertEquals(1, chip.getQuantity());
        Assert.assertEquals("Green", chip.getColor());
    }

    @Test
    public void shouldBeValidPokerChipObjectTestThree() throws PokerChipException {

        PokerChip chip = new PokerChip("Green", new BigDecimal("1.00"), 1);
        chip.setBuyInQuantity(1);

        Assert.assertEquals(new BigDecimal("1.00"), chip.getDenomination());
        Assert.assertEquals(1, chip.getQuantity());
        Assert.assertEquals("Green", chip.getColor());
        Assert.assertEquals(1, chip.getBuyInQuantity());
        Assert.assertEquals(0, chip.getQuantityRemaining());
    }

    @Test
    public void shouldBeValidPokerChipObjectTestFour() throws PokerChipException {

        PokerChip chip = new PokerChip("Green", new BigDecimal("1.00"), 2);
        chip.setBuyInQuantity(1);
        chip.setQuantitySetAside(1);

        Assert.assertEquals(new BigDecimal("1.00"), chip.getDenomination());
        Assert.assertEquals(1, chip.getQuantity());
        Assert.assertEquals("Green", chip.getColor());
        Assert.assertEquals(1, chip.getBuyInQuantity());
        Assert.assertEquals(0, chip.getQuantityRemaining());
        Assert.assertEquals(1, chip.getQuantitySetAside());
    }

    @Test
    public void shouldBeValidPokerChipObjectTestFive() throws PokerChipException {

        PokerChip chip = new PokerChip("Green", new BigDecimal("1.00"), 1);
        chip.setColor("Blue");
        chip.setDenomination(new BigDecimal("2.00"));
        chip.setQuantity(2);

        Assert.assertEquals(new BigDecimal("2.00"), chip.getDenomination());
        Assert.assertEquals(2, chip.getQuantity());
        Assert.assertEquals("Blue", chip.getColor());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionForConstructor() throws PokerChipException {

        new PokerChip(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionForConstructor() throws PokerChipException {

        new PokerChip(new BigDecimal("0.00"), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionForConstructorTestTwo() throws PokerChipException {

        new PokerChip(new BigDecimal("1.00"), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionForConstructorTestThree() throws PokerChipException {

        new PokerChip("", new BigDecimal("1.00"), 0);
    }

    @Test(expected = PokerChipException.class)
    public void shouldThrowPokerChipExceptionForQuantityOfZero() throws PokerChipException {

        PokerChip chip = new PokerChip("Green", new BigDecimal("1.00"), 1);
        chip.setQuantity(0);
    }

    @Test(expected = PokerChipException.class)
    public void shouldThrowPokerChipExceptionWhenQuantityIsLessThanBuyInQuantity() throws PokerChipException {

        PokerChip chip = new PokerChip("Green", new BigDecimal("1.00"), 1);
        chip.setBuyInQuantity(1);
        chip.setQuantity(0);
    }

    @Test(expected = PokerChipException.class)
    public void shouldThrowPokerChipExceptionWhenBuyInQuantityIsGreaterThanQuantity() throws PokerChipException {

        PokerChip chip = new PokerChip("Green", new BigDecimal("1.00"), 1);
        chip.setBuyInQuantity(2);
    }

    @Test(expected = PokerChipException.class)
    public void shouldThrowPokerChipExceptionWhenQuantitySetAsideIsGreaterThanPreviousQuantitySetAsideAndQuantity() throws PokerChipException {

        PokerChip chip = new PokerChip("Green", new BigDecimal("1.00"), 1);
        chip.setQuantitySetAside(2);
    }

    @Test(expected = PokerChipException.class)
    public void shouldThrowPokerChipExceptionWhenQuantitySetAsideIsGreaterThanQuantity() throws PokerChipException {

        PokerChip chip = new PokerChip("Green", new BigDecimal("1.00"), 2);
        chip.setQuantitySetAside(1);
        Assert.assertEquals(1, chip.getQuantity());
        chip.setQuantitySetAside(3);
    }
}
