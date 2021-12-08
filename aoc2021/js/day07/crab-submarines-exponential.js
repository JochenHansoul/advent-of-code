"use strict";

const fs = require("fs");
const path = "../../resources/day07/input.txt";

const readFile = (fs, path) => {
    return fs.readFileSync(path, "utf8")
        .split("\n")
        .slice(0, -1);
};

// increases in steps of 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, 66, 78
const getIncreasingDistance = (positions, destination) => {
    let distance = 0;
    for (const position of positions) {
        const result = Math.abs(position - destination);
        let sum = 0;
        for (let i = 1; i <= result; i++) {
            sum += i;
        }
        distance += sum;
    }
    return distance;
};


// start code
const submarines = readFile(fs, path)[0].split(/,/g)
    .map((x) => parseInt(x, 10));

let counter = 0;
let previousNumber = getIncreasingDistance(submarines, counter) + 1;
let number = getIncreasingDistance(submarines, counter);
while (number < previousNumber) {
    previousNumber = number;
    number = getIncreasingDistance(submarines, ++counter);
}
console.log(previousNumber);
