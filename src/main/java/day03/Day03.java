package day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 {
    public static void main(String[] args) {

        try {
            int result = 0;
            BufferedReader reader = new BufferedReader(new FileReader("input/day03.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    result += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
                }
            }

            System.out.println("Result: " + result);

            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
