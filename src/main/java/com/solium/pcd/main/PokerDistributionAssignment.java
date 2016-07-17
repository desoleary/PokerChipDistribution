package com.solium.pcd.main;

import com.solium.pcd.command.PokerChipDistributionCommand;
import com.solium.pcd.domain.PokerChips;
import com.solium.pcd.exception.AlgorithmException;
import com.solium.pcd.exception.CalculationException;
import com.solium.pcd.exception.MapperException;
import com.solium.pcd.exception.PokerChipException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PokerDistributionAssignment {

    private PokerDistributionAssignment() {
    }

    public static void main(String[] args) throws IllegalArgumentException, AlgorithmException, CalculationException, PokerChipException {

        try {
            String fileName = readFileNameFrom(System.in);
            getPokerChipDistribution(fileName).getResult();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static PokerChips getPokerChipDistribution(String fileName) {
        try {
            return new PokerChipDistributionCommand()
                    .execute(readFileAsList(fileName));
        } catch (MapperException | PokerChipException | AlgorithmException | CalculationException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readFileNameFrom(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        return br.readLine();
    }

    private static List<String> readFileAsList(String filePath) {
        List<String> pokerList = new ArrayList<>();
        try {
            FileReader reader = new FileReader(new File(filePath));
            BufferedReader bin = new BufferedReader(reader);
            for (String line = bin.readLine(); line != null; line = bin.readLine()) {
                pokerList.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return pokerList;
    }
}
