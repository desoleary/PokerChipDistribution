package com.solium.pcd.domain;

import com.solium.pcd.exception.PokerChipException;
import com.solium.pcd.util.Util;

import java.math.BigDecimal;

public class PokerChip {

    private String _color;
    private int _quantity = 0;
    private int _buyInQuantity = 0;
    private int _quantityRemaining = 0;
    private int _quantitySetAside = 0;

    private BigDecimal _denomination = new BigDecimal("0.00");

    /**
     * @param denomination
     * @param quantity
     * @return PokerChipDistribution object
     * @throws PokerChipException
     */
    public PokerChip(final BigDecimal denomination, final int quantity) throws PokerChipException {
        this(null, denomination, quantity);
    }

    /**
     * @param color
     * @param denomination
     * @param quantity
     * @return PokerChipDistribution object
     * @throws PokerChipException
     */
    public PokerChip(final String color, final BigDecimal denomination,
                     final int quantity) throws PokerChipException {

        if (color != null && "".equalsIgnoreCase(color)) {
            throw new IllegalArgumentException("Inputted colour must be greater than empty.");
        }

        if (denomination == null) {
            throw new NullPointerException("Inputted denomiation must not be null.");
        }

        if (denomination.compareTo(new BigDecimal("0.00")) <= 0) {
            throw new IllegalArgumentException(String.format("Inputted denomiation must be greater than zero, actual is [%s].", denomination.toString()));
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException(String.format("Inputted _quantity must be greater than zero, actual is [%s].", Integer.toString(quantity)));
        }

        setColor(color);
        setDenomination(denomination);
        setQuantity(quantity);
        setQuantityRemaining(quantity);
    }

    //TODO: add some tests
    public int buyInQuantityFor(final BigDecimal remainingBuyIn) throws PokerChipException {
        int buyInQuantity = buyInQuantityUpToMax(remainingBuyIn, getQuantity());

        //########## - remainingQty > 0 && (currentQty - buyInQty > 0) -> and 1 to buyInQty, subtract to remainder ( wtf - test, how it gets here ??? )
        if (remainingBuyIn.compareTo(new BigDecimal(0.00)) > 0
                && (getQuantity() - buyInQuantity > 0)) {
            buyInQuantity = buyInQuantity + 1;
        }

        return buyInQuantity;
    }

    public int buyInQuantityUpToMax(BigDecimal remainingBuyIn, int quantity) {
        int maxQuantity = Util.divideFor(remainingBuyIn, getDenomination()).intValue();

        int buyInQuantity = quantity;
        if (maxQuantity > 0 && maxQuantity < buyInQuantity) {
            buyInQuantity = maxQuantity;
        }
        return buyInQuantity;
    }

    /**
     * @param color the _color to set
     */
    final void setColor(final String color) {
        _color = color;
    }

    /**
     * @return the _color
     */
    public final String getColor() {
        return _color;
    }

    /**
     * @param denomination the _denomination to set
     */
    void setDenomination(final BigDecimal denomination) {
        _denomination = denomination;
    }

    /**
     * @return the _denomination
     */
    public final BigDecimal getDenomination() {
        return _denomination;
    }

    public final String getDenominationInDollars() {
        return Util.getDollarAmount(getDenomination());
    }

    /**
     * @param quantity the _quantity to set
     * @throws PokerChipException
     */
    final void setQuantity(final int quantity) throws PokerChipException {

        if (quantity + getQuantitySetAside() < 1) {
            throw new PokerChipException(String.format("Unable to set a _quantity of [%d] which together with the _quantitySetAside is less than 1", quantity));
        }

        if (quantity < _buyInQuantity) {
            throw new PokerChipException(String.format("Unable to set a _quantity of [%d] which is less than the current _buyInQuantity of [%d]", quantity, _buyInQuantity));
        }

        _quantity = quantity;
        _quantityRemaining = _quantity - _buyInQuantity;
    }

    /**
     * @return the _quantity
     */
    public final int getQuantity() {
        return _quantity;
    }

    /**
     * @param buyInQuantity
     * @throws PokerChipException
     */
    public void setBuyInQuantity(int buyInQuantity) throws PokerChipException {

        if (buyInQuantity > _quantity) {
            throw new PokerChipException(String.format("Unable to set a _buyInQuantity of [%d] which is greater than the current available _quantity of [%d]", _buyInQuantity, _quantity));
        }

        _buyInQuantity = buyInQuantity;
        _quantityRemaining = _quantity - buyInQuantity;
    }

    /**
     * @return _buyInQuantity
     */
    public int getBuyInQuantity() {
        return _buyInQuantity;
    }

    /**
     * @return buyInAmount
     */
    public BigDecimal getBuyInAmount() {
        return getDenomination().multiply(new BigDecimal(getBuyInQuantity()));
    }

    /**
     * @return _quantityRemaining
     */
    int getQuantityRemaining() {
        return _quantityRemaining;
    }

    /**
     * @param quantityRemaining
     */
    private void setQuantityRemaining(int quantityRemaining) {
        _quantityRemaining = quantityRemaining;
    }

    public int getQuantitySetAside() {
        return _quantitySetAside;
    }

    public void setQuantitySetAside(int quantitySetAside) throws PokerChipException {

        if (quantitySetAside > (_quantitySetAside + _quantity)) {
            throw new PokerChipException(String.format("Unable to set a _quantitySetAside of [%d] which is greater than the current available _quantity of [%d]", _quantitySetAside, _quantity));
        }

        if (quantitySetAside < _quantitySetAside) {
            setQuantity(_quantity + (_quantitySetAside - quantitySetAside));
        } else if (quantitySetAside > _quantitySetAside) {
            setQuantity(_quantity - (quantitySetAside - _quantitySetAside));
        }
        _quantitySetAside = quantitySetAside;
    }
}
