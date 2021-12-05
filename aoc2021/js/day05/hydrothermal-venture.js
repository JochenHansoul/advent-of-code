"use strict";

const fs = require("fs");
const path = "../../resources/day05/input.txt";

const readFile = (fs, path) => {
    return fs.readFileSync(path, "utf8")
        .split("\n")
        .slice(0, -1);
};

const parseCoordinate = stringCoordinate => {
    const array = stringCoordinate.split(",");
    return {x: parseInt(array[0]), y: parseInt(array[1])};
};

const isHorizontalOrVertical = (coordinate1, coordinate2) => {
    return coordinate1.x === coordinate2.x || coordinate1.y === coordinate2.y;
};

const markLine = (diagram, coordinate1, coordinate2) => {
    if (coordinate1.x === coordinate2.x) {
        // mark y axis
        const highest = Math.max(coordinate1.y, coordinate2.y);
        const lowest = Math.min(coordinate1.y, coordinate2.y);
        for (let i = lowest; i <= highest; i++) {
            diagram[coordinate1.x][i]++;
        }
    } else {
        // mark x axis
        const highest = Math.max(coordinate1.x, coordinate2.x);
        const lowest = Math.min(coordinate1.x, coordinate2.x);
        for (let i = lowest; i <= highest; i++) {
            diagram[i][coordinate1.y]++;
        }
    }
};

const markDiagram = (diagram, coordinates) => {
    for (const thermalLine of coordinates) {
        if (isHorizontalOrVertical(thermalLine[0], thermalLine[1])) {
            markLine(diagram, thermalLine[0], thermalLine[1]);
        }
    }
};

const findCrossings = diagram => {
    let sum = 0;
    for (let row of diagram) {
        for (let n of row) {
            if (n > 1) {
                sum++;
            }
        }
    }
    return sum;
};


// start code
const lines = readFile(fs, path);
// getting coordinates
const ventCoordinates = lines.map((x) => {
    const arrayCoordinates = x.split(/ -> /g);
    return [parseCoordinate(arrayCoordinates[0]), parseCoordinate(arrayCoordinates[1])];
    });

// creating diagram
const diagram = [];
for (let i = 0; i < 999; i++) {
    diagram.push(new Array(999).fill(0));
};

markDiagram(diagram, ventCoordinates);
console.log(findCrossings(diagram));
