package day02;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PasswordValidateAmount {
    public static void main(String[] args) {
        int validPasswords = 0;
        Path path = Paths.get("src/main/resources/day02/passwords.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineArray = line.split(" ");
                String[] minAndMax = lineArray[0].split("-");

                int min = Integer.parseInt(minAndMax[0]);
                int max = Integer.parseInt(minAndMax[1]);
                char c = lineArray[1].toCharArray()[0];
                String password = lineArray[2];

                int numberOfChars = password.length() - password.replaceAll("[" + c + "]", "").length();
                if (numberOfChars >= min && numberOfChars <= max) {
                    validPasswords++;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(validPasswords);
    }
}
