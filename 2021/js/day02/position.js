"use strict";

const fs = require("fs");
const path = "../../resources/day02/input.txt";

let instructions = fs.readFileSync(path,"utf8")
    .split("\n")
    .map((x) => x.split(" "))
    .map((array) => {
        return {
            action: array[0],
            amount: parseInt(array[1])
        }
    });
instructions.pop();

let horizontalPosition = 0;
let depth = 0;
for (let instruction of instructions) {
    if (instruction.action === "forward") {
        horizontalPosition += instruction.amount;
    } else if (instruction.action === "up") {
        depth -= instruction.amount;
    } else {
        depth += instruction.amount;
    }
}
console.log(horizontalPosition * depth);
