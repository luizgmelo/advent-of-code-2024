package day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.*;

public class Day01 {
    public static void main(String[] args) {
        try {
           Path inputPath = Path.of("input/day01.txt");
           String input = Files.readString(inputPath);

            System.out.println("Day 1 Solution: " + solve(input));
        } catch (IOException e) {
            System.out.println("Error reading input: " + e.getMessage());
        }
    }

    private static int solve(String string) {
        String[] stringIds = string.split("\\s+");
        Integer[] leftIds = new Integer[stringIds.length / 2];
        Integer[] rightIds = new Integer[stringIds.length / 2];

        // separate left and right in two lists
        int j = 0;
        int k = 0;
        for (int i = 0; i < stringIds.length; i++) {
            if (i % 2 == 0) {
                leftIds[j] = Integer.parseInt(stringIds[i]);
                j++;
            } else {
                rightIds[k] = Integer.parseInt(stringIds[i]);
                k++;
            }
        }

        // convert the arrays to list
        List<Integer> leftIdsList = new ArrayList<>(Arrays.asList(leftIds));
        List<Integer> rightIdsList = new ArrayList<>(Arrays.asList(rightIds));

        // SOLUTION PART ONE
        // int total_distance = calculateTotalDistance(leftIdsList, rightIdsList, stringIds.length / 2);
        //return total_distance;

        // --- part two --
        return calculateSimilarityScore(leftIdsList, rightIdsList);
    }

    private static int getMin(List<Integer> nums) {
            Integer min = nums.get(0);
            int minIndex = 0;
            for (int i = 1;  i < nums.size(); i++) {
                if (nums.get(i) < min) {
                    min = nums.get(i);
                    minIndex = i;
                }
            }
            return minIndex;
    }

    private static int calculateTotalDistance(List<Integer> leftList, List<Integer> rightList, int size) {
        List<Integer> distances = new ArrayList<>();
        int total_distance = 0;
        // get the smallest left and right and pop it;

        for (int i = 0; i < size; i++) {
            Integer smallest_left = leftList.remove(getMin(leftList));
            Integer smallest_right = rightList.remove(getMin(rightList));

            int distance = Math.abs(smallest_left - smallest_right);
            distances.add(distance);
        }

        // get sum of all distances
        for (int dist : distances) {
            total_distance += dist;
        }

        return total_distance;
    }

    private static int calculateSimilarityScore(List<Integer> leftList, List<Integer> rightList) {
        // get each left in ascending order number in left;
        // when get this multiply by the number of times it repeat in right list
        List<Integer> scores = new ArrayList<>();
        for (int leftNum : leftList) {
            int leftRepeats = getRepeatsOnList(leftNum, rightList);
            scores.add(leftNum * leftRepeats);
        }

        // sum of all scores
        int similarityScore = 0;
        for (int score : scores) {
            similarityScore += score;
        }

        return similarityScore;
    }

    private static int getRepeatsOnList(int number, List<Integer> list) {
        HashMap<Integer, Integer> counter = new HashMap<>();

        for (int num : list) {
            if (counter.containsKey(num)) {
                counter.put(num, counter.get(num) + 1);
            } else {
                counter.put(num, 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            if (number == entry.getKey()) {
                return entry.getValue();
            }
        }

        return 0;
    }
}
