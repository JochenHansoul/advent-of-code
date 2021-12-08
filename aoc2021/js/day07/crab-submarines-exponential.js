"use strict";

const fs = require("fs");
const path = "../../resources/day07/input.txt";

const readFile = (fs, path) => {
    return fs.readFileSync(path, "utf8")
        .split("\n")
        .slice(0, -1);
};

const getDistance = (positions, destination) => {
    let distance = 0;
    for (let position of positions) {
        distance += Math.abs(position - destination);
    }
    return distance;
};


// start code
const submarines = readFile(fs, path)[0].split(/,/g)
    .map((x) => parseInt(x, 10));
//const sum = submarines.reduce((a, b) => a + b);
//const average = Math.round(sum / submarines.length);
let counter = 0;
let previousNumber = getDistance(submarines, counter) + 1;
let number = getDistance(submarines, counter);
while (number < previousNumber) {
    previousNumber = number;
    number = getDistance(submarines, ++counter);
}
console.log(previousNumber);

// 353240 (too high)
