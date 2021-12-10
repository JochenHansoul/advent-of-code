"use strict";

const fs = require("fs");
const path = "../../resources/day09/input.txt";

const readFile = (fs, path) => {
    return fs.readFileSync(path, "utf8")
        .split("\n")
        .slice(0, -1);
};

const createMatrix = (width, height = width) => {
    return Array.from(
        { length: height },
        () => new Array(width).fill(0)
    );
};

const getRiskLevel = points => {
    //const matrix = createMatrix(points[0].length, points.length);
    let riskLevel = 0;
    // i vertical
    // j horizontal
    for (let i = 0; i < points.length; i++) {
        for (let j = 0; j < points[0].length; j++) {
            const point = points[i][j];
            // clockwise starting from upper position
            if ((i === 0 || point < points[i - 1][j])
                && (j === (points[i].length - 1) || point < points[i][j + 1])
                && (i === (points.length - 1) || point < points[i + 1][j])
                && (j === 0 || point < points[i][j - 1])) {
                    //matrix[i][j]++;
                    riskLevel += points[i][j] + 1;
            }
        }
    }
    return riskLevel;
};



// start code
const input = readFile(fs, path).map((x) => {
    return x.split("").map((y) => parseInt(y))
});
//console.log(input);
const riskLevel = getRiskLevel(input);
console.log(riskLevel);
