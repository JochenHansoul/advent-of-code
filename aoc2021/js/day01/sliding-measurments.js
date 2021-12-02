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

const slidingWindow = (numbers, amount = 2) => {
    let result = [];
    for (let i = 0; i < numbers.length - (amount - 1); i++) {
        let sum = 0;
        for (let j = 0; j < amount; j++) {
            sum += numbers[i + j];
        }
        result.push(sum);
    }
    return result;
};

let numbers = fs.readFileSync(path,"utf8")
    .split("\n")
    .map((x) => parseInt(x, 10));
numbers.pop();

numbers = slidingWindow(numbers, 3);
console.log(increasedAmount(numbers));
