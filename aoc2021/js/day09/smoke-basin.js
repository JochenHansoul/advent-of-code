"use strict";

const fs = require("fs");
const path = "../../resources/day09/example.txt";

const readFile = (fs, path) => {
    return fs.readFileSync(path, "utf8")
        .split("\n")
        .slice(0, -1);
};


// start code
const input = readFile(fs, path);
console.log(input);
