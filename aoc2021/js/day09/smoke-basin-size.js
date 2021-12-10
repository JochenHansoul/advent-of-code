"use strict";

const fs = require("fs");
const path = "../../resources/day09/example.txt";

const readFile = (fs, path) => {
    return fs.readFileSync(path, "utf8")
        .split("\n")
        .slice(0, -1);
};

const createMatrix = (width, height = width) => {
    return Array.from({
        length: height },
        () => (new Array(width).fill(0)));
};

const getLowPoints = points => {
    const matrix = createMatrix(points[0].length, points.length);
    // i vertical
    // j horizontal
    for (let i = 0; i < points.length; i++) {
        for (let j = 0; j < points[0].length; j++) {
            // clockwise starting from upper position
            if (points[i][j] < 9) {
                    matrix[i][j]++;
            }
        }
    }
    return matrix;
};



// start code
const input = readFile(fs, path).map((x) => {
    return x.split("").map((y) => parseInt(y))
});
//console.log(input);
const lowPoints = getLowPoints(input);
console.log(lowPoints);
