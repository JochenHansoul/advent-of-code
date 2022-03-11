/*
--- Part Two ---

For some reason, the sea port's computer system still can't communicate with your ferry's docking program. It must be
using version 2 of the decoder chip!

A version 2 decoder chip doesn't modify the values being written at all. Instead, it acts as a memory address decoder.
Immediately before a value is written to memory, each bit in the bitmask modifies the corresponding bit of the
destination memory address in the following way:
    If the bitmask bit is 0, the corresponding memory address bit is unchanged.
    If the bitmask bit is 1, the corresponding memory address bit is overwritten with 1.
    If the bitmask bit is X, the corresponding memory address bit is floating.

A floating bit is not connected to anything and instead fluctuates unpredictably. In practice, this means the floating
bits will take on all possible values, potentially causing many memory addresses to be written all at once!

For example, consider the following program:
mask = 000000000000000000000000000000X1001X
mem[42] = 100
mask = 00000000000000000000000000000000X0XX
mem[26] = 1

When this program goes to write to memory address 42, it first applies the bitmask:
address: 000000000000000000000000000000101010  (decimal 42)
mask:    000000000000000000000000000000X1001X
result:  000000000000000000000000000000X1101X

After applying the mask, four bits are overwritten, three of which are different, and two of which are floating.
Floating bits take on every possible combination of values; with two floating bits, four actual memory addresses are
written:
000000000000000000000000000000011010  (decimal 26)
000000000000000000000000000000011011  (decimal 27)
000000000000000000000000000000111010  (decimal 58)
000000000000000000000000000000111011  (decimal 59)

Next, the program is about to write to memory address 26 with a different bitmask:
address: 000000000000000000000000000000011010  (decimal 26)
mask:    00000000000000000000000000000000X0XX
result:  00000000000000000000000000000001X0XX

This results in an address with three floating bits, causing writes to eight memory addresses:
000000000000000000000000000000010000  (decimal 16)
000000000000000000000000000000010001  (decimal 17)
000000000000000000000000000000010010  (decimal 18)
000000000000000000000000000000010011  (decimal 19)
000000000000000000000000000000011000  (decimal 24)
000000000000000000000000000000011001  (decimal 25)
000000000000000000000000000000011010  (decimal 26)
000000000000000000000000000000011011  (decimal 27)

The entire 36-bit address space still begins initialized to the value 0 at every address, and you still need the sum of
all values left in memory at the end of the program. In this example, the sum is 208.

Execute the initialization program using an emulator for a version 2 decoder chip. What is the sum of all values left in
memory after it completes?
 */

package day14;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class BitmaskChangedMemoryAddress {
    public static void main(String[] args) {
        final Path PATH = Paths.get("src/main/resources/day14/input.txt");
        final char FLOATING_SYMBOL = 'X';
        char[] currentBitmask = new char[0];
        ArrayList<Long> floatingValues = new ArrayList<>();
        HashMap<Long, Long> memoryValues = new HashMap<>();

        try (BufferedReader reader = Files.newBufferedReader(PATH)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.replaceAll("\\s", "").split("=");
                if (splitLine[0].equals("mask")) {
                    // getting floating values
                    String bitmask = splitLine[1];
                    currentBitmask = bitmask.toCharArray();
                    floatingValues.clear();
                    for (int i = 0; i < currentBitmask.length; i++) {
                        if (currentBitmask[i] == FLOATING_SYMBOL) {
                            floatingValues.add((long) Math.pow(2 , bitmask.length() - i - 1));
                        }
                    }
                } else {
                    // calculating new memory addresses and adding values to them
                    Long value = Long.parseLong(splitLine[1]);
                    int address = Integer.parseInt(splitLine[0].substring(4, splitLine[0].length() - 1));
                    char[] binaryAddress = StringUtils
                            .leftPad(Integer.toBinaryString(address), currentBitmask.length, '0')
                            .toCharArray();
                    // mask filter
                    for (int i = currentBitmask.length - 1; i >= 0; i--) {
                        binaryAddress[i] = (currentBitmask[i] == '0') ? binaryAddress[i]
                                : (currentBitmask[i] == '1') ? '1'
                                : '0';
                    }
                    // getting all floating memory addresses
                    ArrayList<Long> initialValue = new ArrayList<>();
                    initialValue.add(Long.parseLong(String.valueOf(binaryAddress), 2));
                    ArrayList<Long> floatingMemoryAddresses = getFloatingMemoryAddresses(initialValue, (ArrayList<Long>) floatingValues.clone());
                    // inserting values into memory addresses
                    for (long memoryAddress : floatingMemoryAddresses) {
                        memoryValues.put(memoryAddress, value);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        long sum = 0;
        for (long values : memoryValues.values()) {
            sum += values;
        }
        System.out.println(sum);
        // 625836681448 (wrong) too low. I couldn't run the example.txt file because it didn't fit on the heap
        // 4173715962894 (right)
    }


    private static ArrayList<Long> getFloatingMemoryAddresses(ArrayList<Long> values, ArrayList<Long> floatingValues) {
        if (floatingValues.size() == 0) {
            return values;
        } else {
            ArrayList<Long> newValues = new ArrayList<>();
            for (long value : values) {
                newValues.add(value);
                newValues.add(value + floatingValues.get(0));
            }
            floatingValues.remove(0);
            return getFloatingMemoryAddresses(newValues, floatingValues);
        }
    }
}
