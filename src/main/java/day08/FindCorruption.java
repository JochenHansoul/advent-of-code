/*
--- Part Two ---

After some careful analysis, you believe that exactly one instruction is corrupted.
Somewhere in the program, either a jmp is supposed to be a nop, or a nop is supposed to be a jmp.
(No acc instructions were harmed in the corruption of this boot code.)

The program is supposed to terminate by attempting to execute an instruction immediately after the last instruction in
the file. By changing exactly one jmp or nop, you can repair the boot code and make it terminate correctly.

For example, consider the same program from above:
nop +0
acc +1
jmp +4
acc +3
jmp -3
acc -99
acc +1
jmp -4
acc +6

If you change the first instruction from nop +0 to jmp +0, it would create a single-instruction infinite loop, never
leaving that instruction. If you change almost any of the jmp instructions, the program will still eventually find
another jmp instruction and loop forever.
However, if you change the second-to-last instruction (from jmp -4 to nop -4), the program terminates! The instructions
are visited in this order:
nop +0  | 1
acc +1  | 2
jmp +4  | 3
acc +3  |
jmp -3  |
acc -99 |
acc +1  | 4
nop -4  | 5
acc +6  | 6

After the last instruction (acc +6), the program terminates by attempting to run the instruction below the last
instruction in the file. With this change, after the program terminates, the accumulator contains the value 8
(acc +1, acc +1, acc +6).

Fix the program so that it terminates normally by changing exactly one jmp (to nop) or nop (to jmp). What is the value
of the accumulator after the program terminates?
 */

package day08;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.TreeSet;

public class FindCorruption {
    public static void main(String[] args) {
        final Path PATH = Paths.get("src/main/resources/day08/boot_code.txt");


        ArrayList<String> instructions = new ArrayList<>();
        ArrayList<Integer> arguments = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(PATH)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineParts = line.split(" ");
                instructions.add(lineParts[0]);
                arguments.add(Integer.parseInt(lineParts[1]));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // changing exactly one jmp (to nop) or nop (to jmp)
        int size = instructions.size();

        int accumulator = 0;
        TreeSet<Integer> alreadyAddedSteps = new TreeSet<>();
        int i = 0;
        while (i < instructions.size()) {
            if (alreadyAddedSteps.contains(i)) {
                i = instructions.size();
            } else {
                alreadyAddedSteps.add(i);
                String instruction = instructions.get(i);
                if (instruction.equals("acc")) {
                    accumulator += arguments.get(i);
                    i++;
                } else if (instruction.equals("jmp")) {
                    i += arguments.get(i);
                } else if (instruction.equals("nop")) {
                    i++;
                }
            }
        }

        System.out.println("value accumulator: " + accumulator);
    }
}
