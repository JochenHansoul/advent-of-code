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

// get an array of matrixes that are filled with bools (false)
const getBoolMatrixes = (amount, hight, width) => {
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

const setBingo = (bingo, boolBingo, nr) => {
    for (let i = 0; i < bingo.length; i++) {
        let row = bingo[i];
        let boolRow = boolBingo[i];
        for (let j = 0; j < row.length; j++) {
            if (row[j] === nr) {
                boolRow[j] = true;
            }
        }
    }
};

const verifyBingoLines = (boolBingo, lineFunction) => {
    let found = false;
    let counter = 0;
    while (!found && counter < boolBingo[0].length){
        found = true;
        for (let i = 0; i < boolBingo.length; i++) {
            if (!lineFunction(boolBingo, counter, i)) {
                found = false;
            }
        }
        counter++;
    }
    return found;
};

const verifyBoolBingo = boolBingo => {
    const rowFunction = (boolBingo, x, y) => boolBingo[x][y];
    const columnFunction = (boolBingo, x, y) => boolBingo[y][x];
    return verifyBingoLines(boolBingo, rowFunction)
        || verifyBingoLines(boolBingo, columnFunction);
};

/*
* bingos array of bingo numbers
* numbers array of winning numbers
* result: an array of the winning bingo and the winning numbers
*/
const getLastWinningBingo = (bingos, numbers) => {
    numbers = numbers.slice();
    const boolArrays = getBoolMatrixes(bingos.length, bingos[0].length, bingos[0][0].length);
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

// start code
const lines = readFile(fs, path);
const numbers = lines.shift().split(",")
    .map((x) => parseInt(x, 10));
lines.shift();

const bingos = getBingos(lines, 5);
const last = getLastWinningBingo(bingos, numbers);
console.log(getBingoScore(last[0], last[1], last[2]));
