"use strict";

const fs = require("fs");
const path = "../../resources/day06/example.txt";

const readFile = (fs, path) => {
    return fs.readFileSync(path, "utf8")
        .split("\n")
        .slice(0, -1);
};

console.log(readFile(fs, path));
