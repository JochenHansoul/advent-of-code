package day04;

/*
--- Part Two ---

The line is moving more quickly now, but you overhear airport security talking about how passports with invalid data
are getting through. Better add some data validation, quick!
You can continue to ignore the cid field, but each other field has strict rules about what values are valid for
automatic validation:

    byr (Birth Year) - four digits; at least 1920 and at most 2002.
    iyr (Issue Year) - four digits; at least 2010 and at most 2020.
    eyr (Expiration Year) - four digits; at least 2020 and at most 2030.

    hgt (Height) - a number followed by either cm or in:
        If cm, the number must be at least 150 and at most 193.
        If in, the number must be at least 59 and at most 76.
    hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
    ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
    pid (Passport ID) - a nine-digit number, including leading zeroes.
    cid (Country ID) - ignored, missing or not.

Your job is to count the passports where all required fields are both present and valid according to the above rules.
Here are some example values:
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ExpertPassportValidator {
    public static void main(String[] args) {

        Path PATH = Paths.get("src/main/resources/day04/passports.txt");
        //Path PATH = Paths.get("src/main/resources/day04/example.txt");

        // fields
        final String[] REQUIRED_FIELDS = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"}; // cid is optional

        // values
        final int MIN_BIRTH_YEAR = 1920;
        final int MAX_BIRTH_YEAR = 2002;
        final int MIN_ISSUE_YEAR = 2010;
        final int MAX_ISSUE_YEAR = 2020;
        final int MIN_EXPIRATION_YEAR = 2020;
        final int MAX_EXPIRATION_YEAR = 2030;
        final int MIN_CM_HEIGHT = 150;
        final int MAX_CM_HEIGHT = 193;
        final int MIN_IN_HEIGHT = 59;
        final int MAX_IN_HEIGHT = 76;
        final String[] VALID_EYE_COLORS = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};

        ArrayList<String[]> passwords = new ArrayList<>();
        int validPassportCounter = 0;

        // read file
        try (BufferedReader reader = Files.newBufferedReader(PATH)) {
            String line;
            StringBuilder passportBuilder = new StringBuilder();
            StringBuilder lastPasswordBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                if (!line.equals("")) {
                    passportBuilder.append(line).append(" ");
                    lastPasswordBuilder.append(line).append(" "); // only meant for adding the last passport
                } else {
                    passwords.add(passportBuilder.toString().trim().split(" ")); // removing last " "
                    lastPasswordBuilder.setLength(0);
                    passportBuilder.setLength(0);
                }
            }
            passwords.add(lastPasswordBuilder.toString().trim().split(" "));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("total passports: " + passwords.size());

        // checking keys and values of each password
        for (String[] password : passwords) { // arrays of key:value key:value key:valye of one passport
            if (password.length >= REQUIRED_FIELDS.length) {
                ArrayList<String> keysList = new ArrayList<>();
                ArrayList<String> valuesList = new ArrayList<>();
                for (String keyAndValue : password) {
                    keysList.add(keyAndValue.split(":")[0]);
                    valuesList.add(keyAndValue.split(":")[1]);
                }
                // checking keys
                boolean containsAllRequiredKeys = true;
                int counter = 0;
                while (counter < REQUIRED_FIELDS.length && containsAllRequiredKeys) {
                    if (!keysList.contains(REQUIRED_FIELDS[counter])) {
                        containsAllRequiredKeys = false;
                    }
                    counter++;
                }
                if (containsAllRequiredKeys) {
                    // if all required keys are present: checking values
                    boolean correctPassport = true;
                    counter = 0; // Ik weet niet of het een goed idee is om de zelfde counter opnieuw te gebruiken
                    while (counter < keysList.size() && correctPassport) {
                        String key = keysList.get(counter);
                        String value = valuesList.get(counter);
                        if (key.equals("byr")) {
                            correctPassport = checkYear(value, MIN_BIRTH_YEAR, MAX_BIRTH_YEAR);
                        } else if (key.equals("iyr")) {
                            correctPassport = checkYear(value, MIN_ISSUE_YEAR, MAX_ISSUE_YEAR);
                        } else if (key.equals("eyr")) {
                            correctPassport = checkYear(value, MIN_EXPIRATION_YEAR, MAX_EXPIRATION_YEAR);
                        } else if (key.equals("hgt")) {
                            int lengthOfNumbers = value.length() - 2;
                            String unit = value.substring(lengthOfNumbers);
                            if (unit.equals("cm") || unit.equals("in")) {
                                int height = Integer.parseInt(value.substring(0, lengthOfNumbers));
                                correctPassport = (unit.equals("cm"))
                                        ? height >= MIN_CM_HEIGHT && height <= MAX_CM_HEIGHT
                                        : height >= MIN_IN_HEIGHT && height <= MAX_IN_HEIGHT;
                            } else {
                                correctPassport = false;
                            }
                        } else if (key.equals("hcl")) {
                            // cl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
                            if (value.charAt(0) == '#' && value.length() == 7) {
                                char[] characters = value.substring(1).toCharArray();
                                int characterCounter = 0;
                                while (characterCounter < characters.length && correctPassport) {
                                    char currentCharacter = characters[characterCounter];
                                    correctPassport = Character.isLetterOrDigit(currentCharacter)
                                            && (Character.isDigit(currentCharacter)
                                            || (currentCharacter >= 'a' && currentCharacter <= 'f'));
                                    characterCounter++;
                                }
                            } else {
                                correctPassport = false;
                            }
                        } else if (key.equals("ecl")) {
                            boolean eyeColorIsValid = false;
                            int eyeColorCounter = 0;
                            while (eyeColorCounter < VALID_EYE_COLORS.length && !eyeColorIsValid) {
                                eyeColorIsValid = value.equals(VALID_EYE_COLORS[eyeColorCounter]);
                                eyeColorCounter++;
                            }
                            correctPassport = eyeColorIsValid;
                        } else if (key.equals("pid")) {
                            correctPassport = value.length() == 9
                                    && value.replaceAll("[0-9]", "").length() == 0;
                        }
                        counter++;
                    }
                    if (correctPassport) {
                        validPassportCounter++;
                    }
                }
            }
        }
        System.out.println("valid: " + validPassportCounter);
        // 282: number is to hight
        // error line 110 passport.txt
        // 157 (no in or cm after height) there was an error were they forgot to ad "cm" or "in" after the lingth
        // ik heb de invoerwaarden nog eens bekeken en het vergeten van "cm" en "in" was toch niet vergeten maar was een bewuste invoerfout
        // uiteindelijk was het antwoord 158. Ik ad ergens een fout gemaakt bij de <, >= && || juiste lengtes van de hoogten
    }

    private static boolean checkYear(String year, int min, int max) {
        int birthYear = Integer.parseInt(year);
        return year.length() == 4 && birthYear >= min && birthYear <= max;
    }
}