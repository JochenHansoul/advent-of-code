"use strict";

const fs = require("fs");
const path = "../../resources/day01/input.txt";
//const path = "../../resources/day01/example.txt";

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

const slidingWindow = numbers => {
    let result = [];
    for (let i = 0; i < numbers.length - 2; i++) {
        result.push(numbers[i] + numbers[i + 1] + numbers[i + 2]);
    }
    return result;
};

let numbers = fs.readFileSync(path,"utf8")
    .split("\n")
    .map((x) => parseInt(x, 10));

let slidingNumbers = slidingWindow(numbers);
let result = increasedAmount(slidingNumbers)
console.log(result);
