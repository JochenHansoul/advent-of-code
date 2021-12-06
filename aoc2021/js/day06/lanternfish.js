"use strict";

const fs = require("fs");
const path = "../../resources/day06/input.txt";

const readFile = (fs, path) => {
    return fs.readFileSync(path, "utf8")
        .split("\n")
        .slice(0, -1);
};

const getArrayOfFishesOrganisedByAge = ages => {
    const amountOfFishWithAge = [0, 0, 0, 0, 0, 0, 0, 0, 0]; // fishes aged 0 to 8
    for (let age of ages) {
        amountOfFishWithAge[age]++;
    }
    return amountOfFishWithAge;
};

const getMultipliedFish = (fishes, days = 1) => {
    const amountOfFishWithAge = getArrayOfFishesOrganisedByAge(fishes);
    for (let i = 0; i < days; i++) {
        const newFishes = amountOfFishWithAge[0];
        for (let j = 0; j < (amountOfFishWithAge.length - 1); j++) {
            amountOfFishWithAge[j] = amountOfFishWithAge[j + 1];
        }
        amountOfFishWithAge[amountOfFishWithAge.length - 1] = 0;
        amountOfFishWithAge[6] += newFishes;
        amountOfFishWithAge[8] += newFishes;
    }
    return amountOfFishWithAge;
};

const fishes = readFile(fs, path)[0].split(/,/g)
    .map((x) => parseInt(x, 10));
const amountOfFishWithAge = getMultipliedFish(fishes, 256);
console.log(amountOfFishWithAge.reduce((a, b) => a + b));
