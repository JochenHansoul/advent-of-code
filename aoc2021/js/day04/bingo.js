"use strict";

const fs = require("fs");
const path = "../../resources/day04/example.txt";

const readFile = (fs, path) => {
    let instructions = fs.readFileSync(path, "utf8")
        .split("\n")
    instructions.pop();
    return instructions;
};

let lines = readFile(fs, path);
console.log(lines);
