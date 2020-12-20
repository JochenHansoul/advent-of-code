package day02;

/*While it appears you validated the passwords correctly, they don't seem to be what the Official Toboggan Corporate
Authentication System is expecting.
The shopkeeper suddenly realizes that he just accidentally explained the password policy rules from his old job
at the sled rental place down the street! The Official Toboggan Corporate Policy actually works a little differently.

Each policy actually describes two positions in the password, where 1 means the first character,
2 means the second character, and so on. (Be careful; Toboggan Corporate Policies have no concept of "index zero"!)
Exactly one of these positions must contain the given letter.
Other occurrences of the letter are irrelevant for the purposes of policy enforcement.

Given the same example.txt list from above:
1-3 a: abcde is valid: position 1 contains a and position 3 does not.
1-3 b: cdefg is invalid: neither position 1 nor position 3 contains b.
2-9 c: ccccccccc is invalid: both position 2 and position 9 contain c.

How many passwords are valid according to the new interpretation of the policies?
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PasswordValidatePositions {
    public static void main(String[] args) {
        int validPasswords = 0;
        Path path = Paths.get("src/main/resources/day02/passwords.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineArray = line.split(" ");
                String[] minAndMax = lineArray[0].split("-");

                int firstPosition = Integer.parseInt(minAndMax[0]) - 1;
                int secondPosition = Integer.parseInt(minAndMax[1]) - 1;
                char c = lineArray[1].toCharArray()[0];
                String password = lineArray[2];
                if (password.charAt(firstPosition) == c
                        && (password.length() <= secondPosition || password.charAt(secondPosition) != c) // checking first character
                        || password.charAt(firstPosition) != c && (password.length() > secondPosition // checking second character
                        && password.charAt(secondPosition) == c)) {
                    validPasswords++;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(validPasswords);
    }
}