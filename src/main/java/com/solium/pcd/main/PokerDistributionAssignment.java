package com.solium.pcd.main;

import com.solium.pcd.command.PokerChipDistributionCommand;
import com.solium.pcd.domain.PokerChipList;
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

        String fileName = null;
        try {
            fileName = readFileNameFrom(System.in);

            PokerChipList resultList = new PokerChipDistributionCommand()
                    .execute(readFileAsList(fileName));

            resultList.getResult();

        } catch (IOException ex) {
            System.out.println(String.format(
                    "IO exception caught trying to read the filename: '%s'",
                    fileName));
            System.exit(1);

        } catch (MapperException e) {
            System.out.println("Mapper exception caught trying to parse input.");
            System.exit(1);
        }
    }

    private static String readFileNameFrom(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        return br.readLine();
    }

    private static List<String> readFileAsList(String filePath) throws java.io.IOException {
        FileReader reader = new FileReader(new File(filePath));
        BufferedReader bin = new BufferedReader(reader);
        List<String> pokerList = new ArrayList<>();
        for (String line = bin.readLine(); line != null; line = bin.readLine()) {
            pokerList.add(line);
        }
        return pokerList;
    }
}
