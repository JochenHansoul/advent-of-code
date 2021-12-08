"use strict";

const fs = require("fs");
const path = "../../resources/day08/example.txt";

const readFile = (fs, path) => {
    return fs.readFileSync(path, "utf8")
        .split("\n")
        .slice(0, -1);
};

const lines = readFile(fs, path)
    .map((x) => {
        const array = x.split(" | ");
        return {
            pattern: array[0].split(/ /g).map((x) => x.length),
            output: array[1].split(/ /g).map((x) => x.length)
        };
    });
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

console.log(lines);
