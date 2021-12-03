"use strict";

const fs = require("fs");
const path = "../../resources/day03/input.txt";

const readFile = (fs, path) => {
    let instructions = fs.readFileSync(path,"utf8")
        .split("\n")
    instructions.pop();
    return instructions;
};

// input is a binary string
const getMostCommonBit = binaryStrings => {
    let numbers = new Array(binaryStrings[0].length).fill(0);
    for (let binary of binaryStrings) {
        for (let i = 0; i < binary.length; i++) {
            numbers[i] += parseInt(binary.charAt(i));
        }
    }
    let binaryResult = "";
    for (let number of numbers) {
        binaryResult += (number < (binaryStrings.length / 2)) ? "0" : "1";
    }
    return binaryResult;
};

/*let numbers = readFile(fs, path)
    .map((x) => parseInt(x, 2));
console.log(numbers);*/

let lines = readFile(fs, path);
let gammaRate = parseInt(getMostCommonBit(lines), 2);
let highestNumber = parseInt("1".repeat(lines[0].length), 2);
let epsilonRate = highestNumber - gammaRate;
console.log(gammaRate * epsilonRate);
