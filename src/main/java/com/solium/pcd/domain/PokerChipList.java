package com.solium.pcd.domain;

import com.solium.pcd.exception.AlgorithmException;
import com.solium.pcd.util.Constants.Algorithm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokerChipList extends ArrayList<PokerChip> {

    private static final long serialVersionUID = 1L;
    private int _playerCount = 0;
    private BigDecimal _buyInAmount = new BigDecimal("0.00");

    private Algorithm _algorithm = Algorithm.BASIC;

    public PokerChipList(List<PokerChip> list) {
        Collections.sort(list, new PokerChipComparator());
        addAll(list);
    }

    /**
     * @param playerCount the _playerCount to set
     */
    public final void setPlayerCount(final int playerCount) {
        _playerCount = playerCount;
    }

    /**
     * @return the _playerCount
     */
    public final int getPlayerCount() {
        return _playerCount;
    }

    /**
     * @param buyInAmount the _buyInAmount to set
     */
    public final void setBuyInAmount(final BigDecimal buyInAmount) {
        _buyInAmount = buyInAmount;
    }

    /**
     * @return the _buyInAmount
     */
    public final BigDecimal getBuyInAmount() {
        return _buyInAmount;
    }

    /**
     * @param algorithm the _algorithm used for the program
     */
    public void setAlgorithm(Algorithm algorithm) {
        _algorithm = algorithm;
    }

    /**
     * @return Algorithm enumeration
     */
    public Algorithm getAlgorithm() {
        return _algorithm;
    }

    public void getResult() throws AlgorithmException {

        System.out.println("Output:");

        switch (getAlgorithm()) {
            case BASIC:
            case BONUS_ONE:
                for (PokerChip result : this) {
                    System.out.println(String.format("%s - %s", result.getDenominationInDollars(), result.getBuyInQuantity()));
                }
                break;
            case BONUS_TWO:
                for (PokerChip result : this) {
                    System.out.println(String.format("%s - %s - %d", result.getColor(), result.getDenominationInDollars(), result.getBuyInQuantity()));
                }
                break;

            default:
                throw new AlgorithmException("Encoding passed is not valid");
        }
    }
}
