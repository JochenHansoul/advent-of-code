package day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReportRepair {
    public static void main(String[] args) {
        final int SUM = 2020;
        List<Integer> numbers = new ArrayList<>();

        //Path path = Paths.get("/home/jochenhansoul/Projects/eigen/Java/2020_adventOfCode/src/main/resources/expense_report.txt"); //Paths.get("/expense_report.txt");
        Path path = Paths.get("src/main/resources/day01/expense_report.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                /*if (!line.equals("")) {
                    numbers.add(Integer.parseInt(line));
                }*/
                numbers.add(Integer.parseInt(line)); // the file may not contain more then one "end of line" at the end of the file
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // first part
        System.out.println("first part:");
        for (int i = 0; i < numbers.size(); i++) {
            int nr1 = numbers.get(i);
            for (int j = i + 1; j < numbers.size(); j++) {
                int nr2 = numbers.get(j);
                if ((nr1 + nr2) == SUM) {
                    System.out.printf("%s + %s = %s%nProduct: %s%n", nr1, nr2, SUM, nr1 * nr2);
                }
            }
        }

        // second part
        System.out.println("second part:");
        for (int i = 0; i < numbers.size(); i++) {
            int nr1 = numbers.get(i);
            for (int j = i + 1; j < numbers.size(); j++) {
                int nr2 = numbers.get(j);
                for (int k = j + 1; k < numbers.size(); k++) {
                    int nr3 = numbers.get(k);
                    if ((nr1 + nr2 + nr3) == SUM) {
                        System.out.printf("%s + %s + %s = %s%nProduct: %s%n", nr1, nr2, nr3, SUM, nr1 * nr2 * nr3);
                    }
                }
            }
        }
    }
}