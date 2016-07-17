package com.solium.pcd.domain;

import com.solium.pcd.exception.PokerChipException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PokerChipTest {

    @Test
    public void shouldBeValidPokerChipObject() throws PokerChipException {

        PokerChip chip = new PokerChip(new BigDecimal("1.00"), 1);

        assertEquals(new BigDecimal("1.00"), chip.getDenomination());
        assertEquals(1, chip.getQuantity());
    }

    @Test
    public void shouldBeValidPokerChipObjectTestTwo() throws PokerChipException {

        PokerChip chip = new PokerChip("Green", new BigDecimal("1.00"), 1);

        assertEquals(new BigDecimal("1.00"), chip.getDenomination());
        assertEquals(1, chip.getQuantity());
        assertEquals("Green", chip.getColor());
    }

    @Test
    public void shouldBeValidPokerChipObjectTestThree() throws PokerChipException {

        PokerChip chip = new PokerChip("Green", new BigDecimal("1.00"), 1);
        chip.setBuyInQuantity(1);

        assertEquals(new BigDecimal("1.00"), chip.getDenomination());
        assertEquals(1, chip.getQuantity());
        assertEquals("Green", chip.getColor());
        assertEquals(1, chip.getBuyInQuantity());
        assertEquals(0, chip.getQuantityRemaining());
    }

    @Test
    public void shouldBeValidPokerChipObjectTestFour() throws PokerChipException {

        PokerChip chip = new PokerChip("Green", new BigDecimal("1.00"), 2);
        chip.setBuyInQuantity(1);
        chip.setQuantitySetAside(1);

        assertEquals(new BigDecimal("1.00"), chip.getDenomination());
        assertEquals(1, chip.getQuantity());
        assertEquals("Green", chip.getColor());
        assertEquals(1, chip.getBuyInQuantity());
        assertEquals(0, chip.getQuantityRemaining());
        assertEquals(1, chip.getQuantitySetAside());
    }

    @Test
    public void shouldBeValidPokerChipObjectTestFive() throws PokerChipException {

        PokerChip chip = new PokerChip("Green", new BigDecimal("1.00"), 1);
        chip.setColor("Blue");
        chip.setDenomination(new BigDecimal("2.00"));
        chip.setQuantity(2);

        assertEquals(new BigDecimal("2.00"), chip.getDenomination());
        assertEquals(2, chip.getQuantity());
        assertEquals("Blue", chip.getColor());
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
        assertEquals(1, chip.getQuantity());
        chip.setQuantitySetAside(3);
    }
}
