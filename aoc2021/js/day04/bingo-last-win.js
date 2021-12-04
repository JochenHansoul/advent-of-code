"use strict";

const fs = require("fs");
const path = "../../resources/day04/input.txt";

const readFile = (fs, path) => {
    return fs.readFileSync(path, "utf8")
        .split("\n")
        .slice(0, -1);
};

const getBingo = stringBingo => {
    return stringBingo.map((x) => {
            return x.trim().replace(/  /g, " ")
                .split(" ")
                .map((y) => parseInt(y, 10));
        });
};

const getBingos = (array, vertical) => {
    const output = [];
    output.push(getBingo(array.splice(0, vertical)));
    while (array.length > 0) {
        array.shift();
        output.push(getBingo(array.splice(0, vertical)));
    }
    return output;
};

const getBoolArrays = (amount, hight, width) => {
    const boolArrays = [];
    for (let i = 0; i < amount; i++) {
        const array = [];
        for (let j = 0; j < hight; j++) {
            array.push(new Array(width).fill(false));
        }
        boolArrays.push(array);
    }
    return boolArrays;
};

const setBingo = (bingo, bool, nr) => {
    for (let i = 0; i < bingo.length; i++) {
        let row = bingo[i];
        let boolRow = bool[i];
        for (let j = 0; j < row.length; j++) {
            if (row[j] === nr) {
                boolRow[j] = true;
            }
        }
    }
};

const verifyBoolColumns = boolBingo => {
    let found = false;
    let counter = 0;
    while (!found && counter < boolBingo[0].length){
        found = true;
        for (let i = 0; i < boolBingo.length; i++) {
            if (!boolBingo[i][counter]) {
                found = false;
            }
        }
        counter++;
    }
    return found;
};

const verifyBoolRows = boolBingo => {
    let found = false;
    let counter = 0;
    while (!found && counter < boolBingo[0].length){
        found = true;
        for (let i = 0; i < boolBingo.length; i++) {
            if (!boolBingo[counter][i]) {
                found = false;
            }
        }
        counter++;
    }
    return found;
};

const verifyBoolBingo = boolBingo => {
    /*const functionPart = (x, y) => {
        let found = true;
        for (let i = 0; i < boolBingo.length; i++) {
            if (!boolBingo[x][y]) {
                found = false;
            }
        }
        return found;
    };*/
    return verifyBoolColumns(boolBingo) || verifyBoolRows(boolBingo);
};

/*
* bingos array of bingo numbers
* numbers array of winning numbers
* result: an array of the winning bingo and the winning numbers
*/
const getLastWinningBingo = (bingos, numbers) => {
    numbers = numbers.slice();
    const boolArrays = getBoolArrays(bingos.length, bingos[0].length, bingos[0][0].length);
    for (let i = 0; i < 4; i++) {
        for (let j = 0; j < bingos.length; j++) {
            setBingo(bingos[j], boolArrays[j], numbers[0]);
        }
        numbers.shift();
    }
    let lastBingo;
    let lastBingoBool;
    let winningNumber;
    while (bingos.length !== 0) {
        let counter = 0;
        while (counter < bingos.length) {
            setBingo(bingos[counter], boolArrays[counter], numbers[0]);
            if (verifyBoolBingo(boolArrays[counter])) {
                lastBingo = bingos.splice(counter, 1)[0];
                lastBingoBool = boolArrays.splice(counter, 1)[0];
                counter--;
            }
            counter++;
        }
        winningNumber = numbers.shift();
    }
    return [lastBingo, lastBingoBool, winningNumber];
};

const getBingoScore = (bingo, boolMatrix, winningNumber) => {
    let nr = 0;
    for (let i = 0; i < bingo.length; i++) {
        for (let j = 0; j < bingo[i].length; j++) {
            if (!boolMatrix[i][j]) {
                nr += bingo[i][j];
            }
        }
    }
    return nr * winningNumber;
};

let lines = readFile(fs, path);
let numbers = lines.shift().split(",")
    .map((x) => parseInt(x, 10));
lines.shift();

let bingos = getBingos(lines, 5);
let last = getLastWinningBingo(bingos, numbers);
/*console.log(last[0]);
console.log(last[1]);
console.log(last[2]);*/
console.log(getBingoScore(last[0], last[1], last[2]));
