package day02;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day02 {
    public static void main(String[] args) {
        List<List<Integer>> reports = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("input/day02.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                int[] intArray = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                List<Integer> list = Arrays.stream(intArray).boxed().toList();
                reports.add(list);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int safeCount = 0;
        for (List<Integer> report : reports) {

            boolean isValid = isValidReport(report);

            // Handle the Problem Dampener
            if (!isValid) {
                List<Integer> copyReport = new ArrayList<>(report);

                for (int i = 0; i < copyReport.size(); i++) {
                    int actual = copyReport.remove(i);
                    boolean isValidCopy = false;
                    for (int j = 0; j < copyReport.size() - 1; j++) {
                        isValidCopy = isValidReport(copyReport);
                    }
                    if (isValidCopy) {
                        isValid = true;
                        break;
                    }
                    copyReport.add(i, actual);
                }
            }

            if (isValid) {
                safeCount++;
            }
        }

        System.out.println(safeCount + " reports are safe");
    }

    private static boolean isValidReport(List<Integer> report) {
        boolean isDecreasing = false;
        boolean isIncreasing = false;
        boolean isValid = true;
        for (int j = 0; j < report.size() - 1; j++) {
            if (report.get(j) > report.get(j + 1)) {
                isDecreasing = true;
            }
            if (report.get(j) < report.get(j + 1)) {
                isIncreasing = true;
            }
            int diff = Math.abs(report.get(j) - report.get(j + 1));
            if ((diff < 1 || diff > 3) || (isDecreasing && isIncreasing)) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }
}
