"use strict";

const fs = require("fs");
const path = "../../resources/day05/input.txt";

const readFile = (fs, path) => {
    return fs.readFileSync(path, "utf8")
        .split("\n")
        .slice(0, -1);
};

const isHorizontalOrVertical = (coordinate1, coordinate2) => {
    return coordinate1.x === coordinate2.x || coordinate1.y === coordinate2.y;
};

const parseCoordinate = stringCoordinate => {
    const array = stringCoordinate.split(",");
    return {x: parseInt(array[0]), y: parseInt(array[1])};
};

// unfinished
const markDiagramLine = (diagram, coordinate1, coordinate2, a, b) => {
    let lowest;
    let highest;
    if (coordinate1[a] < coordinate2[a]) {
        highest = coordinate2[a];
        lowest = coordinate1[a];
    } else {
        highest = coordinate1[a];
        lowest = coordinate2[a];
    }
    for (let i = lowest; i <= highest; i++) {
        diagram[coordinate1[b]][i]++;
    }
};

const markDiagram = (diagram, coordinate1, coordinate2) => {
    let lowest;
    let highest;
    if (coordinate1.x === coordinate2.x) {
        // mark y axis
        //markDiagramLine(diagram, coordinate1, coordinate2, "y", "x");
        if (coordinate1.y < coordinate2.y) {
            highest = coordinate2.y;
            lowest = coordinate1.y;
        } else {
            highest = coordinate1.y;
            lowest = coordinate2.y;
        }
        for (let i = lowest; i <= highest; i++) {
            diagram[coordinate1.x][i]++;
        }
    } else {
        // mark x axis
        //markDiagramLine(diagram, coordinate1, coordinate2, "x", "y");
        if (coordinate1.x < coordinate2.x) {
            highest = coordinate2.x;
            lowest = coordinate1.x;
        } else {
            highest = coordinate1.x;
            lowest = coordinate2.x;
        }
        for (let i = lowest; i <= highest; i++) {
            diagram[i][coordinate1.y]++;
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

// marking diagran
for (let i = 0; i < ventCoordinates.length; i++) {
    const thermalLine = ventCoordinates[i];
    if (isHorizontalOrVertical(thermalLine[0], thermalLine[1])) {
        markDiagram(diagram, thermalLine[0], thermalLine[1]);
    }
}
// result
console.log(findCrossings(diagram));
