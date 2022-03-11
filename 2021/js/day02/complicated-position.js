"use strict";

const fs = require("fs");
const path = "../../resources/day02/input.txt";

const readFile = (fs, path) => {
    let instructions = fs.readFileSync(path,"utf8")
        .split("\n")
    instructions.pop();
    return instructions;
};

const getInstructions = array => {
    return array
        .map((x) => x.split(" "))
        .map((array) => {
            return {
                action: array[0],
                amount: parseInt(array[1])
            }
        });
};

/*
down X increases your aim by X units.
up X decreases your aim by X units.
forward X does two things:
    It increases your horizontal position by X units.
    It increases your depth by your aim multiplied by X.

Again note that since you're on a submarine, down and up do the opposite of
what you might expect: "down" means aiming in the positive direction.
*/
const getPosition = instructions => {
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
    return {
        horizontalPosition: horizontalPosition,
        depth: depth
    }
};

// start code
let instructions = getInstructions(readFile(fs, path));
let position = getPosition(instructions);
console.log(position.horizontalPosition * position.depth);
