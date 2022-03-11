"use strict";

const fs = require("fs");
const path = "../../resources/day03/input.txt";

const readFile = (fs, path) => {
    let instructions = fs.readFileSync(path, "utf8")
        .split("\n")
    instructions.pop();
    return instructions;
};

const getCommomBitIntern = (binaryStrings, b1, b2) => {
    let counter = 0;
    while (binaryStrings.length > 1) {
        let amountOfOnes = 0;
        for (let binary of binaryStrings) {
            amountOfOnes += parseInt(binary.charAt(counter));
        }
        let bin = (amountOfOnes < binaryStrings.length / 2) ? b1 : b2;
        for (let i = binaryStrings.length - 1; i >= 0; i--) {
            if (parseInt(binaryStrings[i].charAt(counter)) !== bin) {
                binaryStrings.splice(i, 1);
            }
        }
        counter++;
    }
    return binaryStrings[0];
}
// input is an array of binary strings
const getCommonBit = (binaryStrings, highest = true) => {
    if (highest) {
        return getCommomBitIntern(binaryStrings.slice(), 0, 1);
    } else {
        return getCommomBitIntern(binaryStrings.slice(), 1, 0);
    }
};

let lines = readFile(fs, path);
let oxygen = parseInt(getCommonBit(lines), 2);
let co2 = parseInt(getCommonBit(lines, false), 2);
console.log(oxygen * co2);
