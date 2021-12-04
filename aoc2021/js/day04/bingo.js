"use strict";

const fs = require("fs");
const path = "../../resources/day04/example.txt";

const readFile = (fs, path) => {
    let instructions = fs.readFileSync(path, "utf8")
        .split("\n")
    instructions.pop();
    return instructions;
};

const getBingo = stringBingo => {
    return stringBingo.map((x) => {
            return x.trim().replace(/  /g, " ")
                .split(" ")
                .map((y) => parseInt(y, 10));
        });
};

const getBingos = (array, vertical) => {
    const output = [];
    output.push(getBingo(array.splice(0, vertical)));
    while (array.length > 0) {
        array.shift();
        output.push(getBingo(array.splice(0, vertical)));
    }
    return output;
};

let lines = readFile(fs, path);
let numbers = lines.shift()
    .split(",")
    .map((x) => parseInt(x, 10));
lines.shift();

let bingos = getBingos(lines, 5);
console.log(bingos);
