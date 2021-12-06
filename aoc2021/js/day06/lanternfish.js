"use strict";

const fs = require("fs");
const path = "../../resources/day06/example.txt";

const readFile = (fs, path) => {
    return fs.readFileSync(path, "utf8")
        .split("\n")
        .slice(0, -1);
};

const multiplyFish = (fishes, days = 1) => {
    for (let i = 0; i < days; i++) {
        const length = fishes.length;
        for (let j = 0; j < fishes.length; j++) {
            if (fishes[j] === 0) {
                fishes[j] = 6;
                fishes.push(9);
            } else {
                fishes[j]--;
            }
        }
    }
};

const fishes = readFile(fs, path)[0].split(/,/g)
    .map((x) => parseInt(x, 10));
multiplyFish(fishes, 80);
console.log(fishes.length);
