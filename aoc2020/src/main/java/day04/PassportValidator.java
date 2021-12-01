package day04;

/*
--- Day 4: Passport Processing ---

You arrive at the airport only to realize that you grabbed your North Pole Credentials instead of your passport.
While these documents are extremely similar, North Pole Credentials aren't issued by a country and therefore aren't
actually valid documentation for travel in most of the world.
It seems like you're not the only one having problems, though; a very long line has formed for the automatic passport
scanners, and the delay could upset your travel itinerary.
Due to some questionable network security, you realize you might be able to solve both of these problems at the same time.
The automatic passport scanners are slow because they're having trouble detecting which passports have all required
fields.

The expected fields are as follows:
    byr (Birth Year)
    iyr (Issue Year)
    eyr (Expiration Year)
    hgt (Height)
    hcl (Hair Color)
    ecl (Eye Color)
    pid (Passport ID)
    cid (Country ID)

Passport data is validated in batch files (your puzzle input). Each passport is represented as a sequence of key:value
pairs separated by spaces or newlines. Passports are separated by blank lines.

Here is an example.txt batch file containing four passports:

ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
byr:1937 iyr:2017 cid:147 hgt:183cm

iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
hcl:#cfa07d byr:1929

hcl:#ae17e1 iyr:2013
eyr:2024
ecl:brn pid:760753108 byr:1931
hgt:179cm

hcl:#cfa07d eyr:2025 pid:166559648
iyr:2011 ecl:brn hgt:59in

The first passport is valid - all eight fields are present. The second passport is invalid - it is missing hgt
(the Height field).

The third passport is interesting; the only missing field is cid, so it looks like data from North Pole Credentials,
not a passport at all! Surely, nobody would mind if you made the system temporarily ignore missing cid fields. Treat
this "passport" as valid.

The fourth passport is missing two fields, cid and byr. Missing cid is fine, but missing any other field is not, so
this passport is invalid.

According to the above rules, your improved system would report 2 valid passports.

Count the number of valid passports - those that have all required fields. Treat cid as optional. In your batch file, how many passports are valid?
*/

/*
fout: input is to low (guessed 249)

De eerste keer was fout omdat het laatste paspoort niet bji de lijst werd opgeteld.
Het was ook mogelijk om een extra lijn (twee lege lijnen) op het einde toe te voegen maar dat vond ik risicovol
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class PassportValidator {
    public static void main(String[] args) {
        final String[] REQUIRED_FIELDS = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"}; // cid is optional
        final String OPTIONAL_FIELD = "cid";
        final int MINIMUM_FIELDS = REQUIRED_FIELDS.length;

        Path path = Paths.get("src/main/resources/day04/passports.txt");
        //Path path = Paths.get("src/main/resources/day04/example.txt");

        ArrayList<String[]> passwords = new ArrayList<>();
        int validPassportCounter = 0;

        try (BufferedReader reader = Files.newBufferedReader(path)) {
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

        for (String[] password : passwords) { // arrays of key:value key:value key:valye of one passport
            if (password.length >= MINIMUM_FIELDS) {
                ArrayList<String> keysPassport = new ArrayList<>();
                for (String keyAndValue : password) {
                    keysPassport.add(keyAndValue.split(":")[0]);
                }
                if (keysPassport.contains(REQUIRED_FIELDS[0])
                        && keysPassport.contains(REQUIRED_FIELDS[1])
                        && keysPassport.contains(REQUIRED_FIELDS[2])
                        && keysPassport.contains(REQUIRED_FIELDS[3])
                        && keysPassport.contains(REQUIRED_FIELDS[4])
                        && keysPassport.contains(REQUIRED_FIELDS[5])
                        && keysPassport.contains(REQUIRED_FIELDS[6])
                ) {
                    validPassportCounter++;
                }
            }
        }
        System.out.println("valid: " + validPassportCounter);
    }
}