"use strict";

const fs = require("fs");
const path = "../../resources/day07/example.txt";

const readFile = (fs, path) => {
    return fs.readFileSync(path, "utf8")
        .split("\n")
        .slice(0, -1);
};


// start code
const submarines = readFile(fs, path)[0].split(/,/g)
    .map((x) => parseInt(x, 10));
console.log(submarines);
