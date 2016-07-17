package com.solium.pcd.main;

import com.solium.pcd.domain.PokerChip;
import com.solium.pcd.domain.PokerChips;
import com.solium.pcd.util.TestCase;
import org.junit.Test;

public class PokerDistributionAssignmentIntTest extends TestCase {

    @Test
    public void main_problemOne_shouldReturnOptimumDistribution() {
        PokerChips actualPokerChipDistribution = PokerDistributionAssignment.getPokerChipDistribution("data/problem1.txt");
        assertSize(6, actualPokerChipDistribution);
        PokerChip firstPokerChip = actualPokerChipDistribution.get(0);
        assertEquals(5, firstPokerChip.getQuantity());
    }
}
