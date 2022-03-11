"use strict";

const fs = require("fs");
const path = "../../resources/day08/input.txt";

const searchedNumbers = [2, 4, 3, 7];

const readFile = (fs, path) => {
    return fs.readFileSync(path, "utf8")
        .split("\n")
        .slice(0, -1);
};

const includedNumbers = (numbers, searchedNumbers) => {
    let counter = 0;
    for (const n of numbers) {
        if (searchedNumbers.includes(n)) {
            counter++;
        }
    }
    return counter;
};

const includedNumbersOfMatrix = (matrix, searchedNumbers) => {
    let result = 0;
    for (let numbers of matrix) {
        result += includedNumbers(numbers, searchedNumbers);
    }
    return result;
}

/*
input:
ten unique signal patterns, a | delimiter, and finally the four digit output value

each use a unique number of segments:
1   2
4   4
7   3
8   7

question:
In the output values, how many times do digits 1, 4, 7, or 8 appear?
*/

// start code
const input = readFile(fs, path)
    .map((x) => {
        const array = x.split(" | ");
        return {
            pattern: array[0].split(/ /g).map((x) => x.length),
            output: array[1].split(/ /g).map((x) => x.length)
        };
    });

const outputNumbers = input.map((x) => x.output);
//console.log("searched numbers:", searchedNumbers);
//console.log(outputNumbers);
console.log(includedNumbersOfMatrix(outputNumbers, searchedNumbers));
