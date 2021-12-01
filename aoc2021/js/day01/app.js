"use strict";

const fs = require("fs");
const path = "../../resources/day01/input.txt";

const increasedAmount = numbers => {
    let counter = 0;
    let previousNumber = numbers.shift();
    for (let n of numbers) {
        if (previousNumber < n) {
            counter++;
        }
        previousNumber = n;
    }
    return counter;
};

let lines = fs.readFileSync(path,"utf8").split("\n");
let numbers = lines.map(function (x) { 
    return parseInt(x, 10); 
});

console.log(increasedAmount(numbers));
