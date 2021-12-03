"use strict";

const fs = require("fs");
const path = "../../resources/day03/example.txt";

const readFile = (fs, path) => {
    let instructions = fs.readFileSync(path,"utf8")
        .split("\n")
    instructions.pop();
    return instructions;
};
