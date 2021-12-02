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

/*
down X increases your aim by X units.
up X decreases your aim by X units.
forward X does two things:
    It increases your horizontal position by X units.
    It increases your depth by your aim multiplied by X.

Again note that since you're on a submarine, down and up do the opposite of
what you might expect: "down" means aiming in the positive direction.
*/
let aim = 0;
let horizontalPosition = 0;
let depth = 0;
for (let instruction of instructions) {
    if (instruction.action === "down") {
        aim += instruction.amount;
    } else if (instruction.action === "up") {
        aim -= instruction.amount;
    } else {
        horizontalPosition += instruction.amount;
        depth += aim * instruction.amount;
    }
}
console.log(horizontalPosition * depth);
