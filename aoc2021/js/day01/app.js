"use strict";

const fs = require("fs");
const path = "../../resources/day01/input.txt";

let lines = fs.readFileSync(path,"utf8").split("\n");
let counter = 0;
let previousNumber = parseInt(lines.shift());
for (let line of lines) {
    let n = parseInt(line);
    if (previousNumber < n) {
        counter++;
    }
    previousNumber = n;
}
console.log(counter);
